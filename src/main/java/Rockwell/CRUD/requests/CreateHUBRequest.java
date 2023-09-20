package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo HUB
public class CreateHUBRequest {

    private String name;
    private Integer positionX;
    private Integer positionY; // Nome do novo HUB
    
    // Construtor padrão vazio
    public CreateHUBRequest() {
        this.positionX = 0;
        this.positionY = 0;
    }

    // Método getter para obter o nome do novo HUB
    public String getName() {
        return name;
    }

    public Integer getPositionX(){
        return positionX;
    }

    public Integer getPositionY(){
        return positionY;
    }

    // Método setter para definir o nome do novo HUB
    public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(int positionX){
        this.positionX = positionX;
    }

    public void setPositionY(int positionY){
        this.positionY = positionY;
    }
}
