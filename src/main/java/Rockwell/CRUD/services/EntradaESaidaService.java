package Rockwell.CRUD.services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.EntradaESaida;
import Rockwell.CRUD.repositories.EntradaESaidaRepository;
import Rockwell.CRUD.requests.CreateEntradaESaidaRequest;


@Service
public class EntradaESaidaService {


    @Autowired
    private EntradaESaidaRepository entradaESaidaRepository;


    /**
     * Método para recuperar todas as entradas e saidas
     *
     * @return Uma lista iterável de todas as entradas e saidas.
     */
    public Iterable<EntradaESaida> getAllEntradasOuSaidas() {


        return entradaESaidaRepository.findAll();
    }


    public EntradaESaida getEntradaESaidaByName(String name) {
        return entradaESaidaRepository.findEntradaOuSaidaByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


     /**
     * Método para criar uma nova entrada ou saida.
     *
     * @param request Objeto contendo as informações necessárias para criar uma nova entrada ou saida.
     * @return O a entrada ou saida criadas.


        */
    public EntradaESaida createEntradaOuSaida(CreateEntradaESaidaRequest request) {
        EntradaESaida EntradaESaida = new EntradaESaida();
        EntradaESaida.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        EntradaESaida.setName(request.getName());
        EntradaESaida.setPositionX(request.getPositionX());
        EntradaESaida.setPositionY(request.getPositionY());
        return entradaESaidaRepository.save(EntradaESaida);
    }


     /**
     * Método para deletar todas as entradas e saidas.
     */
    public void deleteAllEntradasOuSaidas() {
        entradaESaidaRepository.deleteAll();
    }

    public void deleteByName(String entradaESaidaName) {
        entradaESaidaRepository.deleteByName(entradaESaidaName);
    }


    /**
     * Método para conectar dois entradas ou saidas pelo nome.
     *
     * @param name1 O nome do primeiro item a ser conectado.
     * @param name2 O nome do segundo item a ser conectado.
     */
    public void connectByName(String startItem, String endItem){
        entradaESaidaRepository.createConnection(startItem, endItem);
    }


    /**
     * Método para deletar a conexão entre duas entradas ou saidas pelo nome.
     *
     * @param name1 O nome do primeiro item da conexão a ser deletada.
     * @param name2 O nome do segundo item da conexão a ser deletada.
     */
    public void deleteConnection(String startItem, String endItem){
        entradaESaidaRepository.deleteConnectionByName(startItem, endItem);
    }

    // connect to tank
    public void connectEntradaESaidaToTank(String entradaESaidaName, Integer tankNumber){
        entradaESaidaRepository.connectEntradaESaidaToTank(entradaESaidaName, tankNumber);
    }


    /**
     * Método para conectar um item a um HUB pelo nome.
     *
     * @param name1 O nome do item a ser conectado.
     * @param name2 O nome do HUB ao qual o item será conectado.
     */
    public void connectToHub(String entradaESaidaName, String hubName) {
        entradaESaidaRepository.connectToHub(entradaESaidaName, hubName);
    }

    public void connectEntradaESaidaToValve(String entradaESaidaName, String valveName) {
        entradaESaidaRepository.connectEntradaESaidaToValve(entradaESaidaName, valveName);
    }

    public void deleteConnectionToValve(String entradaESaidaName, String valveName) {
        entradaESaidaRepository.deleteConnectionToValve(entradaESaidaName, valveName);
    }

    public void deleteConnectionToHub(String entradaESaidaName, String hubName) {
        entradaESaidaRepository.deleteConnectionToHub(entradaESaidaName, hubName);
    }


    public void deleteConnectionToTank(String entradaESaidaName, int tankNumber) {
        entradaESaidaRepository.deleteConnectionToTank(entradaESaidaName, tankNumber);
    }

    public EntradaESaida updateEntradaESaidaPosition(String name, Integer positionX, Integer positionY){
        EntradaESaida entradaESaida = getEntradaESaidaByName(name);
    
        if (positionX != null) {
            entradaESaida.setPositionX(positionX);
        }
    
        if (positionY != null) {
            entradaESaida.setPositionY(positionY);
        }
    
        entradaESaidaRepository.save(entradaESaida);
    
        return entradaESaida;
    }
    
}
