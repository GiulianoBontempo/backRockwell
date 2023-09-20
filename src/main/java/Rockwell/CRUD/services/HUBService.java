package Rockwell.CRUD.services;

// Importações das classes e anotações necessárias
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.repositories.HUBRepository;
import Rockwell.CRUD.requests.CreateHUBRequest;

// Anotação que marca a classe como um serviço gerenciado pelo Spring
@Service
public class HUBService {
    private final HUBRepository hubRepository;
    
    /**
     * Construtor para injeção de dependência do repositório HUBRepository.
     *
     * @param hubRepository O repositório responsável pela persistência dos HUBs.
     */
    public HUBService(HUBRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    /**
     * Método para recuperar todos os HUBs.
     *
     * @return Uma lista de todos os HUBs.
     */
    public List<HUB> getAllHubs(){
        return hubRepository.findAll();
    }

     /**
     * Método para recuperar um HUB pelo nome.
     *
     * @param name O nome do HUB.
     * @return O HUB correspondente.
     */
    public HUB getHubByName(String name){
        return hubRepository.findHUBByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name));
    }

    /**
     * Método para criar um novo HUB.
     *
     * @param request Objeto contendo as informações necessárias para criar um novo HUB.
     * @return O HUB criado.
     */
    public HUB createHub(CreateHUBRequest request){
        HUB hub = new HUB();

        hub.setName(request.getName());
        hub.setPositionX(request.getPositionX());
        hub.setPositionY(request.getPositionY());

        hubRepository.save(hub);

        return hub;
    }

     /**
     * Método para deletar um HUB pelo nome.
     *
     * @param name O nome do HUB a ser deletado.
     */
    public void deleteHub(String name){
        HUB hub = getHubByName(name);

        hubRepository.delete(hub);
    }

    /**
     * Método para verificar se um HUB está conectado a um tanque.
     *
     * @param hubName O nome do HUB.
     * @param tankNumber O número do tanque.
     * @return Verdadeiro se o HUB estiver conectado ao tanque, falso caso contrário.
     */
    public Boolean checkIfConnectedToTank(String hubName, int tankNumber){
        return hubRepository.checkIfConnectedToTank(hubName, tankNumber);
    }

    /**
     * Método para criar uma conexão entre um HUB e um tanque.
     *
     * @param hubName O nome do HUB.
     * @param tankNumber O número do tanque.
     */
    public void createConnectionToTank(String hubName, int tankNumber){
        hubRepository.createConnectionToTank(hubName, tankNumber);
    }


    /**
     * Método para verificar se um HUB está conectado a um EntradaESaida.
     *
     * @param hubName O nome do HUB.
     * @param entradaESaidaName O nome do EntradaESaida.
     * @return Verdadeiro se o HUB estiver conectado ao EntradaESaida, falso caso contrário.
     */
    public Boolean checkIfConnectedToEntradaESaida(String hubName, String entradaESaidaName){
        return hubRepository.checkIfConnectedToEntradaESaida(hubName, entradaESaidaName);
    }

    /**
     * Método para criar uma conexão entre um HUB e um EntradaESaida.
     *
     * @param hubName O nome do HUB.
     * @param entradaESaidaName O nome do EntradaESaida.
     */
    public void createConnectionToEntradaESaida(String hubName, String entradaESaidaName){
        hubRepository.createConnectionToEntradaESaida(hubName, entradaESaidaName);
    }

    /**
     * Método para verificar se dois HUBs estão conectados.
     *
     * @param startHubName O nome do HUB inicial.
     * @param endHubName O nome do HUB final.
     * @return Verdadeiro se os HUBs estiverem conectados, falso caso contrário.
     */
    public Boolean checkIfConnectedToHub(String startHubName, String endHubName){
        return hubRepository.checkIfConnectedToHub(startHubName, endHubName);
    }

    /**
     * Método para criar uma conexão entre dois HUBs.
     *
     * @param startHubName O nome do HUB inicial.
     * @param endHubName O nome do HUB final.
     */
    public void createConnectionToHub(String startHubName, String endHubName){
        hubRepository.createConnectionToHub(startHubName, endHubName);
    }

    /**
     * Método para deletar uma conexão entre um HUB e um tanque.
     *
     * @param hubName O nome do HUB.
     * @param tankNumber O número do tanque.
     */
    public void deleteConnectionToTank(String hubName, int tankNumber){
        hubRepository.deleteConnectionToTank(hubName, tankNumber);
    }

     /**
     * Método para deletar uma conexão entre um HUB e um EntradaESaida.
     *
     * @param hubName O nome do HUB.
     * @param entradaESaidaName O nome do EntradaESaida.
     */
    public void deleteConnectionToEntradaESaida(String hubName, String entradaESaidaName){
        hubRepository.deleteConnectionToEntradaESaida(hubName, entradaESaidaName);
    }

    /**
     * Método para deletar uma conexão entre dois HUBs.
     *
     * @param startHubName O nome do HUB inicial.
     * @param endHubName O nome do HUB final.
     */

    public void deleteConnectionToHub(String startHubName, String endHubName){
        hubRepository.deleteConnectionToHub(startHubName, endHubName);
    }

    public HUB updateHubPosition(String name, Integer positionX, Integer positionY){
        HUB hub = getHubByName(name);
        
        if (positionX != null) {
            hub.setPositionX(positionX);
        }
        
        if (positionY != null) {
            hub.setPositionY(positionY);
        }
        
        hubRepository.save(hub);
    
        return hub;
    }
    

}
