package de.tekup.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.Modele.MetEntity;
import de.tekup.project.Modele.TableEntity;

public interface MetRepisotery extends JpaRepository<MetEntity, Integer> {
	public  Optional<MetEntity> findByNom (String nom);
}
