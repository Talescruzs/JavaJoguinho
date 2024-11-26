package teste;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import java.util.ArrayList;

public class MyGame extends ApplicationAdapter implements InputProcessor {
    private String fundo;
    private Texture imageFundo;
    private Dino p1, p2;
    private int stage = 0;
    private GameDraw gameDraw;
    private GameIO gameIO;
    private Locais local;
    private Integer tamx, tamy;

    public MyGame(int id1, int tamx, int tamy){
        this.fundo = "Game/src/main/resources/img/mapaMenu.jpg";
        this.tamx = tamx;
        this.tamy = tamy;
    }

    private void pvp(){
        p1.move();
        p2.move();

        gameDraw.setFundo(imageFundo, 0, 0);
        gameDraw.draw();
    }

    private void menu(){
        gameDraw.setFundo(imageFundo, 250, 0);
        gameDraw.draw();
    }
    
    private void detalhes(){
        gameDraw.setFundo(imageFundo, 0, 100);
        gameDraw.draw();
    }

    @Override
    public void create() {

        imageFundo = new Texture(Gdx.files.internal(this.fundo));


        this.p1 = new Dino(1, 0, 0);
        this.p2 = new Dino(1, 0, 0);
        ArrayList<Dino> dinos = new ArrayList<>();
        dinos.add(this.p1); // Primeiro Dino
        dinos.add(this.p2); // Segundo Dino

        ArrayList<Bolinha> bolinhas = new ArrayList<>();
        Bolinha bolinha = new Bolinha(725, 425, 1);
        bolinhas.add(bolinha);
        bolinha = new Bolinha(625, 600, 2);
        bolinhas.add(bolinha);
        bolinha = new Bolinha(1125, 525, 3);
        bolinhas.add(bolinha);


        this.gameDraw = new GameDraw(imageFundo, dinos, bolinhas);
        this.gameIO = new GameIO(this.tamx, this.tamy, bolinhas);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(this.stage == 0){
            imageFundo = new Texture(Gdx.files.internal(this.fundo));
            gameDraw.goMenu();
            menu();
        }
        
        if(this.stage == 1){
            imageFundo = new Texture(Gdx.files.internal(this.local.getImagens().get(0)));
            gameDraw.goLocal();
            detalhes();
        }

        if(this.stage == 2){
            imageFundo = new Texture(Gdx.files.internal(this.local.getImagens().get(1)));
            gameDraw.goBattle();
            pvp();
        }

    }

    @Override
    public void dispose() {
        // Libera os recursos
        imageFundo.dispose();
        gameDraw.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        // Move a imagem com as setas do teclado
        switch (keycode) {
            case Input.Keys.LEFT:
                p2.changeEsq();
                break;
            case Input.Keys.RIGHT:
                p2.changeDir();
                break;
            case Input.Keys.UP:
                p2.jump();
                break;
            case Input.Keys.A:
                p1.changeEsq();
                break;
            case Input.Keys.D:
                p1.changeDir();
                break;
            case Input.Keys.W:
                p1.jump();
                break;
        }
        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                p2.changeEsq();
                break;
            case Input.Keys.RIGHT:
                p2.changeDir();
                break;
            case Input.Keys.A:
                p1.changeEsq();
                break;
            case Input.Keys.D:
                p1.changeDir();
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // Evento ao digitar uma tecla (ex.: texto)
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX>=725 && screenX<=775 && screenY<=(837-425) && screenY>=(837-475) && stage == 1){
            System.out.println("Quarta Colonia");
            this.local = new Locais(1);
            this.stage = 2;
        }

        Integer idLocal = gameIO.bolinhaMenuClick(screenX, screenY);
        if(idLocal != 0){
            this.local = new Locais(idLocal);
            this.stage = 1;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Evento ao soltar o clique
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Evento ao arrastar o mouse (se necessário)
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Evento ao mover o mouse (se necessário)
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Evento ao usar o scroll do mouse (se necessário)
        return false;
    }
}
