package hu.bme.vik.szoftarch.climbingorgmanager.rest.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import hu.bme.vik.szoftarch.climbingorgamanager.backend.exceptions.NoSuchEquipmentException;
import hu.bme.vik.szoftarch.climbingorgamanager.backend.managers.EquipmentManager;
import hu.bme.vik.szoftarch.climbingorgmanager.core.entities.Equipment;
import hu.bme.vik.szoftarch.climbingorgmanager.core.entities.EquipmentType;
import hu.bme.vik.szoftarch.climbingorgmanager.core.entities.Rental;

@Path("/equipments")
@Produces("application/json")
@Consumes("application/json")
public class EquipmentService {

	@Inject
	private EquipmentManager equipmentManager;

	@GET
	public List<Equipment> getEquipments(@QueryParam("name") String name,
			@QueryParam("equipmentTypeId") Long equipmentTypeId, @QueryParam("rented") Boolean rented,
			@QueryParam("accessionNumber") String accessionNumber, @QueryParam("description") String description) {

		EquipmentManager.EquipmentFilter equipmentFilter =
				new EquipmentManager.EquipmentFilter(name, equipmentTypeId, rented, accessionNumber, description);
		List<Equipment> equipments = equipmentManager.getEquipments(equipmentFilter);
		for (Equipment equipment : equipments) {
			if (equipment.getActualRental() != null) {
				Rental rental = equipment.getActualRental();
				rental.setEquipment(null);
			}
		}
		return equipments;
	}

	@POST
	public Response addEquipment(Equipment equipment) {
		equipmentManager.addEquipment(equipment);
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	public Response editEquipment(Equipment equipment) {
		equipmentManager.editEquipment(equipment);
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeEquipment(@PathParam("id") long id) {
		Equipment equipment;
		try {
			equipment = equipmentManager.getEquipmentById(id);
			equipmentManager.removeEquipment(equipment);
		} catch (NoSuchEquipmentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No equipment found for id.").build();
		}
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/types")
	public List<EquipmentType> getEquipmentTypes() {
		return equipmentManager.getEquipmentTypes();
	}
}
