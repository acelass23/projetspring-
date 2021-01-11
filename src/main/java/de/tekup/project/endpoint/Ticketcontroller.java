package de.tekup.project.endpoint;

import java.time.Instant; 
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
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.MetEntity;
import de.tekup.project.Modele.TicketEntity;
import de.tekup.project.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class Ticketcontroller {

	private TicketService serviceticket;
	
	
@Autowired
	public Ticketcontroller(TicketService serviceticket) {
		super();
		this.serviceticket = serviceticket;
	}
	
	
@GetMapping
public List<TicketEntity> getAll(){
	return serviceticket.getAllEntities();
}

@GetMapping("/{id}")
public TicketEntity getById(@PathVariable("id") Integer id) {
	return serviceticket.getEntityById(id);
}
@GetMapping("clientjour/{id}")
public Instant clientjour(@PathVariable("id") Integer id) {
	return serviceticket.mostresrvedday(id);
}
@GetMapping("/most")
public Tablereponse most() {
	return serviceticket.mostReservedTable();
}
@GetMapping("/x")
public String Revnue() {
	return serviceticket.RevenuejSm();
}

@PostMapping
public TicketEntity createticket(@RequestBody TicketEntity ticket) {
	return serviceticket.createTicket(ticket);
}

@PutMapping("/{id}")
public TicketEntity modifyticket(@PathVariable("id") Integer id, @RequestBody TicketEntity newticket) {
	return serviceticket.modifyticket(id, newticket);
}
@DeleteMapping("/{id}")
public TicketEntity deleteById(@PathVariable("id") Integer id) {
	return serviceticket.deleteticket(id);
}

@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
	return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
}


@GetMapping("/revenue/period/{begin}/{end}")
public Double RevenuePeriod(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
    return serviceticket.revenudansperiode(begin, end);
}
@GetMapping("/client/fidel/{begin}/{end}")
public de.tekup.project.Modele.Client ClientplusFidel(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
    return serviceticket.ClientplusFidel(begin, end);
}	
	
@GetMapping("/top/plat/{begin}/{end}")
public MetReponse getTopPlat(@PathVariable("begin") Instant begin, @PathVariable("end") Instant end){
    return serviceticket.mostBuyedPlat(begin,end);
}
	
	
}
