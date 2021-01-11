package de.tekup.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import de.tekup.project.Modele.TicketEntity;

public interface TicketRepository  extends JpaRepository<TicketEntity, Long>{


}
