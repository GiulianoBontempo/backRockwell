package Rockwell.CRUD.services;

// Importações das classes e anotações necessárias
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.repositories.HUBRepository;
import Rockwell.CRUD.requests.CreateHUBRequest;

// Anotação que marca a classe como um serviço gerenciado pelo Spring
@Service
public class HUBService {
    private final HUBRepository hubRepository;

    // Injeção de dependência do repositório HUBRepository
    public HUBService(HUBRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    // Métodos de serviço relacionados aos HUBs
    public List<HUB> getAllHubs(){
        return hubRepository.findAll();
    }

    public HUB getHubByName(String name){
        return hubRepository.findHUBByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name));
    }

    public HUB createHub(CreateHUBRequest request){
        HUB hub = new HUB();

        hub.setName(request.getName());

        hubRepository.save(hub);

        return hub;
    }

    public void deleteHub(String name){
        HUB hub = getHubByName(name);

        hubRepository.delete(hub);
    }

    public Boolean checkIfConnectedToTank(String hubName, int tankNumber){
        return hubRepository.checkIfConnectedToTank(hubName, tankNumber);
    }

    public void createConnectionToTank(String hubName, int tankNumber){
        hubRepository.createConnectionToTank(hubName, tankNumber);
    }

    public Boolean checkIfConnectedToItem(String hubName, String itemName){
        return hubRepository.checkIfConnectedToItem(hubName, itemName);
    }

    public void createConnectionToItem(String hubName, String itemName){
        hubRepository.createConnectionToItem(hubName, itemName);
    }

    public Boolean checkIfConnectedToHub(String startHubName, String endHubName){
        return hubRepository.checkIfConnectedToHub(startHubName, endHubName);
    }

    public void createConnectionToHub(String startHubName, String endHubName){
        hubRepository.createConnectionToHub(startHubName, endHubName);
    }

    public void deleteConnectionToTank(String hubName, int tankNumber){
        hubRepository.deleteConnectionToTank(hubName, tankNumber);
    }

    public void deleteConnectionToItem(String hubName, String itemName){
        hubRepository.deleteConnectionToItem(hubName, itemName);
    }

    public void deleteConnectionToHub(String startHubName, String endHubName){
        hubRepository.deleteConnectionToHub(startHubName, endHubName);
    }

    
}
