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
    private int tamx, tamy;
    private int esq = 0, dir = 0, whereG = 0;
    private float posx, posy, posyBase; 
    private double dy = 0.0;
    private String img1;
    private String img2;
    private Map<String, Ataque> ataques;

    @JsonCreator
    private Dino(
        @JsonProperty("id") int id,
        @JsonProperty("tamx") int tamx,
        @JsonProperty("tamy") int tamy,
        @JsonProperty("img1") String img1,
        @JsonProperty("img2") String img2
    ) {
        this.id = id;
        this.tamx = tamx;
        this.tamy = tamy;
        this.img1 = img1;
        this.img2 = img2;
    }

    // Construtor que inicializa o Dino com base no ID
    public Dino(int id, float posx, float posy) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/resources/json/dinos.json");

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
            this.img1 = dino.img1;
            this.img2 = dino.img2;
            this.ataques = dino.ataques;
            this.posx = posx;
            this.posy = posy;
            this.posyBase = posy;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Dino com ID " + id, e);
        }
    }

    public void move(){
        if(this.esq == 1){
            this.move_esq();
        }
        if(this.dir == 1){
            this.move_dir();
        }
        gravity();
    }

    private void move_esq(){
        this.posx -= 5;
        if(this.posx < 0){
            this.posx = 0;
        }
    }

    private void move_dir(){
        this.posx += 5;
        if(this.posx > 1000){
            this.posx = 1000;
        }
    }

    private void gravity(){
        this.posy += this.dy;
        this.dy -= 0.1;
        if(this.posy < this.posyBase){
            this.posy = this.posyBase;
            this.dy = 0;
        }
    }

    public void jump(){
        if(this.posy == this.posyBase){
            this.dy = 5;
        }
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getTamx() { return tamx; }
    public int getTamy() { return tamy; }
    public String getImg1() { return img1; }
    public String getImg2() { return img2; }
    public Map<String, Ataque> getAtaques() { return ataques; }
    public Float getPx() { return this.posx; }
    public Float getPy() { return this.posy; }
    
    public int whereGo(){
        if(this.dir == 1){
            this.whereG = 0;
        }
        if(this.esq == 1){
            this.whereG = 1;
        }
        return this.whereG;
    }

    public void changeEsq(){
        if(this.esq == 0){
            this.esq = 1;
        }
        else{
            this.esq = 0;
        }
    }
    
    public void changeDir(){
        if(this.dir == 0){
            this.dir = 1;
        }
        else{
            this.dir = 0;
        }
    }

}