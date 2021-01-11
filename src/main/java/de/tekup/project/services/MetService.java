package de.tekup.project.services;

import java.util.ArrayList; 
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.Dto.MetReponse;
import de.tekup.project.Dto.Metrequest;
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.Dessert;
import de.tekup.project.Modele.Entree;
import de.tekup.project.Modele.MetEntity;
import de.tekup.project.Modele.Plat;
import de.tekup.project.Modele.TableEntity;
import de.tekup.project.Repository.DessertRepisotory;
import de.tekup.project.Repository.EntreRepistory;
import de.tekup.project.Repository.MetRepisotery;
import de.tekup.project.Repository.platRepository;

@Service
public class MetService {

	private MetRepisotery repomet;
	private platRepository repoplat;
	private DessertRepisotory repodesert;
	private EntreRepistory repoentre;
	private ModelMapper mapper = new ModelMapper();
	
	
@Autowired
public MetService(MetRepisotery repomet, platRepository repoplat, DessertRepisotory repodesert,
			EntreRepistory repoentre) {
		super();
		this.repomet = repomet;
		this.repoplat = repoplat;
		this.repodesert = repodesert;
		this.repoentre = repoentre;
	}
public List<MetReponse> getAllEntities() {
	
	// TODO Auto-generated method stub
	  List<MetEntity> mets = repomet.findAll();
      List<MetReponse> reponses = new ArrayList<>();
      for (MetEntity met:mets) {
   	   reponses.add(mapper.map(met, MetReponse.class)); } 
return reponses; 
}
public MetReponse getEntityById(Integer id) {
	Optional<MetEntity> opt = repomet.findById(id);
	MetEntity entity;
	if(opt.isPresent())
		entity= opt.get();
	else
		throw new NoSuchElementException("met with this Id is not found");
	return mapper.map(entity, MetReponse.class);	
}



public MetReponse createplat(Metrequest request) {
	Plat entity = mapper.map(request, Plat.class);
	
	entity.setNom(entity.getNom().toUpperCase());
	 repoplat.save(entity);
	 return mapper.map(entity, MetReponse.class);
}
public MetReponse createdessert(Metrequest request) {
	Dessert entity = mapper.map(request, Dessert.class);
	entity.setNom(entity.getNom().toUpperCase());
	 repodesert.save(entity);
	 return mapper.map(entity, MetReponse.class);
}
public MetReponse createentree(Metrequest request) {
	Entree entity = mapper.map(request, Entree.class);
	entity.setNom(entity.getNom().toUpperCase());
	 repoentre.save(entity);
	 return mapper.map(entity, MetReponse.class);
}

public MetReponse modifymet(Integer id, Metrequest metrequest) {
	MetReponse reponse= this.getEntityById(id);
	MetEntity oldmet = mapper.map(reponse, MetEntity.class);
	MetEntity newEntity = mapper.map(metrequest, MetEntity.class);
	if(newEntity.getNom() != null)
		oldmet.setNom(newEntity.getNom().toUpperCase());
	if(newEntity.getPrix() != 0)
		oldmet.setPrix(newEntity.getPrix());
 repomet.save(oldmet);
 return mapper.map(metrequest, MetReponse.class);
}

public MetReponse deletemet(Integer id) {
	MetReponse entity = this.getEntityById(id);
	repomet.deleteById(id);
	return entity;
}
	
public MetEntity getbynom(String nom) {
 String localnom = nom.toUpperCase();	
	Optional<MetEntity> opt = repomet.findByNom(localnom);
	MetEntity entity;
	if(opt.isPresent())
		entity= opt.get();
	else
		throw new NoSuchElementException("met with this nom is not found");
	return entity;
}		
}
