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

import Rockwell.CRUD.models.Tank;
import Rockwell.CRUD.requests.CreateTankRequest;
import Rockwell.CRUD.services.HUBService;
import Rockwell.CRUD.services.TankService;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/tank")
public class TankController {
    private final TankService tankService;
    private final HUBService hubService;

    // Injeção de dependências dos serviços TankService e HUBService
    public TankController(TankService tankService, HUBService hubService){
        this.hubService = hubService;
        this.tankService = tankService;
    }

    // Endpoint para obter todos os tanques
    @GetMapping("/")
    public ResponseEntity<List<Tank>> tankIndex(){
        return new ResponseEntity<List<Tank>>(tankService.getAllTanks(), HttpStatus.OK);
    }

    // Endpoint para criar um novo tanque
    @PostMapping("/create")
    public ResponseEntity<Tank> createTank(@RequestBody CreateTankRequest request){
        Tank tank = tankService.createTank(request);
        return new ResponseEntity<>(tank , HttpStatus.CREATED);
    }

    // Endpoint para deletar um tanque pelo número
    @DeleteMapping("/{number}")
    public ResponseEntity<Integer> deleteTank(@PathVariable int number){
        tankService.deleteTank(number);
        return new ResponseEntity<Integer>(number, HttpStatus.OK);
    }
}
