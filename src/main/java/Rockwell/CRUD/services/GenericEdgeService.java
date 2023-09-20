package Rockwell.CRUD.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GenericEdgeService {
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
    public GenericEdgeService(HUBService hubService, TankService tankService, EntradaESaidaService entradaESaidaService) {
        this.hubService = hubService;
        this.tankService = tankService;
        this.entradaESaidaService = entradaESaidaService;
    }

    public void createEdges(String typeStart, String nameOrNumberStart, String typeEnd, String NameOrNumberEnd){
        switch(typeStart){
            case "HUB":
            switch(typeEnd){
                case "HUB":
                hubService.createConnectionToHub(nameOrNumberStart, NameOrNumberEnd);
                break;

                case "EntradaESaida":
                hubService.createConnectionToEntradaESaida(nameOrNumberStart, NameOrNumberEnd);
                break;

                case "Tank":
                hubService.createConnectionToTank(nameOrNumberStart, Integer.parseInt(NameOrNumberEnd));
                break;
            }
            break;

            case "EntradaESaida":
            switch(typeEnd){
                case "HUB":
                entradaESaidaService.connectEntradaOuSaidaToHub(nameOrNumberStart, NameOrNumberEnd);
                break;

                case "EntradaESaida":
                entradaESaidaService.connectByName(nameOrNumberStart, NameOrNumberEnd);
                break;
            }
            break;

            case "Tank":
            switch(typeEnd){
                case "HUB":
                tankService.connectTankToHub(Integer.parseInt(nameOrNumberStart), NameOrNumberEnd);
                break;

                case "EntradaESaida":
                tankService.connectTankToEntradaESaida(Integer.parseInt(nameOrNumberStart), NameOrNumberEnd);
                break;

                case "Tank":
                tankService.connectByNumber(Integer.parseInt(nameOrNumberStart), Integer.parseInt(NameOrNumberEnd));
                break;
            }
            break;
        }

    }
}
