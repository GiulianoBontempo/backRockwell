package Rockwell.CRUD.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.models.Valve;
import Rockwell.CRUD.requests.CreateValveRequest;
import Rockwell.CRUD.services.ValveService;

/**
 * Controlador para operações relacionadas a válvulas (Valve).
 */
@RestController
@RequestMapping("/api/v1/valves")
public class ValveController {

    /**
     * Serviço de válvulas.
     */
    private final ValveService valveService;

    /**
     * Construtor da classe ValveController.
     *
     * @param valveService O serviço de válvulas a ser injetado.
     */
    public ValveController(ValveService valveService){
        this.valveService = valveService;
    }

    /**
     * Obtém todas as válvulas.
     *
     * @return Um ResponseEntity contendo a lista de todas as válvulas encontradas.
     */
    @GetMapping("/")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<List<Valve>> getAllValves(){
        return new ResponseEntity<List<Valve>>(valveService.getAllValves(), HttpStatus.OK);
    }

    /**
     * Obtém uma válvula pelo nome.
     *
     * @param name O nome da válvula a ser recuperada.
     * @return Um ResponseEntity contendo a válvula encontrada.
     */
    @GetMapping("/{name}")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<Valve> getValveByName(@PathVariable String name){
        return new ResponseEntity<Valve>(valveService.getValveByName(name), HttpStatus.OK);
    }

    /**
     * Cria uma nova válvula.
     *
     * @param request O objeto de requisição contendo os detalhes da nova válvula.
     * @return A válvula criada.
     */
    @PostMapping("/create")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public Valve createValve(@RequestBody CreateValveRequest request){
        return valveService.createValve(request);
    }

    /**
     * Deleta todas as válvulas.
     *
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/delete")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteAllValves(){
        valveService.deleteAllValves();
        return new ResponseEntity<>("Valve deletada", HttpStatus.OK);
    }

    /**
     * Deleta uma válvula pelo nome.
     *
     * @param deleteMap Um mapa que contém o nome da válvula a ser deletada.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/deleteValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })

    public ResponseEntity<String> deleteValveByName(@RequestBody Map<String, String> deleteMap) {
        valveService.deleteValveByName(deleteMap.get("name"));
        return new ResponseEntity<>("Valve deletada", HttpStatus.OK);
    }    

    /**
     * Conecta uma válvula a outra entidade (valvula, tanque, entrada/saída, hub).
     *
     * @param connectMap Um mapa que contém as informações de conexão, incluindo os nomes das entidades a serem conectadas.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @PostMapping("/connectToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToValve(@RequestBody Map<String,String> connectMap){
        valveService.connectToValve(connectMap.get("name"), connectMap.get("name2"));
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    /**
     * Deleta uma conexão entre uma válvula e outra entidade (valvula, tanque, entrada/saída, hub).
     *
     * @param deleteMap Um mapa que contém as informações de conexão a serem excluídas, incluindo os nomes das entidades a serem desconectadas.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/deleteConnectionToValve")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValve(@RequestBody Map<String,String> deleteMap){
        valveService.deleteConnectionToValve(deleteMap.get("name"), deleteMap.get("name2"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    /**
     * Conecta uma válvula a um tanque especificado.
     *
     * @param connectMap Um mapa que contém as informações de conexão, incluindo o nome da válvula e o número do tanque.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @PostMapping("/connectToTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToTank(@RequestBody Map<String, Object> connectMap){
        String valveName = (String) connectMap.get("valve");
        Integer tankName = (Integer) connectMap.get("tank");
        valveService.connectToTank(valveName, tankName);
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    /**
     * Deleta uma conexão entre uma válvula e um tanque especificado.
     *
     * @param deleteMap Um mapa que contém as informações de conexão a serem excluídas, incluindo o nome da válvula e o número do tanque.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/deleteValveTank")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValveToTank(@RequestBody Map<String, Object> deleteMap){
        String valveName = (String) deleteMap.get("valve");
        Integer tankName = (Integer) deleteMap.get("tank");
        valveService.deleteConnectionToTank(valveName, tankName);
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    /**
     * Conecta uma válvula a uma entrada/saída especificada.
     *
     * @param connectMap Um mapa que contém as informações de conexão, incluindo o nome da válvula e o nome da entrada/saída.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @PostMapping("/connectToEntradaESaida")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToEntradaESaida(@RequestBody Map<String, String> connectMap){
        valveService.connectToEntradaESaida(connectMap.get("valve"), connectMap.get("entradaESaida"));
        return new ResponseEntity<>("Conexão criada", HttpStatus.OK);
    }

    /**
     * Deleta uma conexão entre uma válvula e uma entrada/saída especificada.
     *
     * @param deleteMap Um mapa que contém as informações de conexão a serem excluídas, incluindo o nome da válvula e o nome da entrada/saída.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/deleteValveEntradaESaida")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteValveToEntradaESaida(@RequestBody Map<String, String> deleteMap){
        valveService.deleteConnectionToEntradaESaida(deleteMap.get("valve"), deleteMap.get("entradaESaida"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    /**
     * Conecta uma válvula a um HUB especificado.
     *
     * @param connectionMap Um mapa que contém as informações de conexão, incluindo o nome da válvula e o nome do HUB.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @PostMapping("/connectToHub")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> connectToHub(@RequestBody Map<String, String> connectionMap) {
        valveService.connectToHub(connectionMap.get("valveName"), connectionMap.get("hubName"));
        return new ResponseEntity<>("Valve conectada ao HUB", HttpStatus.OK);
    }

    /**
     * Deleta uma conexão entre uma válvula e um HUB especificado.
     *
     * @param connectionMap Um mapa que contém as informações de conexão a serem excluídas, incluindo o nome da válvula e o nome do HUB.
     * @return Um ResponseEntity com uma mensagem de confirmação.
     */
    @DeleteMapping("/deleteConnectionToHub")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteConnectionToHub(@RequestBody Map<String, String> connectionMap) {
        valveService.deleteConnectionToHub(connectionMap.get("valveName"), connectionMap.get("hubName"));
        return new ResponseEntity<>("Conexão entre Valve e HUB deletada", HttpStatus.OK);
    }


       /**
    * Atualiza o nome de uma Valve.
    *
    * @param updateMap Um mapa que contém o nome atual da Valve e o novo nome da Valve.
    * @return Um ResponseEntity com a Valve atualizada.
    */
    @PatchMapping("/updateName")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<Valve> updateName(@RequestBody Map<String, String> updateMap) {
        String currentName = updateMap.get("currentName");
        String newName = updateMap.get("newName");
        Valve updatedValve = valveService.updateName(currentName, newName);
        return new ResponseEntity<>(updatedValve, HttpStatus.OK);
    }

}