package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para conectar um HUB a um tanque (Tank)
public class HUBToTankRequest {

    private String name; // Nome do HUB
    private int number;  // Número do tanque (Tank)

    // Construtor padrão vazio
    public HUBToTankRequest() {
    }

    // Método getter para obter o nome do HUB
    public String getName() {
        return name;
    }

    // Método setter para definir o nome do HUB
    public void setName(String name) {
        this.name = name;
    }

    // Método getter para obter o número do tanque (Tank)
    public int getNumber() {
        return number;
    }

    // Método setter para definir o número do tanque (Tank)
    public void setNumber(int number) {
        this.number = number;
    }
}
