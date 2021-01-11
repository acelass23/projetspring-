package de.tekup.project.Modele;

import java.util.List;  

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class TableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtable;
	@Column(length = 255,nullable = false,unique = true)
	private int numero;
	private int nbcouvert;
	private float supplement;
	private String type;
	@OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<TicketEntity>ticket;
	
}
