package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo tanque
public class CreateTankRequest {
    private int number; // Número do novo tanque
    private Integer positionX;
    private Integer positionY; 

    // Construtor padrão vazio
    public CreateTankRequest(){
    }

    // Método getter para obter o número do novo tanque
    public int getNumber(){
        return number;
    }

    // Método setter para definir o número do novo tanque
    public void setNumber(int number){
        this.number = number;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void setPositionX(int positionX){
        this.positionX = positionX;
    }

    public void setPositionY(int positionY){
        this.positionY = positionY;
    }
    
}
