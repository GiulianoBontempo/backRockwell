package Rockwell.CRUD.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.models.Valve;
import Rockwell.CRUD.requests.CreateValveRequest;
import Rockwell.CRUD.services.ValveService;

@RestController
@RequestMapping("/api/v1/valves")


public class ValveController {
    private final ValveService valveService;

    public ValveController(ValveService valveService){
        this.valveService = valveService;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<List<Valve>> getAllValves(){
        return new ResponseEntity<List<Valve>>(valveService.getAllValves(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<Valve> getValveByName(@PathVariable String name){
        return new ResponseEntity<Valve>(valveService.getValveByName(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public Valve createValve(@RequestBody CreateValveRequest request){
        return valveService.createValve(request);
    }

    @DeleteMapping("/delete")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteAllValves(){
        valveService.deleteAllValves();
        return new ResponseEntity<>("Valve deletada", HttpStatus.OK);
    }

    @PostMapping("/connectToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToValve(@RequestBody Map<String,String> connectMap){
        valveService.connectToValve(connectMap.get("name"), connectMap.get("name2"));
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    @DeleteMapping("/deleteConnectionToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValve(@RequestBody Map<String,String> deleteMap){
        valveService.deleteConnectionToValve(deleteMap.get("name"), deleteMap.get("name2"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    @PostMapping("/connectToTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToTank(@RequestBody Map<String, Object> connectMap){
        String valveName = (String) connectMap.get("valve");
        Integer tankName = (Integer) connectMap.get("tank");
        valveService.connectToTank(valveName, tankName);
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    @DeleteMapping("/deleteValveTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValveToTank(@RequestBody Map<String, Object> deleteMap){
        String valveName = (String) deleteMap.get("valve");
        Integer tankName = (Integer) deleteMap.get("tank");
        valveService.deleteConnectionToTank(valveName, tankName);
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    @PostMapping("/connectToEntradaESaida")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToEntradaESaida(@RequestBody Map<String, String> connectMap){
        valveService.connectToEntradaESaida(connectMap.get("valve"), connectMap.get("entradaESaida"));
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    @DeleteMapping("/deleteValveEntradaESaida")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValveToEntradaESaida(@RequestBody Map<String, String> deleteMap){
        valveService.deleteConnectionToEntradaESaida(deleteMap.get("valve"), deleteMap.get("entradaESaida"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }
}