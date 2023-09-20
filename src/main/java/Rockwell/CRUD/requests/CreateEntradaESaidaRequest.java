package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar um novo item
public class CreateEntradaESaidaRequest {
    private String name; 
    private int posicaoX;
    private int posicaoY;

    public CreateEntradaESaidaRequest(){

    }

    public String getName(){
        return name;
    }

    public int getPositionX(){
        return posicaoX;
    }

    public int getPositionY(){
        return posicaoY;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setPositionX(int posicaoX){
        this.posicaoX = posicaoX;
    }

    public void setPositionY(int posicaoY){
        this.posicaoY = posicaoY;
    }
}
