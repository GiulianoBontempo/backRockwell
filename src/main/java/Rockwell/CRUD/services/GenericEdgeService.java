package Rockwell.CRUD.services;

import org.springframework.stereotype.Service;

@Service
public class GenericEdgeService {
    private final HUBService hubService;
    private final TankService tankService;
    private final EntradaESaidaService entradaESaidaService;
    private final ValveService valveService;

    /**
     * Construtor para criar um novo HUBController com dependências injetadas.
     *
     * @param hubService O serviço para o gerenciamento de HUBs.
     * @param tankService O serviço para o gerenciamento de tanques.
     * @param entradaESaidaService O serviço para o gerenciamento de tanques.
     * @param valveService
     */
    public GenericEdgeService(HUBService hubService, TankService tankService, EntradaESaidaService entradaESaidaService, ValveService valveService) {
        this.hubService = hubService;
        this.tankService = tankService;
        this.entradaESaidaService = entradaESaidaService;
        this.valveService = valveService;
    }

    public void createEdges(String typeStart, String nameOrNumberStart, String typeEnd, String nameOrNumberEnd){
        switch(typeStart){
            case "HUB":
            switch(typeEnd){
                case "HUB":
                hubService.createConnectionToHub(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "EntradaESaida":
                hubService.createConnectionToEntradaESaida(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "Tank":
                hubService.connectToTank(nameOrNumberStart, Integer.parseInt(nameOrNumberEnd));
                break;

                case "Valve":
                hubService.connectHUBToValve(nameOrNumberStart, nameOrNumberEnd);
                break;
            }
            break;

            case "EntradaESaida":
            switch(typeEnd){
                case "HUB":
                entradaESaidaService.connectToHub(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "EntradaESaida":
                entradaESaidaService.connectByName(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "Valve":
                entradaESaidaService.connectEntradaESaidaToValve(nameOrNumberStart, nameOrNumberEnd);
                break;
            }
            break;

            case "Tank":
            switch(typeEnd){
                case "HUB":
                tankService.connectToHub(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                break;

                case "EntradaESaida":
                tankService.connectTankToEntradaESaida(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                break;

                case "Tank":
                tankService.connectByNumber(Integer.parseInt(nameOrNumberStart), Integer.parseInt(nameOrNumberEnd));
                break;

                case "Valve":
                tankService.connectTankToValve(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                break;
            }
            break;

            case "Valve":
            switch(typeEnd){
                case "Valve":
                valveService.connectToValve(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "HUB":
                valveService.connectToHub(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "EntradaESaida":
                valveService.connectToEntradaESaida(nameOrNumberStart, nameOrNumberEnd);
                break;

                case "Tank":
                valveService.connectToTank(nameOrNumberStart, Integer.parseInt(nameOrNumberEnd));
                break;
            }
            break;
        }

    }

    public void deleteEdges(String typeStart, String nameOrNumberStart, String typeEnd, String nameOrNumberEnd) {
        switch(typeStart) {
            case "HUB":
                switch(typeEnd) {
                    case "HUB":
                        hubService.deleteConnectionToHub(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "EntradaESaida":
                        hubService.deleteConnectionToEntradaESaida(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "Tank":
                        hubService.deleteConnectionToTank(nameOrNumberStart, Integer.parseInt(nameOrNumberEnd));
                        break;
                    case "Valve":
                        hubService.deleteConnectionToValve(nameOrNumberStart, nameOrNumberEnd);
                        break;
                }
                break;

            case "EntradaESaida":
                switch(typeEnd) {
                    case "HUB":
                        entradaESaidaService.deleteConnectionToHub(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "EntradaESaida":
                        entradaESaidaService.deleteConnection(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "Tank":
                        entradaESaidaService.deleteConnectionToTank(nameOrNumberStart, Integer.parseInt(nameOrNumberEnd));
                        break;
                    case "Valve":
                        entradaESaidaService.deleteConnectionToValve(nameOrNumberStart, nameOrNumberEnd);
                        break;
                }
                break;

            case "Tank":
                switch(typeEnd) {
                    case "HUB":
                        tankService.deleteConnectionToHub(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                        break;
                    case "EntradaESaida":
                        tankService.deleteTankEntradaESaidaConnection(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                        break;
                    case "Tank":
                        tankService.deleteConnection(Integer.parseInt(nameOrNumberStart), Integer.parseInt(nameOrNumberEnd));
                        break;
                    case "Valve":
                        tankService.deleteConnectionToValve(Integer.parseInt(nameOrNumberStart), nameOrNumberEnd);
                        break;
                }
                break;

            case "Valve":
                switch(typeEnd) {
                    case "HUB":
                        valveService.deleteConnectionToHub(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "EntradaESaida":
                        valveService.deleteConnectionToEntradaESaida(nameOrNumberStart, nameOrNumberEnd);
                        break;
                    case "Tank":
                        valveService.deleteConnectionToTank(nameOrNumberStart, Integer.parseInt(nameOrNumberEnd));
                        break;
                    case "Valve":
                        valveService.deleteConnectionToValve(nameOrNumberStart, nameOrNumberEnd);
                        break;
                }
                break;
        }
    }

    

    
}
