package de.tekup.project.endpoint;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.project.Dto.ClientReponse;
import de.tekup.project.Dto.ClientRequest;
import de.tekup.project.Modele.Client;
import de.tekup.project.services.ClientService;

@RestController
@RequestMapping("/cLient")
public class CLientController {

	private ClientService serclient;
@Autowired
	public CLientController(ClientService serclient) {
		super();
		this.serclient = serclient;
	}
	
	@GetMapping
	public List<ClientReponse> getAll(){
		return serclient.getAllEntities();
	}

	@GetMapping("/{id}")
	public ClientReponse getById(@PathVariable("id") Integer id) {
		return serclient.getEntityById(id);
	}
	
	@PostMapping
	public ClientReponse createclient(@RequestBody ClientRequest client) {
		return serclient.createclient(client);
	}

	@PutMapping("/{id}")
	public ClientReponse modifymedecin(@PathVariable("id") Integer id, @RequestBody ClientRequest client) {
		return serclient.modifyclient(id, client);
	}

	@DeleteMapping("/{id}")
	public ClientReponse deleteById(@PathVariable("id") Integer id) {
		return serclient.deletclient(id);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	

	
}
