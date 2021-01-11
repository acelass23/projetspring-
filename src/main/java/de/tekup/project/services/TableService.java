package de.tekup.project.services;

import java.util.ArrayList;
import java.util.List; 
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.Dto.TableRequest;
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.TableEntity;
import de.tekup.project.Repository.TableRepository;

@Service
public class TableService {

	private TableRepository repotable;
	private ModelMapper mapper = new ModelMapper();
@Autowired
	public TableService(TableRepository repotable) {
		super();
		this.repotable = repotable;
	}
	

public List<Tablereponse> getAllEntities() {
	// TODO Auto-generated method stub
	
	       List<TableEntity> tables = repotable.findAll();
	       List<Tablereponse> reponses = new ArrayList<>();
	       for (TableEntity table:tables) {
	    	   reponses.add(mapper.map(table, Tablereponse.class)); } 
   return reponses;
}
public Tablereponse getEntityById(Long id) {
	
	Optional<TableEntity> opt = repotable.findById(id);
	TableEntity entity;
	if(opt.isPresent())
		entity= opt.get();
	else
		throw new NoSuchElementException("table with this Id is not found");
	return mapper.map(entity, Tablereponse.class);
	
}

public Tablereponse createtable(TableRequest request) {
	TableEntity tablerequest = mapper.map(request, TableEntity.class);
	
	 repotable.save(tablerequest);
return mapper.map(tablerequest, Tablereponse.class);
}

public Tablereponse modifytable(Long id, TableRequest tableresquest) {
	Tablereponse tablereponse = this.getEntityById(id);
	TableEntity  oldTbale = mapper.map(tablereponse, TableEntity.class);
	TableEntity newEntity = mapper.map(tableresquest, TableEntity.class);
 	if(newEntity.getNumero() != 0)
		oldTbale.setNumero(newEntity.getNumero());
	
	  if(newEntity.getNbcouvert() != 0)
	  oldTbale.setNbcouvert(newEntity.getNbcouvert());
	 
	if(newEntity.getSupplement() != 0)
		oldTbale.setSupplement(newEntity.getSupplement());
	if(newEntity.getType() != null)
		oldTbale.setType(newEntity.getType());

	 repotable.save(oldTbale);
  return mapper.map(tableresquest, Tablereponse.class);
}

public Tablereponse deletetable(Long id) {
	Tablereponse entity = this.getEntityById(id);
	repotable.deleteById(id);
	return entity;
}
public TableEntity  findbynumero(int numero){
	Optional<TableEntity> opt = repotable.findByNumero(numero);
	TableEntity entity;
	if(opt.isPresent())
		entity= opt.get();
	else
		throw new NoSuchElementException("table with this numero is not found");
	return entity;
	
}	
}
