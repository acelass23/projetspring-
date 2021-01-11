package de.tekup.project.Modele;

import java.time.Instant;  
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idticket;
	@Column(length = 255,nullable = false,unique = true)
	private int numero;
	private Instant date;
	private int nbcouvert;
	private float addition;
	@ManyToOne
	private Client client;
	@ManyToOne
	private TableEntity table;
	@ManyToMany()
	@JoinTable(name = "Compose")
	private List<MetEntity>met;
}
