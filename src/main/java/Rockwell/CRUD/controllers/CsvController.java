package Rockwell.CRUD.controllers;

import java.io.InputStreamReader;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import Rockwell.CRUD.services.CsvService;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/csv")
public class CsvController {
    private final CsvService csvService;

    /**
     * Construtor para criar um novo HUBController com dependências injetadas.
     * @param csvService O serviço para o gerenciamento de tanques.
     */

    // Injeção de dependências dos serviços HUBService e TankService
    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

     /**
     * Endpoint para criar um novo HUB.
     *
     * @param csvFile O pedido contendo informações para criar um novo HUB.
     * @return ResponseEntity<String> com feedback da operação.
     */

    // Endpoint para criar um novo HUB
    @PostMapping("/createNodes")
    public ResponseEntity<String> createNodes(@RequestParam("file") MultipartFile file){
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
                List<String[]> csvData = csvReader.readAll();
                csvService.createNodes(csvData); // Chama classe para criar os nós
                csvReader.close();
                return ResponseEntity.ok("Upload de CSV bem-sucedido e nós adicionados com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }

    /**
     * Endpoint para criar um novo HUB.
     *
     * @param csvFile O pedido contendo informações para criar um novo HUB.
     * @return ResponseEntity<String> com feedback da operação.
     */

    // Endpoint para criar um novo HUB
    @PostMapping("/createEdges")
    public ResponseEntity<String> createEdges(@RequestParam("file") MultipartFile file){
        try {
            // Verificação se o arquivo é csv e chamando o processamento
            if (file.getContentType().equals("text/csv")) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
                List<String[]> csvData = csvReader.readAll();
                csvService.createEdges(csvData); // Chama classe para criar os nós
                csvReader.close();
                return ResponseEntity.ok("Upload de CSV bem-sucedido e arestas criadas com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não é um CSV válido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o processamento do CSV.");
        }
    }
}
