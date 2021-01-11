package de.tekup.project.services;

import java.time.Instant; 
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import com.tekup.restau.DTO.MetsDTO.MetResponse;
import com.tekup.restau.models.Met;
import com.tekup.restau.models.Plat;
import com.tekup.restau.models.Ticket;

import de.tekup.project.Dto.MetReponse;
import de.tekup.project.Dto.Tablereponse;
import de.tekup.project.Modele.Client;
import de.tekup.project.Modele.MetEntity;
import de.tekup.project.Modele.TableEntity;
import de.tekup.project.Modele.TicketEntity;
import de.tekup.project.Repository.MetRepisotery;
import de.tekup.project.Repository.TableRepository;
import de.tekup.project.Repository.TicketRepository;
import de.tekup.project.Repository.clientRepository;

@Service
public class TicketService {

	private TicketRepository repoticket;
private TableRepository repotable;
private clientRepository repoclient;
private MetRepisotery repomet;
private ModelMapper mapper = new ModelMapper();
	
@Autowired
	public TicketService(TicketRepository repoticket, TableRepository repotable, clientRepository repoclient,
		MetRepisotery repomet) {
	super();
	this.repoticket = repoticket;
	this.repotable = repotable;
	this.repoclient = repoclient;
	this.repomet = repomet;
}
	
	public List<TicketEntity> getAllEntities() {
		// TODO Auto-generated method stub
		return repoticket.findAll();
	}
	public TicketEntity getEntityById(Integer id) {
		Optional<TicketEntity> opt = repoticket.findById(id);
		TicketEntity entity;
		if(opt.isPresent())
			entity= opt.get();
		else
			throw new NoSuchElementException("met with this Id is not found");
		return entity;	
	}


	public TicketEntity createTicket(TicketEntity entity) {
        Instant now = Instant.now();
        entity.setDate(now);
		return repoticket.save(entity);
	}

	public TicketEntity modifyticket(Integer id, TicketEntity newEntity) {
		TicketEntity oldmet= this.getEntityById(id);
		Instant now = Instant.now();
		if(newEntity.getNumero() != 0)
	    oldmet.setNumero(newEntity.getNumero());
		oldmet.setDate(now);
		return repoticket.save(oldmet);
	}

	public TicketEntity deleteticket(Integer id) {
		TicketEntity entity = this.getEntityById(id);
		repoticket.deleteById(id);
		return entity;
	}
		
    public Tablereponse mostReservedTable(){
        Map<Long,Integer> listTableWithkey=new HashMap<>();
        List<TableEntity> tables=repotable.findAll();
        for(TableEntity table:tables){
            listTableWithkey.put(table.getIdtable(),table.getTicket().size());
        }
        Long toptable= listTableWithkey.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        TableEntity table=repotable.findById(toptable).get();
        return mapper.map(table,Tablereponse.class);
       }
       public String RevenuejSm(){
        List<TicketEntity> tickets=repoticket.findAll();
        double revenueJours=0,revenueSemaine=0,revenuemois=0;
        for (TicketEntity ticket:tickets){
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(30)))){
                revenuemois=revenuemois+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(7)))){
                revenueSemaine=revenueSemaine+ticket.getAddition();
            }
            if (ticket.getDate().isAfter(Instant.now().minus(Period.ofDays(1)))){
                revenueJours=revenueJours+ticket.getAddition();
            }
        }

return "Revenue moins derniere :"+revenuemois+"\n Revenue semaine derniere :"+revenueSemaine+"\n Revenue jour derniere :"+revenueJours;
       }
	
   public Instant mostresrvedday(int id) {
           Optional  <Client> client=repoclient.findById(id);
           Instant dateplusrepter=Instant.now();
           if(client.isPresent()){
               dateplusrepter= client.get().getTicket().stream().map(ticket->ticket.getDate())
                       .collect(Collectors.groupingBy(I->I, Collectors.counting()))
                       .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
           }else throw new NoSuchElementException("client id est incorrect ");
           return dateplusrepter;
       }
   
   
   public double revenudansperiode(Instant debutperiode, Instant finperiode) {
       List<TicketEntity> tickets=repoticket.findAll();
       double somme=0;
       for(TicketEntity ticket:tickets){
           if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
               somme=ticket.getAddition()+somme;
           }
       }
       return somme;
   }
   public Client ClientplusFidel(Instant debutperiode,Instant finperiode){
       List<TicketEntity>tickets=repoticket.findAll();
       List<TicketEntity>ticketss=new ArrayList<>();

       for(TicketEntity ticket:tickets){
           if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
               ticketss.add(ticket);
           }
       }
       List<Client> cl=  ticketss.stream().map(tic->tic.getClient()).collect(Collectors.toList());

       Client fidel=cl.stream().collect(Collectors.groupingBy(l->l, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
       return fidel;
   }
   public MetReponse mostBuyedPlat(Instant begin,Instant end){
       List<TicketEntity> tickets=repoticket.findAll();
       List<Long> idList=new ArrayList<>();
       for (TicketEntity ticket:tickets){
           //check if ticket is in the given time interval
           if(ticket.getDate().isAfter(begin)&&ticket.getDate().isBefore(end)){

               for (MetEntity met:ticket.getMet()){
                   //filtering Plat out from list of mets
                   if(met instanceof de.tekup.project.Modele.Plat){
                       idList.add(met.getIdmet());
                   }
               }
           }
       }
       Long metid= idList.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
        .entrySet()
        .stream()
         .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
       MetEntity met=repomet.findById(metid).get();
       return mapper.map(met,MetReponse.class);
   }
}
