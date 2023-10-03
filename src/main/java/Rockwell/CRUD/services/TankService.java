package Rockwell.CRUD.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Rockwell.CRUD.models.Tank;
import Rockwell.CRUD.repositories.TankRepository;
import Rockwell.CRUD.requests.CreateTankRequest;

// Anotação que marca a classe como um serviço gerenciado pelo Spring
@Service
public class TankService {
    private final TankRepository tankRepository;

    /**
     * Construtor para injeção de dependência do repositório TankRepository.
     *
     * @param tankRepository O repositório responsável pela persistência de tanques.
     */
    // Injeção de dependência do repositório TankRepository
    public TankService(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    /**
     * Método para recuperar todos os tanques associados a um determinado HUB pelo nome.
     *
     * @param name O nome do HUB.
     * @return Uma lista de tanques associados ao HUB.
     */
    public List<Tank> getAllTanksByHUBName(String name){
        return tankRepository.findTanksByHubName(name);
    }

    /**
     * Método para recuperar todos os tanques.
     *
     * @return Uma lista de todos os tanques.
     */
    public List<Tank> getAllTanks(){
        return tankRepository.findAll();
    }

    /**
     * Método para recuperar um tanque pelo número.
     *
     * @param number O número do tanque.
     * @return O tanque correspondente.
     */
    public Tank getTankByNumber(int number){
        return tankRepository.findTankByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

     /**
     * Método para criar um novo tanque.
     *
     * @param request Objeto contendo as informações necessárias para criar um novo tanque.
     * @return O tanque criado.
     */
    public Tank createTank(CreateTankRequest request){
        Tank tank = new Tank();
        tank.setNumber(request.getNumber());
        tankRepository.save(tank);
        return tank;
    }

    /**
     * Método para deletar um tanque pelo número.
     *
     * @param number O número do tanque a ser deletado.
     */
    public void deleteTank(int number){
        Tank tank = getTankByNumber(number);

        tankRepository.delete(tank);
    }

     /**
     * Método para conectar dois tanques pelo número.
     *
     * @param startTankNumber O número do primeiro tanque a ser conectado.
     * @param endTankNumber O número do segundo tanque a ser conectado.
     */
    public void connectByNumber(Integer startTankNumber, Integer endTankNumber) {
        tankRepository.createConnection(startTankNumber, endTankNumber);
    }
    
    /**
     * Método para deletar uma conexão entre dois tanques pelo número.
     *
     * @param startTankNumber O número do primeiro tanque da conexão a ser deletada.
     * @param endTankNumber O número do segundo tanque da conexão a ser deletada.
     */
    public void deleteConnection(Integer startTankNumber, Integer endTankNumber){
        tankRepository.deleteConnection(startTankNumber, endTankNumber);
    }

    /**
     * Método para conectar um tanque a um EntradaESaida.
     *
     * @param tankNumber O número do tanque a ser conectado.
     * @param entradaESaidaName O nome do EntradaESaida ao qual o tanque será conectado.
     */
    public void connectTankToEntradaESaida(Integer tankNumber, String entradaESaidaName){
        tankRepository.connectTankToEntradaESaida(tankNumber, entradaESaidaName);
   }

   /**
     * Método para deletar uma conexão entre um tanque e um EntradaESaida.
     *
     * @param tankNumber O número do tanque cuja conexão com o EntradaESaida será deletada.
     * @param entradaESaidaName O nome do EntradaESaida cuja conexão com o tanque será deletada.
     */
   public void deleteTankEntradaESaidaConnection(Integer tankNumber, String entradaESaidaName){
    tankRepository.deleteTankEntradaESaidaConnection(tankNumber, entradaESaidaName);
   }

   /**
     * Método para conectar um tanque a um HUB.
     *
     * @param tankNumber O número do tanque a ser conectado.
     * @param hubName O nome do HUB ao qual o tanque será conectado.
     */
    public void connectToHub(int tankNumber, String hubName) {
        tankRepository.connectToHub(tankNumber, hubName);
    }

     /**
     * Método para deletar uma conexão entre um tanque e um HUB.
     *
     * @param tankNumber O número do tanque cuja conexão com o HUB será deletada.
     * @param hubName O nome do HUB cuja conexão com o tanque será deletada.
     */
    public void deleteConnectionToHub(int tankNumber, String hubName) {
        tankRepository.deleteConnectionToHub(tankNumber, hubName);
    }

    /**
     * Método para conectar um tanque a uma válvula.
     * @param tankNumber O número do tanque a ser conectado.
     * @param valveName O nome da válvula ao qual o tanque será conectado.
     */
    public void connectTankToValve(int tankNumber, String valveName) {
        tankRepository.connectTankToValve(tankNumber, valveName);
    }

    /**
     * Método para deletar uma conexão entre um tanque e uma válvula.
     * @param tankNumber O número do tanque cuja conexão com a válvula será deletada.
     * @param valveName O nome da válvula cuja conexão com o tanque será deletada.
     */
    public void deleteConnectionToValve(int tankNumber, String valveName) {
        tankRepository.deleteConnectionToValve(tankNumber, valveName);
    }

    /**
     * Método para atualizar o nome de um tanque.
     * @param currentName O nome atual do tanque.
     * @param newName O novo nome do tanque.
     * @return O tanque com o nome atualizado.
     */
    public Tank updateNumber(long currentNumber, long newNumber) {
        return tankRepository.updateNumber(currentNumber, newNumber);
    }
    

    /**
     * Método para atualizar a posição de um tanque.
     * @param number O número do tanque.
     * @param positionX A nova posição X do tanque.
     * @param positionY A nova posição Y do tanque.
     * @return O tanque com a posição atualizada.
     */
    public Tank updateTankPosition(int number, Integer positionX, Integer positionY){
        Tank tank = getTankByNumber(number); 
        
        if (positionX != null) {
            tank.setPositionX(positionX);
        }
        
        if (positionY != null) {
            tank.setPositionY(positionY);
        }
        
        tankRepository.save(tank); 
        
        return tank;
    }
    
    

}
