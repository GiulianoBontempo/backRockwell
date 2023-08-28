package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para deletar uma aresta
public class DeletarArestaRequest {
    private String name1; // Nome do primeiro elemento
    private String name2; // Nome do segundo elemento

    // Método getter para obter o nome do primeiro elemento
    public String getName1(){
        return name1;
    }

    // Método getter para obter o nome do segundo elemento
    public String getName2(){
        return name2;
    }

    // Método setter para definir o nome do primeiro elemento
    public void setName1(String name1) {
        this.name1 = name1;
    }

    // Método setter para definir o nome do segundo elemento
    public void setName2(String name2) {
        this.name2 = name2;
    }
}
