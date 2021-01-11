package de.tekup.project.Modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = "ticket")
@ToString(exclude = "ticket")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
name="type")
public class MetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Idmet;
    @Column(length = 255,nullable = false,unique = true)
	private String nom;
	private float prix;
	@ManyToMany(mappedBy = "met")
	@JsonIgnore
	private List<TicketEntity> ticket = new ArrayList<>();
	
}
