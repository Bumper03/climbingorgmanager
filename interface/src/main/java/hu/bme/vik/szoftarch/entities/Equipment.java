package hu.bme.vik.szoftarch.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NamedQueries({
		@NamedQuery(
				name = Equipment.GET_ALL,
				query = "select e from Equipment e"
		),
		@NamedQuery(
				name = Equipment.GET_BY_ID,
				query = "select e from Equipment e where e.id = :id"
		)
})
public class Equipment implements Serializable {

	public static final String GET_ALL = "Equipment.getAll";
	public static final String GET_BY_ID = "Equipment.getById";

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 4, max = 20)
    @NotNull
    private String accessionNumber;

    @Size(min = 5, max = 256)
    @NotNull
    private String description;

    @Size(min = 3, max = 40)
    @NotNull
    private String name;

    @ManyToOne
    @NotNull
    private EquipmentType equipmentType;

}
