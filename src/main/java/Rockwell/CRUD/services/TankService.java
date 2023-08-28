package Rockwell.CRUD.services;

// Importações das classes e anotações necessárias
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

    // Injeção de dependência do repositório TankRepository
    public TankService(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    // Métodos de serviço relacionados aos tanques
    public List<Tank> getAllTanksByHUBName(String name){
        return tankRepository.findTanksByHubName(name);
    }

    public List<Tank> getAllTanks(){
        return tankRepository.findAll();
    }

    public Tank getTankByNumber(int number){
        return tankRepository.findTankByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Tank createTank(CreateTankRequest request){
        Tank tank = new Tank();

        tank.setNumber(request.getNumber());

        tankRepository.save(tank);

        return tank;
    }

    public void deleteTank(int number){
        Tank tank = getTankByNumber(number);

        tankRepository.delete(tank);
    }
}
