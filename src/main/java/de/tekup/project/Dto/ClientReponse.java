package de.tekup.project.Dto;

import java.time.LocalDate; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientReponse {
	private String nom;
	private String prenom;
    private LocalDate dateofbirth;
	private String couriel;
	private String telephone;	
}
