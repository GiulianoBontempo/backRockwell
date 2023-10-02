package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo hub (central)
public class HubRequest {
    private String hubName;

    // Método para obter o nome do hub
    public String getHubName() {
        return hubName;
    }

    // Método para definir o nome do hub
    public void setHubName(String hubName) {
        this.hubName = hubName;
    }
}
