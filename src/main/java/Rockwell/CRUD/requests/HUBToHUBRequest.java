package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar dois HUBs
public class HUBToHUBRequest {
    
    private String startHubName;  // Nome do primeiro HUB
    private String endHubName;    // Nome do segundo HUB

    // Construtor padrão vazio
    public HUBToHUBRequest() {
    }

    // Método getter para obter o nome do primeiro HUB
    public String getStartHubName() {
        return startHubName;
    }

    // Método setter para definir o nome do primeiro HUB
    public void setStartHubName(String startHubName) {
        this.startHubName = startHubName;
    }

    // Método getter para obter o nome do segundo HUB
    public String getEndHubName() {
        return endHubName;
    }

    // Método setter para definir o nome do segundo HUB
    public void setEndHubName(String endHubName) {
        this.endHubName = endHubName;
    }
}
