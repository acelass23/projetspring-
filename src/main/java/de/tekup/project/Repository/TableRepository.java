package de.tekup.project.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.Modele.TableEntity;
import de.tekup.project.Modele.TicketEntity;

public interface TableRepository  extends JpaRepository<TableEntity, Long>{
	public  Optional<TableEntity> findByNumero (int numero);



}
