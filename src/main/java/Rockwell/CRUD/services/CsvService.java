package Rockwell.CRUD.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Rockwell.CRUD.requests.CreateEntradaESaidaRequest;
import Rockwell.CRUD.requests.CreateHUBRequest;
import Rockwell.CRUD.requests.CreateTankRequest;

@Service
public class CsvService {
    private final HUBService hubService;
    private final TankService tankService;
    private final EntradaESaidaService entradaESaidaService;

    /**
     * Construtor para criar um novo HUBController com dependências injetadas.
     *
     * @param hubService O serviço para o gerenciamento de HUBs.
     * @param tankService O serviço para o gerenciamento de tanques.
     * @param entradaESaidaService O serviço para o gerenciamento de tanques.
     */
    public CsvService(HUBService hubService, TankService tankService, EntradaESaidaService entradaESaidaService) {
        this.hubService = hubService;
        this.tankService = tankService;
        this.entradaESaidaService = entradaESaidaService;
    }

    public void createNodes(List<String[]> content){
        for(String[] row : content){
            switch(row[0]){
                case "HUB":
                CreateHUBRequest hubRequest = new CreateHUBRequest();
                hubRequest.setName(row[1]);
                hubService.createHub(hubRequest);
                break;

                case "EntradaESaida":
                CreateEntradaESaidaRequest entradaESaidaRequest = new CreateEntradaESaidaRequest();
                entradaESaidaRequest.setName(row[1]);
                entradaESaidaService.createEntradaOuSaida(entradaESaidaRequest);
                break;

                case "Tank":
                CreateTankRequest tankRequest = new CreateTankRequest();
                tankRequest.setNumber(Integer.parseInt(row[2]));
                tankService.createTank(tankRequest);
                break;
            }

        }
    }

    public void createEdges(List<String[]> content){
        for(String[] row : content){
            switch(row[0]){
                case "HUB":
                switch(row[2]){
                    case "HUB":
                    hubService.createConnectionToHub(row[1], row[3]);
                    break;

                    case "EntradaESaida":
                    hubService.createConnectionToEntradaESaida(row[1], row[3]);
                    break;

                    case "Tank":
                    hubService.createConnectionToTank(row[1], Integer.parseInt(row[3]));
                    break;
                }
                break;

                case "EntradaESaida":
                switch(row[2]){
                    case "HUB":
                    entradaESaidaService.connectEntradaOuSaidaToHub(row[1], row[3]);
                    break;

                    case "EntradaESaida":
                    entradaESaidaService.connectByName(row[1], row[3]);
                    break;
                }
                break;

                case "Tank":
                switch(row[2]){
                    case "HUB":
                    tankService.connectTankToHub(Integer.parseInt(row[1]), row[3]);
                    break;

                    case "EntradaESaida":
                    tankService.connectTankToEntradaESaida(Integer.parseInt(row[1]), row[3]);
                    break;

                    case "Tank":
                    tankService.connectByNumber(Integer.parseInt(row[1]), Integer.parseInt(row[3]));
                    break;
                }
                break;
            }

        }
    }
}
