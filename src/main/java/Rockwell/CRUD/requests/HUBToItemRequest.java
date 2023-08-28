package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar um HUB a um Item
public class HUBToItemRequest {
    
    private String hubName;  // Nome do HUB
    private String itemName; // Nome do Item

    // Construtor padrão vazio
    public HUBToItemRequest() {
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
    public String getItemName() {
        return itemName;
    }

    // Método setter para definir o nome do Item
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
