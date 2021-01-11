package de.tekup.project.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	private String nom;
	private String prenom;
    private LocalDate dateofbirth;
	private String couriel;
	private int telephone;

}
