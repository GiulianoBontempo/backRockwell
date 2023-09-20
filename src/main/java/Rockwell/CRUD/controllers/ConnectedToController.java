package Rockwell.CRUD.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.queries.GetAllConnectionsQueryResult;
import Rockwell.CRUD.services.ConnectedToService;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/connections")
public class ConnectedToController {
    private final ConnectedToService connectedToService;

    /**
     * Construtor para criar um novo HUBController com dependências injetadas.
     * @param connectedToService O serviço para o gerenciamento de tanques.
     */

    // Injeção de dependências dos serviços HUBService e TankService
    public ConnectedToController(ConnectedToService connectedToService) {
        this.connectedToService = connectedToService;
    }

    /**
     * Endpoint para recuperar todos os HUBs.
     *
     * @return Uma lista de todas as conexoes.
     */

    // Endpoint para obter todos os HUBs
    @GetMapping("/getAll")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<List<GetAllConnectionsQueryResult>> getAllConnections(){
        return new ResponseEntity<List<GetAllConnectionsQueryResult>>(connectedToService.getAllConnections(), HttpStatus.OK);
    }
}
