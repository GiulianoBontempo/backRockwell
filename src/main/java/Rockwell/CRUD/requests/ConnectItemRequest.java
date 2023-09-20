package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar itens
public class ConnectItemRequest {
    private String startItemName; // Nome do primeiro item
    private String endItemName; // Nome do segundo item

    // Método getter para obter o nome do primeiro item
    public String getStartItemName() {
        return startItemName;
    }

    // Método getter para obter o nome do segundo item
    public String getEndItemName() {
        return endItemName;
    }

    // Método setter para definir o nome do primeiro item
    public void setStartItemName(String startItemName) {
        this.startItemName = startItemName;
    }

    // Método setter para definir o nome do segundo item
    public void setEndItemName(String endItemName) {
        this.endItemName = endItemName;
    }
}
