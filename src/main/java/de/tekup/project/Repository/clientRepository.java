package de.tekup.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import de.tekup.project.Modele.Client;

public interface clientRepository  extends JpaRepository<Client, Integer>{

}
