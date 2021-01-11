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

import de.tekup.project.Dto.TableRequest;
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.TableEntity;
import de.tekup.project.services.TableService;

@RestController
@RequestMapping("/table")
public class TableController {
 private TableService servicetable;

 @Autowired
public TableController(TableService servicetable) {
	super();
	this.servicetable = servicetable;
}
 @GetMapping
	public List<Tablereponse> getAll(){
		return servicetable.getAllEntities();
	}
	
	@GetMapping("/{id}")
	public Tablereponse getById(@PathVariable("id") Long id) {
		return servicetable.getEntityById(id);
	}
	@GetMapping("/num/{numero}")
	public TableEntity getBynumero(@PathVariable("numero") int numero) {
		return servicetable.findbynumero(numero);
	}
	
	@PostMapping
	public Tablereponse createtable(@RequestBody TableRequest table) {
		return servicetable.createtable(table);
	}
	
	@PutMapping("/{id}")
	public Tablereponse modifymedecin(@PathVariable("id") Long id, @RequestBody TableRequest newtable) {
		return servicetable.modifytable(id, newtable);
	}
		@DeleteMapping("/{id}")
	public Tablereponse deleteById(@PathVariable("id") Long id) {
		return servicetable.deletetable(id);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
