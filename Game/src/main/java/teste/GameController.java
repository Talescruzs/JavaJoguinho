package teste;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameController {
    private Integer tamx, tamy, stage, avatarAtualSelec;
    private Texture fundoBase;
    private Locais local;
    private Quiz quiz;
    
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private ArrayList<Avatar> avatars = new ArrayList<>();

    private GameDraw gameDraw;
    private GameIO gameIO;

    public GameController(Texture fundoBase,int tamx, int tamy){
        this.tamx = tamx;
        this.tamy = tamy;
        this.fundoBase = fundoBase;
        this.stage = 0;
        this.gameDraw = new GameDraw(
            this.tamx, 
            this.tamy, 
            fundoBase, 
            new BtSelecionar(0, 200, 200),
            new BtSelecionar(1, this.tamx-500, 200)
        );
        gameDraw.setFundo(fundoBase, 250, 0, 1000, 837);
        this.gameIO = new GameIO(
            this.tamx, 
            this.tamy, 
            this.bolinhas, 
            new BtSelecionar(0, 200, 200),
            new BtSelecionar(1, this.tamx-500, 200)
        );
        createBolinhas(0);
        createAvatars();
        this.avatarAtualSelec = 1;
    }

    // Parte Interativa
    public void move(){
        for (Personagem p : personagens) {
            p.move();
        }
    }
    public void render(){
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.gameDraw.draw(this.stage, this.avatarAtualSelec-1, this.bolinhas, this.personagens, this.avatars, this.quiz);
    }
    public void click(int screenX, int screenY, int pointer, int button){
        Integer idLocal;
        if(this.stage == 0){
            idLocal = gameIO.bolinhaMenuClick(screenX, screenY);
            if(idLocal != 0){
                goDetalhes(idLocal);
            }
        }
        else if(this.stage == 1){
            idLocal = gameIO.selectClick(screenX, screenY);
            if(idLocal == -1){
                goMenu();
            }
            if(idLocal > 0){
                this.local = new Locais(idLocal);
                int random = new Random().nextInt(local.getQuestoes().size());
                this.quiz = new Quiz(this.local.getQuestoes().get(random));
                createPersonagem(this.local.getPersonagem());
                
                if(this.stage != 2){
                    goQuiz();

                }
                else {
                    changeAvatar();
                    if(this.avatarAtualSelec == 3){
                        goPvp();
                    }
                    else{
                        goMenu();
                    }
                }

            }
        }
        else if(this.stage == 2){
            idLocal = (gameIO.bolinhaMenuClick(screenX, screenY) - 1); // desculpa
            if(this.quiz.isRespostaCorreta(idLocal)){
                changeAvatar();
                if(this.avatarAtualSelec == 3){
                    goPvp();
                }
                else{
                    goMenu();
                }
            }
        }
    }
    public void tecla(int keycode){
        for (Personagem p : personagens) {
            p.processMove(keycode);
        }
    }
    public void dispose(){
        gameDraw.dispose();
    }
    
    // Parte Criacoes
    private void createBolinhas(Integer op){
        Bolinha bolinha;
        bolinhas.clear();
        if(op == 0){
            bolinha = new Bolinha(725, 425, 1); // Quarta Colônia
            this.bolinhas.add(bolinha);
            bolinha = new Bolinha(625, 600, 2); // Missões
            this.bolinhas.add(bolinha);
            bolinha = new Bolinha(1125, 525, 3); // Torres
            this.bolinhas.add(bolinha);
        } 
        if(op == 1){
            bolinha = new Bolinha(470, 100+24, 1); 
            this.bolinhas.add(bolinha);
            bolinha = new Bolinha(470, 206+24, 2); 
            this.bolinhas.add(bolinha);
            bolinha = new Bolinha(470, 313+24, 3); 
            this.bolinhas.add(bolinha);
            bolinha = new Bolinha(470, 421+24, 4); 
            this.bolinhas.add(bolinha);
        }
    }
    private void createAvatars(){
        Avatar avatar = new Avatar(true, this.tamx, this.tamy);
        this.avatars.add(avatar);
        avatar = new Avatar(false, this.tamx, this.tamy);
        this.avatars.add(avatar);
    }
    private void createPersonagem(Integer id){
        Personagem p;
        if(this.avatarAtualSelec == 1){
            p = new Personagem(id, 0, 100);
            
            ArrayList<Integer> movies = new ArrayList<Integer>();
            movies.add(Input.Keys.A);
            movies.add(Input.Keys.D);
            movies.add(Input.Keys.W);
            movies.add(Input.Keys.F); // ataque
            p.setMoves(movies);
        }
        else if(this.avatarAtualSelec == 2) {
            p = new Personagem(id, 500, 100);

            ArrayList<Integer> movies = new ArrayList<Integer>();
            movies.add(Input.Keys.LEFT);
            movies.add(Input.Keys.RIGHT);
            movies.add(Input.Keys.UP);
            movies.add(Input.Keys.ENTER); // ataque
            p.setMoves(movies);
        }
        else{
            return;
        }
        this.personagens.add(p); 
        // changeAvatar();
        // this.gameDraw.changeAvatar();
    }

    // Rotas
    private void goMenu(){
        gameDraw.setFundo(fundoBase, 250, 0, 1000, 837);
        createBolinhas(0);
        this.stage = 0;
    }
    private void goDetalhes(Integer idLocal){
        this.local = new Locais(idLocal);
        gameDraw.setFundo(new Texture(Gdx.files.internal(this.local.getImagens().get(0))), 0, 100, this.tamx, this.tamy-200);
        this.stage = 1;
    }
    private void goQuiz(){
        gameDraw.setFundo(new Texture(Gdx.files.internal("Game/src/main/resources/img/fundoQuiz.jpg")), 250, 0, this.tamx-500, this.tamy);
        createBolinhas(1);
        this.stage = 2;
    }
    private void goPvp(){
        gameDraw.setFundo(new Texture(Gdx.files.internal(this.local.getImagens().get(0))), 0, 100, this.tamx, this.tamy-200);
        this.stage = 3;
    }

    // Metodos internos
    private void changeAvatar(){
        // System.err.println(this.avatarAtualSelec);
        this.avatars.get(0).setFrameIni();
        this.avatars.get(1).setFrameIni();
        this.avatarAtualSelec ++;
    }
}
