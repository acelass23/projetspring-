package de.tekup.project.services;

 
import java.util.List;  
import java.util.NoSuchElementException;
import java.util.Optional;

import java.util.ArrayList;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.tekup.project.Dto.ClientReponse;
import de.tekup.project.Dto.ClientRequest;
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.Client;
import de.tekup.project.Repository.clientRepository;

@Service
public class ClientService {

	private clientRepository reposclient;
	private ModelMapper mapper = new ModelMapper();
	public ClientService(clientRepository reposclient) {
		super();
		this.reposclient = reposclient;
	}
	
	public List<ClientReponse> getAllEntities() {
		// TODO Auto-generated method stub
      List<Client> clients = reposclient.findAll();
      List<ClientReponse>reponses =new ArrayList<>() ;
        for(Client client:clients) {
	   
	   reponses.add(mapper.map(client, ClientReponse.class));
	   
   }
    return reponses;

	}
	public ClientReponse getEntityById(Integer id) {
		Optional<Client> opt = reposclient.findById(id);
		Client entity;
		if(opt.isPresent())
			entity= opt.get();
		else
			throw new NoSuchElementException("client with this Id is not found");
		return mapper.map(entity,ClientReponse.class);
	}


	public ClientReponse createclient(ClientRequest request) {
		Client entity = mapper.map(request, Client.class);
		 reposclient.save(entity);
		 return mapper.map(entity, ClientReponse.class);
	}

	public ClientReponse modifyclient(Integer id, ClientRequest requestc) {		
		ClientReponse clientreponse= this.getEntityById(id);
		Client oldclien = mapper.map(clientreponse, Client.class);
		Client newEntity = mapper.map(requestc, Client.class);
		if(newEntity.getNom() != null)
			oldclien.setNom(newEntity.getNom());
		if(newEntity.getPrenom() != null)
			oldclien.setPrenom(newEntity.getPrenom());
		if(newEntity.getTelephone()!= null)
			oldclien.setTelephone(newEntity.getTelephone());
		if(newEntity.getCouriel()!= null)
			oldclien.setCouriel(newEntity.getCouriel());
		if(newEntity.getDateofbirth()!= null)
			oldclien.setDateofbirth(newEntity.getDateofbirth());
		 reposclient.save(oldclien);
		 return mapper.map(requestc, ClientReponse.class);
	}

	public ClientReponse deletclient(Integer id) {
		ClientReponse entity = this.getEntityById(id);
		reposclient.deleteById(id);
		return entity;
	}
}
