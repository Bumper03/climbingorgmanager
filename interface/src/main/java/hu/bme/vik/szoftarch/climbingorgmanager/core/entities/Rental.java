package hu.bme.vik.szoftarch.climbingorgmanager.core.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Abel on 2014.11.02..
 */
@Data
@Entity
public class Rental implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Equipment equipment;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date rentedOn;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date rentedUntil;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedOn;

}