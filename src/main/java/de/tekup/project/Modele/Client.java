package de.tekup.project.Modele;

import java.time.LocalDate; 
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = "ticket")
@ToString(exclude = "ticket")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idclient;
	private String nom;
	private String prenom ;
	private LocalDate dateofbirth;
	private String couriel;
	private String telephone;
	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<TicketEntity> ticket;
	
	
	
}
