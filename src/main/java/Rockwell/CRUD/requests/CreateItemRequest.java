package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo item
public class CreateItemRequest {
    private String name; // Nome do novo item

    // Construtor padrão vazio
    public CreateItemRequest(){

    }

    // Método getter para obter o nome do novo item
    public String getName(){
        return name;
    }

    // Método setter para definir o nome do novo item
    public void setName(String name){
        this.name = name;
    }
}
