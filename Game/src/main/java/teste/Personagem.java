package teste;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
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

public class Personagem {
    private int id;
    private int tamx, tamy;
    private int esq = 0, dir = 0, whereG = 0;
    private float posx, posy, posyBase; 
    private double dy = 0.0;
    private String img1, img2, imgAva;
    private Map<String, Ataque> ataques;
    private Animation animation;
    private Texture avatar;
    private ArrayList<Integer> listMoves = new ArrayList<Integer>();

    @JsonCreator
    private Personagem(
        @JsonProperty("id") int id,
        @JsonProperty("tamx") int tamx,
        @JsonProperty("tamy") int tamy,
        @JsonProperty("img1") String img1,
        @JsonProperty("img2") String img2,
        @JsonProperty("avatar") String imgAva
    ) {
        this.id = id;
        this.tamx = tamx;
        this.tamy = tamy;
        this.img1 = img1;
        this.img2 = img2;
        this.imgAva = imgAva;
    }

    // Construtor que inicializa o Personagem com base no ID
    public Personagem(int id, float posx, float posy) {
        try {
            // Caminho para o arquivo JSON
            File file = new File("Game/src/main/resources/json/personagens.json");

            // Criar o ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Ler o JSON como um Map
            Map<String, Personagem> personagens = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Personagem.class));

            // Procurar pelo personagem com o ID correspondente
            Personagem personagem = personagens.values().stream()
                    .filter(d -> d.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Personagem com ID " + id + " não encontrado!"));



            // Inicializar os atributos do Personagem atual com os valores do JSON
            this.id = personagem.id;
            this.tamx = personagem.tamx;
            this.tamy = personagem.tamy;
            this.img1 = personagem.img1;
            this.img2 = personagem.img2;
            this.ataques = personagem.ataques;
            this.posx = posx;
            this.posy = posy;
            this.posyBase = posy;
            this.imgAva = personagem.imgAva;

            Texture imagem = new Texture(Gdx.files.internal(personagem.img1));
            this.avatar = new Texture(Gdx.files.internal(this.imgAva));
            this.animation = new Animation(new TextureRegion(imagem), 2, 10f);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar o Personagem com ID " + id, e);
        }
    }

    public void setMoves(ArrayList<Integer> listMoves){
        this.listMoves = listMoves;
    }

    public void processMove(Integer move){
        if (move.equals(this.listMoves.get(0))) {
            changeEsq();
        } 
        else if (move.equals(this.listMoves.get(1))){
            changeDir();
        }
        else if (move.equals(this.listMoves.get(2))){
            jump();
        }
    }

    public void move(){
        if(this.esq == 1){
            this.move_esq();
            this.animation.update(1);
        }
        if(this.dir == 1){
            this.move_dir();
            this.animation.update(1);
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
        this.dy -= 0.6;
        if(this.posy < this.posyBase){
            this.posy = this.posyBase;
            this.dy = 0;
        }
    }

    public void jump(){
        if(this.posy == this.posyBase){
            this.dy = 19;
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
    public TextureRegion getFrame(){return this.animation.getFrame();}
    public Texture getAvatar(){return this.avatar; }
    
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