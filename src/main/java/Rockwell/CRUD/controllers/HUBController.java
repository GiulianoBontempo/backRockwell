package Rockwell.CRUD.controllers;

// Importações das classes e anotações necessárias
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.objects.HUBDTO;
import Rockwell.CRUD.requests.CreateHUBRequest;
import Rockwell.CRUD.requests.HUBToHUBRequest;
import Rockwell.CRUD.requests.HUBToItemRequest;
import Rockwell.CRUD.requests.HUBToTankRequest;
import Rockwell.CRUD.services.HUBService;
import Rockwell.CRUD.services.TankService;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/hubs")
public class HUBController {
    private final HUBService hubService;
    private final TankService tankService;

    // Injeção de dependências dos serviços HUBService e TankService
    public HUBController(HUBService hubService, TankService tankService) {
        this.hubService = hubService;
        this.tankService = tankService;
    }

    // Endpoint para obter todos os HUBs
    @GetMapping("/")
    public ResponseEntity<List<HUB>> hubIndex(){
        return new ResponseEntity<List<HUB>>(hubService.getAllHubs(), HttpStatus.OK);
    }

    // Endpoint para obter um HUB pelo nome
    @GetMapping("/{name}")
    public ResponseEntity<HUBDTO> getHubByName(@PathVariable String name){
        HUB hub = hubService.getHubByName(name);

        HUBDTO responseHub = new HUBDTO(hub.getName());
        responseHub.setTanks(tankService.getAllTanksByHUBName(hub.getName()));

        return new ResponseEntity<>(responseHub, HttpStatus.OK);
    }

    // Endpoint para criar um novo HUB
    @PostMapping("/create")
    public ResponseEntity<HUBDTO> createHub(@RequestBody CreateHUBRequest request){
        HUB hub = hubService.createHub(request);
        HUBDTO responseHub = new HUBDTO(hub.getName());

        return new ResponseEntity<>(responseHub, HttpStatus.CREATED);
    }

    // Endpoint para deletar um HUB pelo nome
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHub(@PathVariable String name) {
        hubService.deleteHub(name);
        return new ResponseEntity<>("Hub deleted successfully", HttpStatus.OK);
    }

    // Endpoint para conectar um HUB a um tanque
    @PostMapping("/connectToTank")
    public ResponseEntity<String> connectToTank(@RequestBody HUBToTankRequest request){

        String hubName = request.getName();
        int tankNumber = request.getNumber();

        if(hubService.checkIfConnectedToTank(hubName, tankNumber)){
            return new ResponseEntity<String>("HUB already connected to tank", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.createConnectionToTank(hubName, tankNumber);

        return new ResponseEntity<String>(hubName + " -> " + tankNumber, HttpStatus.OK);
    }

    // Endpoint para conectar um HUB a um item
    @PostMapping("/connectToItem")
    public ResponseEntity<String> connectToValve(@RequestBody HUBToItemRequest request){

        String hubName = request.getHubName();
        String itemName = request.getItemName();

        if(hubService.checkIfConnectedToItem(hubName, itemName)){
            return new ResponseEntity<String>("HUB already connected to item", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.createConnectionToItem(hubName, itemName);

        return new ResponseEntity<String>(hubName + " -> " + itemName, HttpStatus.OK);
    }

    // Endpoint para conectar um HUB a outro HUB
    @PostMapping("/connectToHub")
    public ResponseEntity<String> connectToHub(@RequestBody HUBToHUBRequest request){

        String startHubName = request.getStartHubName();
        String endHubName = request.getEndHubName();

        Boolean isConnected = hubService.checkIfConnectedToHub(startHubName, endHubName);
        if (isConnected != null && isConnected.booleanValue()) {
            return new ResponseEntity<String>("HUB already connected to HUB", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.createConnectionToHub(startHubName, endHubName);

        return new ResponseEntity<String>(startHubName + " -> " + endHubName, HttpStatus.OK);
    }

    // Endpoint para deletar a conexão entre um HUB a um tanque
    @DeleteMapping("/deleteConnectionToTank")
    public ResponseEntity<String> deleteConnectionToTank(@RequestBody HUBToTankRequest request){

        String hubName = request.getName();
        int tankNumber = request.getNumber();

        if(!(hubService.checkIfConnectedToTank(hubName, tankNumber))){
            return new ResponseEntity<String>("The HUB is already not connected to this tank", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.deleteConnectionToTank(hubName, tankNumber);

        return new ResponseEntity<String>(hubName + " and " + tankNumber + " disconnected", HttpStatus.OK);
    }

    // Endpoint para deletar a conexão entre um HUB a um item
    @DeleteMapping("/deleteConnectionToItem")
    public ResponseEntity<String> deleteConnectionToItem(@RequestBody HUBToItemRequest request){

        String hubName = request.getHubName();
        String itemName = request.getItemName();

        if(!(hubService.checkIfConnectedToItem(hubName, itemName))){
            return new ResponseEntity<String>("The HUB is already not connected to this item", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.deleteConnectionToItem(hubName, itemName);

        return new ResponseEntity<String>(hubName + " and " + itemName + " disconnected", HttpStatus.OK);
    }

    // Endpoint para deletar a conexão entre um HUB a outro HUB
    @DeleteMapping("/deleteConnectionToHub")
    public ResponseEntity<String> deleteConnectionToHub(@RequestBody HUBToHUBRequest request){

        String startHubName = request.getStartHubName();
        String endHubName = request.getEndHubName();

        Boolean isConnected = hubService.checkIfConnectedToHub(startHubName, endHubName);
        if (!(isConnected != null && isConnected.booleanValue())) {
            return new ResponseEntity<String>("The HUB is already not connected to this HUB", HttpStatus.NOT_ACCEPTABLE);
        }

        hubService.deleteConnectionToHub(startHubName, endHubName);

        return new ResponseEntity<String>(startHubName + " and " + endHubName + " disconnected", HttpStatus.OK);
    }
}
