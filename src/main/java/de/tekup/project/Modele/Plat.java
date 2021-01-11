package de.tekup.project.Modele;

import javax.persistence.DiscriminatorValue; 
import javax.persistence.Entity;

import lombok.Data;
@Entity
@Data
@DiscriminatorValue("plat")
public class Plat  extends MetEntity{

}
