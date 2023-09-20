package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar um HUB a um Item
public class HubToEntradaESaidaRequest {
    
    private String hubName;  // Nome do HUB
    private String entradaESaidaName; // Nome do Item

    // Construtor padrão vazio
    public HubToEntradaESaidaRequest() {
    }

    // Método getter para obter o nome do HUB
    public String getHubName() {
        return hubName;
    }

    // Método setter para definir o nome do HUB
    public void setName(String hubName) {
        this.hubName = hubName;
    }

    // Método getter para obter o nome do Item
    public String getEntradaESaidaName() {
        return entradaESaidaName;
    }

    // Método setter para definir o nome do Item
    public void setEntradaESaidaName(String entradaESaidaName) {
        this.entradaESaidaName = entradaESaidaName;
    }
}
