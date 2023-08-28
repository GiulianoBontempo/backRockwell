package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo HUB
public class CreateHUBRequest {

    private String name; // Nome do novo HUB
    
    // Construtor padrão vazio
    public CreateHUBRequest() {
    }

    // Método getter para obter o nome do novo HUB
    public String getName() {
        return name;
    }

    // Método setter para definir o nome do novo HUB
    public void setName(String name) {
        this.name = name;
    }
}
