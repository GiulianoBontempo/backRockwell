package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar itens
public class ConectarItemRequest {
    private String name1; // Nome do primeiro item
    private String name2; // Nome do segundo item

    // Método getter para obter o nome do primeiro item
    public String getName1() {
        return name1;
    }

    // Método getter para obter o nome do segundo item
    public String getName2() {
        return name2;
    }

    // Método setter para definir o nome do primeiro item
    public void setName1(String name1) {
        this.name1 = name1;
    }

    // Método setter para definir o nome do segundo item
    public void setName2(String name2) {
        this.name2 = name2;
    }
}
