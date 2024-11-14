package teste;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class Ataque {
    private int dano;
    private int alcance;
    private int velocidade;

    // Getters e Setters
    public int getDano() { return dano; }
    public int getAlcance() { return alcance; }
    public int getVelocidade() { return velocidade; }

    public void setDano(int dano) { this.dano = dano; }
    public void setAlcance(int alcance) { this.alcance = alcance; }
    public void setVelocidade(int velocidade) { this.velocidade = velocidade; }
}

public class Dino {
    private int id;
    private int tamx;
    private int tamy;
    private String img;
    private Map<String, Ataque> ataques;

    @JsonCreator
    private Dino(
        @JsonProperty("id") int id,
        @JsonProperty("tamx") int tamx,
        @JsonProperty("tamy") int tamy,
        @JsonProperty("img") String img
    ) {
        this.id = id;
        this.tamx = tamx;
        this.tamy = tamy;
        this.img = img;
    }

    // @Override
    // public String toString() {
    //     return "Dino{" +
    //             "id=" + id +
    //             ", tamx=" + tamx +
    //             ", tamy=" + tamy +
    //             ", img='" + img + '\'' +
    //             '}';
    // }

    // Construtor que inicializa o Dino com base no ID
    public Dino(int id) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/java/teste/dinos.json");

            // Criar o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Ler o JSON como um Map
            Map<String, Dino> dinos = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Dino.class));

            // Procurar pelo dino com o ID correspondente
            Dino dino = dinos.values().stream()
                    .filter(d -> d.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Dino com ID " + id + " não encontrado!"));

            // Inicializar os atributos do Dino atual com os valores do JSON
            this.id = dino.id;
            this.tamx = dino.tamx;
            this.tamy = dino.tamy;
            this.img = dino.img;
            this.ataques = dino.ataques;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Dino com ID " + id, e);
        }
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getTamx() { return tamx; }
    public int getTamy() { return tamy; }
    public String getImg() { return img; }
    public Map<String, Ataque> getAtaques() { return ataques; }
}