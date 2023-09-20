package Rockwell.CRUD.controllers;

// Importações das classes e anotações necessárias
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.models.Tank;
import Rockwell.CRUD.requests.CreateTankRequest;
import Rockwell.CRUD.requests.UpdateTankPositionRequest;
import Rockwell.CRUD.services.TankService;

@RestController
@RequestMapping("/api/v1/tank")
public class TankController {
    private final TankService tankService;


    /**
     * Construtor para criar um novo TankController com dependências injetadas.
     *
     * @param tankService O serviço para o gerenciamento de tanques.
     * @param hubService O serviço para o gerenciamento de HUBs.
     */
    // Injeção de dependências dos serviços TankService e HUBService
    public TankController(TankService tankService){
        this.tankService = tankService;
    }


     /**
     * Endpoint para recuperar todos os tanques.
     *
     * @return Uma lista de todos os tanques.
     */
    // Endpoint para obter todos os tanques
    @GetMapping("/")
    public ResponseEntity<List<Tank>> tankIndex(){
        return new ResponseEntity<List<Tank>>(tankService.getAllTanks(), HttpStatus.OK);
    }


     /**
     * Endpoint para criar um novo tanque.
     *
     * @param request O pedido contendo informações para criar um novo tanque.
     * @return O tanque criado.
     */
    // Endpoint para criar um novo tanque
    @PostMapping("/create")
    public ResponseEntity<Tank> createTank(@RequestBody CreateTankRequest request){
        Tank tank = tankService.createTank(request);
        return new ResponseEntity<>(tank , HttpStatus.CREATED);
    }

    
    /**
     * Endpoint para deletar um tanque pelo número.
     *
     * @param number O número do tanque a ser deletado.
     * @return O número do tanque deletado.
     */
    // Endpoint para deletar um tanque pelo número
    @DeleteMapping("/{number}")
    public ResponseEntity<Integer> deleteTank(@PathVariable int number){
        tankService.deleteTank(number);
        return new ResponseEntity<Integer>(number, HttpStatus.OK);
    }

    /**
     * Endpoint para conectar dois tanques por número.
     *
     * @param conectarMap Mapa contendo os números dos tanques a serem conectados.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @PostMapping("/connectToTank")
    public ResponseEntity<String> connectByName(@RequestBody Map<String, Integer> connectMap){
        tankService.connectByNumber(connectMap.get("startTankNumber"), connectMap.get("endTankNumber"));
        return  new ResponseEntity<>("Tanks conectados!", HttpStatus.OK);
    }


    /**
     * Endpoint para conectar um tanque a um EntradaESaida.
     *
     * @param conectaMap Mapa contendo o número do tanque e o nome do EntradaESaida a serem conectados.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @PostMapping("/connectToEntradaESaida")
    public ResponseEntity<String> connectTankToEntradaESaida(@RequestBody Map<String, Object> connectMap){
        Integer tankNumber = (Integer) connectMap.get("tankNumber");
        String entradaESaidaName = (String) connectMap.get("entradaESaidaName");
        tankService.connectTankToEntradaESaida(tankNumber, entradaESaidaName);
        return new ResponseEntity<>("Conexão criada com sucesso", HttpStatus.OK);
    }


    /**
     * Endpoint para deletar uma conexão entre dois tanques.
     *
     * @param deletarMap Mapa contendo os números dos tanques cuja conexão será deletada.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @DeleteMapping("/deleteConnection")
    public ResponseEntity<String> deleteConnection(@RequestBody Map<String, Integer> deleteMap){
        tankService.deleteConnection(deleteMap.get("startTankNumber"), deleteMap.get("endTankNumber"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);

    }


    /**
     * Endpoint para deletar uma conexão entre um tanque e um EntradaESaida.
     *
     * @param deletarmMap Mapa contendo o número do tanque e o nome do EntradaESaida cuja conexão será deletada.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @DeleteMapping("/deleteTankEntradaESaidaConnection")
    public ResponseEntity<String> deleteTankEntradaESaidaConnection(@RequestBody Map<String, Object> deleteMap){
        Integer tankNumber = (Integer) deleteMap.get("tankNumber");
        String entradaESaidaName = (String) deleteMap.get("entradaESaidaName");
        tankService.deleteTankEntradaESaidaConnection(tankNumber, entradaESaidaName);
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);

    }


    /**
     * Endpoint para conectar um tanque a um HUB.
     *
     * @param connectMap Mapa contendo o número do tanque e o nome do HUB a serem conectados.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @PostMapping("/connectToHub")
    public ResponseEntity<String> connectToHub(@RequestBody Map<String, Object> connectMap){
        Integer tankNumber = (Integer) connectMap.get("tankNumber");
        String hubName = (String) connectMap.get("hubName");
        tankService.connectTankToHub(tankNumber, hubName);
        return new ResponseEntity<>("Conexao criada com sucesso", HttpStatus.OK);
    }
    

    /**
     * Endpoint para deletar uma conexão entre um tanque e um HUB.
     *
     * @param disconnectMap Mapa contendo o número do tanque e o nome do HUB cuja conexão será deletada.
     * @return Uma mensagem indicando sucesso na operação.
     */
    @DeleteMapping("/deleteConnectionToHub")
    public ResponseEntity<String> deleteConnectionToHub(@RequestBody Map<String, Object> disconnectMap) {

        Integer tankNumber = (Integer) disconnectMap.get("tankNumber");
        String hubName = (String) disconnectMap.get("hubName");
        tankService.deleteConnectionToHub(tankNumber, hubName);
        return new ResponseEntity<>("Tank disconnected from Hub successfully", HttpStatus.OK);

    }

    @PutMapping("/{number}/updatePosition")
    public ResponseEntity<Tank> updateTankPosition(@PathVariable int number, @RequestBody UpdateTankPositionRequest request) {
        Tank updatedTank = tankService.updateTankPosition(number, request.getPositionX(), request.getPositionY());
        return new ResponseEntity<>(updatedTank, HttpStatus.OK);
    }
}