package Rockwell.CRUD.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.Valve;
import Rockwell.CRUD.repositories.ValveRepository;
import Rockwell.CRUD.requests.CreateValveRequest;

@Service

public class ValveService {
    private final ValveRepository valveRepository;

    public ValveService(ValveRepository valveRepository) {
        this.valveRepository = valveRepository;
    }

    public List<Valve> getAllValves(){
        return valveRepository.findAll();
    }

    public Valve getValveByName(String name){
        return valveRepository.findValveByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name));
    }

    public void deleteAllValves(){
        valveRepository.deleteAll();
    }

    public void deleteValveByName(String valveName) {
        valveRepository.deleteValveByName(valveName);
    }

    public Valve createValve(CreateValveRequest request){
        Valve valve = new Valve();

        //long generatedId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        //valve.setId(generatedId);

        valve.setName(request.getName());
        valve.setPositionX(request.getPositionX());
        valve.setPositionY(request.getPositionY());
        
        return valveRepository.save(valve);
    }


    
    public void deleteValve(String name){
        valveRepository.deleteValveByName(name);
    }

    public void connectToValve(String valveName, String valveName2){
        valveRepository.connectToValve(valveName, valveName2);
    }

   
    public void connectToTank(String valveName, int tankNumber){
        valveRepository.connectToTank(valveName, tankNumber);
    }

    public void connectToEntradaESaida(String valveName, String entradaESaidaName){
        valveRepository.connectToEntradaESaida(valveName, entradaESaidaName);
    }

    public void deleteConnectionToTank(String valveName, int tankNumber){
        valveRepository.deleteConnectionToTank(valveName, tankNumber);
    }

    public void deleteConnectionToEntradaESaida(String valveName, String entradaESaidaName){
        valveRepository.deleteConnectionToEntradaESaida(valveName, entradaESaidaName);
    }

     public void deleteConnectionToValve(String valveName, String valveName2){
            valveRepository.deleteConnectionToValve(valveName, valveName2);
    }


    public void connectToHub(String valveName, String hubName) {
        valveRepository.connectToHub(valveName, hubName);
    }

    public void deleteConnectionToHub(String valveName, String hubName) {
        valveRepository.deleteConnectionToHub(valveName, hubName);
    }
}