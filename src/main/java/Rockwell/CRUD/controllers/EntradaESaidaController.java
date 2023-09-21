package Rockwell.CRUD.controllers;

// Importações das classes e anotações necessárias
import Rockwell.CRUD.services.EntradaESaidaService;
import Rockwell.CRUD.models.EntradaESaida;
import Rockwell.CRUD.requests.CreateEntradaESaidaRequest;
import Rockwell.CRUD.requests.UpdateEntradaESaidaPosition;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/EntradaESaida")
public class EntradaESaidaController {

    @Autowired
    private EntradaESaidaService entradaESaidaService;

    /**
     * Endpoint para recuperar todas as entradas e saídas.
     *
     * @return Uma lista iterável de todas as entradas e saídas.
     */
    @GetMapping("/")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public Iterable<EntradaESaida> getAllEntradasOuSaidas() {
        return entradaESaidaService.getAllEntradasOuSaidas();
    }


     @GetMapping("/{name}")
     @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<EntradaESaida> getEntradaESaidaByName(@PathVariable String name) {
        EntradaESaida entradaESaida = entradaESaidaService.getEntradaESaidaByName(name);
        return new ResponseEntity<>(entradaESaida, HttpStatus.OK);
    }

    /**
     * Endpoint para criar uma nova entrada e saída.
     *
     * @param newEntradaESaidaRequest O pedido contendo informações para criar uma nova entrada e saída.
     * @return A entrada e saída criada.
     */
    @PostMapping("/create")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public EntradaESaida createEntradaOuSaida(@RequestBody CreateEntradaESaidaRequest newEntradaESaidaRequest) {
        return entradaESaidaService.createEntradaOuSaida(newEntradaESaidaRequest);
    }

    /**
     * Endpoint para conectar duas entradas e saídas pelo nome.
     *
     * @param conectarMap Um mapa contendo os nomes das entradas e saídas a serem conectados.
     * @return Uma mensagem de sucesso.
     */
    @PostMapping("/connectToEntradaESaida")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectByName(@RequestBody Map<String, String> connectMap) {
        entradaESaidaService.connectByName(connectMap.get("startItem"), connectMap.get("endItem"));
        return new ResponseEntity<>("Entradas e Saídas conectadas!", HttpStatus.OK);
    }

    /**
     * Endpoint para deletar todas as entradas e saídas.
     */
    @DeleteMapping("/delete")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public void deleteAllEntradasOuSaidas() {
        entradaESaidaService.deleteAllEntradasOuSaidas();
    }

    @DeleteMapping("/deleteByName")
    public ResponseEntity<String> deleteValveByName(@RequestBody Map<String, String> deleteMap) {
        entradaESaidaService.deleteByName(deleteMap.get("name"));
        return new ResponseEntity<>("Entrada ou Saida deletada", HttpStatus.OK);
    }

    /**
     * Endpoint para deletar uma conexão entre entradas e saídas pelo nome.
     *
     * @param deletarMap Um mapa contendo os nomes das entradas e saídas cuja conexão será deletada.
     * @return Uma mensagem de sucesso.
     */
    @DeleteMapping("/deleteConnection")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteConnection(@RequestBody Map<String, String> deleteMap){
        entradaESaidaService.deleteConnection(deleteMap.get("startItem"), deleteMap.get("endItem"));
        return new ResponseEntity<>("Conexão entre entradas e saídas deletada", HttpStatus.OK);
    }

    /**
     * Endpoint para conectar uma entrada ou saída a um HUB pelo nome.
     *
     * @param conectarMap Um mapa contendo os nomes da entrada ou saída e do HUB a serem conectados.
     * @return Uma mensagem de sucesso.
     */
    @PostMapping("/connectToHUB")
    public ResponseEntity<String> connectToHub(@RequestBody Map<String, String> connectMap) {
        entradaESaidaService.connectToHub(connectMap.get("entradaESaidaName"), connectMap.get("hubName"));
        return new ResponseEntity<>("EntradaESaida conectada ao HUB", HttpStatus.OK);
    }


    //connect to tank
    @PostMapping("/connectToTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectEntradaESaidaToTank(@RequestBody Map<String, Object> connectMap){
        String entradaESaidaName = (String) connectMap.get("entradaESaidaName");
        Integer tankNumber = (Integer) connectMap.get("tankNumber");
        entradaESaidaService.connectEntradaESaidaToTank(entradaESaidaName, tankNumber);
        return new ResponseEntity<>("Entrada e Saída conectadas ao tanque", HttpStatus.OK);
    }

    @PostMapping("/connectToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectEntradaESaidaToValve(@RequestBody Map<String, String> connectMap) {
        entradaESaidaService.connectEntradaESaidaToValve(connectMap.get("entradaESaidaName"), connectMap.get("valveName"));
        return new ResponseEntity<>("EntradaESaida conectada ao Valve", HttpStatus.OK);
    }

    @DeleteMapping("/deleteConnectionToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteConnectionToValve(@RequestBody Map<String, String> deleteMap) {
        String entradaESaidaName = deleteMap.get("entradaESaidaName");
        String valveName = deleteMap.get("valveName");
        entradaESaidaService.deleteConnectionToValve(entradaESaidaName, valveName);
        return new ResponseEntity<>("Conexão entre EntradaESaida e Valve deletada", HttpStatus.OK);
    }


    @DeleteMapping("/deleteConnectionToHub")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteConnectionToHub(@RequestBody Map<String, String> deleteMap) {
        entradaESaidaService.deleteConnectionToHub(deleteMap.get("entradaESaidaName"), deleteMap.get("hubName"));
        return new ResponseEntity<>("Conexão entre EntradaESaida e HUB deletada", HttpStatus.OK);
    }


    @DeleteMapping("/deleteConnectionToTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteConnectionToTank(@RequestBody Map<String, String> deleteMap) {
        String entradaESaidaName = deleteMap.get("entradaESaidaName");
        int tankNumber = Integer.parseInt(deleteMap.get("tankNumber"));
        entradaESaidaService.deleteConnectionToTank(entradaESaidaName, tankNumber);
        return new ResponseEntity<>("Conexão entre EntradaESaida e Tank deletada", HttpStatus.OK);
    }

    @PutMapping("/{name}/updatePosition")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
        public ResponseEntity<EntradaESaida> updateEntradaESaidaPosition(@PathVariable String name,@RequestBody UpdateEntradaESaidaPosition request) {
            EntradaESaida updatedEntradaESaida = entradaESaidaService.updateEntradaESaidaPosition(name, request.getPositionX(), request.getPositionY());
            return new ResponseEntity<>(updatedEntradaESaida, HttpStatus.OK);
    }
}
