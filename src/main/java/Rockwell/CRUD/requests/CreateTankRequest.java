package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo tanque
public class CreateTankRequest {
    private int number; // Número do novo tanque

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
}
