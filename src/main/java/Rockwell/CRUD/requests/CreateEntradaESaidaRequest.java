package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo item
public class CreateEntradaESaidaRequest {
    private String name;
    private int posicaoX;
    private int posicaoY;

    // Construtor padrão da classe CreateEntradaESaidaRequest
    public CreateEntradaESaidaRequest(){

    }

    // Método para obter o nome do item
    public String getName(){
        return name;
    }

    // Método para obter a posição X do item
    public int getPositionX(){
        return posicaoX;
    }

    // Método para obter a posição Y do item
    public int getPositionY(){
        return posicaoY;
    }

    // Método para definir o nome do item
    public void setName(String name){
        this.name = name;
    }
    
    // Método para definir a posição X do item
    public void setPositionX(int posicaoX){
        this.posicaoX = posicaoX;
    }

    // Método para definir a posição Y do item
    public void setPositionY(int posicaoY){
        this.posicaoY = posicaoY;
    }
}
