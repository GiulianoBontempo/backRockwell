package Rockwell.CRUD.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.Valve;
import Rockwell.CRUD.repositories.ValveRepository;
import Rockwell.CRUD.requests.CreateValveRequest;

@Service
public class ValveService {
    private final ValveRepository valveRepository;

    public ValveService(ValveRepository valveRepository) {
        this.valveRepository = valveRepository;
    }

    // Retorna uma lista de todas as válvulas
    public List<Valve> getAllValves() {
        return valveRepository.findAll();
    }

    /**
     * Método para recuperar uma válvula pelo nome.
     * @param name O nome da válvula a ser recuperada.
     * @return A válvula correspondente.
     */
    public Valve getValveByName(String name) {
        return valveRepository.findValveByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name));
    }


   /**
    * Método para deletar todas as válvulas.
    */
    public void deleteAllValves() {
        valveRepository.deleteAll();
    }

    /**
     * Método para deletar uma válvula pelo nome.
     * @param valveName O nome da válvula a ser deletada.
     */
    public void deleteValveByName(String valveName) {
        valveRepository.deleteValveByName(valveName);
    }


    /**
     * Método para criar uma nova válvula.
     * @param request Objeto contendo as informações necessárias para criar uma nova válvula.
     * @return A válvula criada.
     */
    public Valve createValve(CreateValveRequest request) {
        Valve valve = new Valve();
        valve.setName(request.getName());
        valve.setPositionX(request.getPositionX());
        valve.setPositionY(request.getPositionY());
        return valveRepository.save(valve);
    }

    /**
     * Método para deletar uma válvula pelo nome.
     * @param name O nome da válvula a ser deletada.
     */
    public void deleteValve(String name) {
        valveRepository.deleteValveByName(name);
    }

    /**
     * metodo para conectar duas válvulas pelo nome
     * @param valveName nome da válvula
     * @param valveName2 nome da válvula
     * 
     */
    public void connectToValve(String valveName, String valveName2) {
        valveRepository.connectToValve(valveName, valveName2);
    }

    /**
     * Método para conectar uma válvula a um tanque pelo número do tanque
     * @param valveName nome da válvula
     * @param tankNumber número do tanque
     */
    public void connectToTank(String valveName, int tankNumber) {
        valveRepository.connectToTank(valveName, tankNumber);
    }

    /**
     * Método para conectar uma válvula a uma Entrada/Saída pelo nome
     * @param valveName nome da válvula
     * @param entradaESaidaName nome da Entrada/Saída
     */
    public void connectToEntradaESaida(String valveName, String entradaESaidaName) {
        valveRepository.connectToEntradaESaida(valveName, entradaESaidaName);
    }

    /**
     * Método para excluir a conexão de uma válvula com um tanque
     * @param valveName nome da válvula
     * @param tankNumber número do tanque
     */
    public void deleteConnectionToTank(String valveName, int tankNumber) {
        valveRepository.deleteConnectionToTank(valveName, tankNumber);
    }

    /**
     * Método para excluir a conexão de uma válvula com uma Entrada/Saída
     * @param valveName nome da válvula
     * @param entradaESaidaName nome da Entrada/Saída
     */
    public void deleteConnectionToEntradaESaida(String valveName, String entradaESaidaName) {
        valveRepository.deleteConnectionToEntradaESaida(valveName, entradaESaidaName);
    }

    /**
     * Método para excluir a conexão de uma válvula com outra válvula
     * @param valveName nome da válvula
     * @param valveName2 nome da válvula
     */
    public void deleteConnectionToValve(String valveName, String valveName2) {
        valveRepository.deleteConnectionToValve(valveName, valveName2);
    }

    /**
     * Método para conectar uma válvula a um hub
     * @param valveName nome da válvula
     * @param hubName nome do hub
     */
    public void connectToHub(String valveName, String hubName) {
        valveRepository.connectToHub(valveName, hubName);
    }

    /**
     * Método para excluir a conexão de uma válvula com um hub
     * @param valveName nome da válvula
     * @param hubName nome do hub
     */
    public void deleteConnectionToHub(String valveName, String hubName) {
        valveRepository.deleteConnectionToHub(valveName, hubName);
    }

    /**
     * Método para atualizar o nome de uma válvula
     * @param currentName nome atual da válvula
     * @param newName novo nome da válvula
     * @return válvula com o nome atualizado
     */
    public Valve updateName(String currentName, String newName) {
        return valveRepository.updateName(currentName, newName);
    }
}