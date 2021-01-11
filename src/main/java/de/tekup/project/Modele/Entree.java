package de.tekup.project.Modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Entree")
public class Entree  extends MetEntity{

}
