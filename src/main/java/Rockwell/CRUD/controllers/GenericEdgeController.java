package Rockwell.CRUD.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.services.GenericEdgeService;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/genericEdges")
public class GenericEdgeController {

    private final GenericEdgeService genericEdgeService;

    /**
     * Construtor para criar um novo HUBController com dependências injetadas.
     * @param genericEdgeService O serviço para o gerenciamento de arestas genéricas.
     */

    // Injeção de dependências dos serviços HUBService e TankService
    public GenericEdgeController(GenericEdgeService genericEdgeService) {
        this.genericEdgeService = genericEdgeService;
    }

    /**
     * Endpoint para criar um novo HUB.
     *
     * @param connectMap Mapa contendo o número do tanque e o nome do HUB a serem conectados.
     * @return ResponseEntity<String> com feedback da operação.
     */

    // Endpoint para criar um novo HUB
    @PostMapping("/createEdge")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> createEdges(@RequestBody Map<String, Object> connectMap){
        try {
            String typeStart = (String) connectMap.get("typeStart");
            String nameOrNumberStart = (String) connectMap.get("nameOrNumberStart");
            String typeEnd = (String) connectMap.get("typeEnd");
            String nameOrNumberEnd = (String) connectMap.get("nameOrNumberEnd");
            genericEdgeService.createEdges(typeStart, nameOrNumberStart, typeEnd, nameOrNumberEnd);
            return ResponseEntity.ok("Sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
