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

import de.tekup.project.Dto.MetReponse;
import de.tekup.project.Dto.Metrequest;
import de.tekup.project.Modele.MetEntity;
import de.tekup.project.services.MetService;

@RestController
@RequestMapping("/met")
public class MetController {
private MetService servicemet;
@Autowired
public MetController(MetService servicemet) {
	super();
	this.servicemet = servicemet;
}
@GetMapping
public List<MetReponse> getAll(){
	return servicemet.getAllEntities();
}

@GetMapping("/{id}")
public MetReponse getById(@PathVariable("id") Integer id) {
	return servicemet.getEntityById(id);
}
@GetMapping("/plat/{nom}")
public MetEntity getById(@PathVariable("nom") String nom) {
	return servicemet.getbynom(nom);
}

@PostMapping("/plat")
public MetReponse createPlat(@RequestBody Metrequest met) {
	return servicemet.createplat(met);
}
@PostMapping("/dessert")
public MetReponse createdessert(@RequestBody Metrequest met) {
	return servicemet.createdessert(met);
}
@PostMapping("/entree")
public MetReponse createentree(@RequestBody Metrequest met) {
	return servicemet.createentree(met);
}
@PutMapping("/{id}")
public MetReponse modifymedecin(@PathVariable("id") Integer id, @RequestBody Metrequest newmet) {
	return servicemet.modifymet(id, newmet);
}

@DeleteMapping("/{id}")
public MetReponse deleteById(@PathVariable("id") Integer id) {
	return servicemet.deletemet(id);
}

@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
	return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
}


	
	
	
}
