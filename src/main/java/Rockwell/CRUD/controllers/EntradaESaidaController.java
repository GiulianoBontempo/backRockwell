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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Items")
public class EntradaESaidaController {

    @Autowired
    private EntradaESaidaService entradaESaidaService;

    /**
     * Endpoint para recuperar todas as entradas e saídas.
     *
     * @return Uma lista iterável de todas as entradas e saídas.
     */
    @GetMapping("/")
    public Iterable<EntradaESaida> getAllEntradasOuSaidas() {
        return entradaESaidaService.getAllEntradasOuSaidas();
    }


     @GetMapping("/{name}")
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
    public ResponseEntity<String> connectByName(@RequestBody Map<String, String> connectMap) {
        entradaESaidaService.connectByName(connectMap.get("startItem"), connectMap.get("endItem"));
        return new ResponseEntity<>("Entradas e Saídas conectadas!", HttpStatus.OK);
    }

    /**
     * Endpoint para deletar todas as entradas e saídas.
     */
    @DeleteMapping("/delete")
    public void deleteAllEntradasOuSaidas() {
        entradaESaidaService.deleteAllEntradasOuSaidas();
    }

    /**
     * Endpoint para deletar uma conexão entre entradas e saídas pelo nome.
     *
     * @param deletarMap Um mapa contendo os nomes das entradas e saídas cuja conexão será deletada.
     * @return Uma mensagem de sucesso.
     */
    @DeleteMapping("/deleteConnection")
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
    public ResponseEntity<String> connectEntradaOuSaidaToHub(@RequestBody Map<String, String> connectMap){
        entradaESaidaService.connectEntradaOuSaidaToHub(connectMap.get("Item"), connectMap.get("HUB"));
        return new ResponseEntity<>("Entrada e Saída conectadas ao HUB", HttpStatus.OK);
    }


    //connect to tank
    @PostMapping("/connectToTank")
    public ResponseEntity<String> connectEntradaESaidaToTank(@RequestBody Map<String, Object> connectMap){
        String entradaESaidaName = (String) connectMap.get("entradaESaidaName");
        Integer tankNumber = (Integer) connectMap.get("tankNumber");
        entradaESaidaService.connectEntradaESaidaToTank(entradaESaidaName, tankNumber);
        return new ResponseEntity<>("Entrada e Saída conectadas ao tanque", HttpStatus.OK);
    }

    @PutMapping("/{name}/updatePosition")
        public ResponseEntity<EntradaESaida> updateEntradaESaidaPosition(@PathVariable String name,@RequestBody UpdateEntradaESaidaPosition request) {
            EntradaESaida updatedEntradaESaida = entradaESaidaService.updateEntradaESaidaPosition(name, request.getPositionX(), request.getPositionY());
            return new ResponseEntity<>(updatedEntradaESaida, HttpStatus.OK);
    }
}
