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
    private Personagem p1, p2;
    private int stage = 0;
    private GameDraw gameDraw;
    private GameIO gameIO;
    private Locais local;
    private Integer tamx, tamy;
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private ArrayList<Avatar> avatars = new ArrayList<>();

    private GameController gController;

    public MyGame(int tamx, int tamy){
        this.fundo = "Game/src/main/resources/img/mapaMenu.jpg";
        this.tamx = tamx;
        this.tamy = tamy;
    }

    // private void pvp(){
    //     p1.move();
    //     p2.move();
    //     gameDraw.setFundo(imageFundo, 0, 0, this.tamx, this.tamy);
    //     gameDraw.draw(this.bolinhas, this.personagens, this.avatars);
    // }

    // private void menu(){
    //     gameDraw.setFundo(imageFundo, 250, 0, 1000, 837);
    //     gameDraw.draw(this.bolinhas, this.personagens, this.avatars);
    // }
    
    // private void detalhes(){
    //     gameDraw.setFundo(imageFundo, 0, 100, this.tamx, this.tamy-200);
    //     gameDraw.draw(this.bolinhas, this.personagens, this.avatars);
    // }

    @Override
    public void create() {

        this.gController = new GameController(
            new Texture(Gdx.files.internal("Game/src/main/resources/img/mapaMenu.jpg")), 
            this.tamx, 
            this.tamy
        );

        Gdx.input.setInputProcessor(this);
        
        // BtSelecionar btSelecionar = new BtSelecionar(500, 200);
        // imageFundo = new Texture(Gdx.files.internal(this.fundo));

        // this.p1 = new Personagem(1, 0, 0);
        // this.p2 = new Personagem(1, 0, 0);
        // this.personagens.add(this.p1); // Primeiro Personagem
        // this.personagens.add(this.p2); // Segundo Personagem

        // Bolinha bolinha = new Bolinha(725, 425, 1);
        // this.bolinhas.add(bolinha);
        // bolinha = new Bolinha(625, 600, 2);
        // this.bolinhas.add(bolinha);
        // bolinha = new Bolinha(1125, 525, 3);
        // this.bolinhas.add(bolinha);

        // Avatar avatar = new Avatar(true, this.tamx, this.tamy);
        // this.avatars.add(avatar);
        // avatar = new Avatar(false, this.tamx, this.tamy);
        // this.avatars.add(avatar);
        

        // // this.gameDraw = new GameDraw(this.tamx, this.tamy, imageFundo, btSelecionar);
        // // this.gameIO = new GameIO(this.tamx, this.tamy, bolinhas, btSelecionar);
        
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gController.render();

        // if(this.stage == 0){
        //     imageFundo = new Texture(Gdx.files.internal(this.fundo));
        //     gameDraw.goMenu();
        //     menu();
        // }
        
        // if(this.stage == 1){
        //     imageFundo = new Texture(Gdx.files.internal(this.local.getImagens().get(0)));
        //     gameDraw.goLocal();
        //     detalhes();
        // }

        // if(this.stage == 2){
        //     imageFundo = new Texture(Gdx.files.internal(this.local.getImagens().get(1)));
        //     gameDraw.goBattle();
        //     pvp();
        // }
    }

    @Override
    public void dispose() {
        // Libera os recursos
        // imageFundo.dispose();
        // gameDraw.dispose();
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
        // Integer idLocal;
        // if(this.stage == 0){
        //     idLocal = gameIO.bolinhaMenuClick(screenX, screenY);
        //     if(idLocal != 0){
        //         this.local = new Locais(idLocal);
        //         this.stage = 1;
        //     }
        // }
        // else if(this.stage == 1){
        //     idLocal = gameIO.selectClick(screenX, screenY);
        //     if(idLocal != 0){
        //         this.local = new Locais(idLocal);
        //         this.stage = 2;
        //         // this.p1 = new Personagem(this.local.getPersonagem(), 0, 0);
        //     }
        // }
        
        gController.click(screenX, screenY, pointer, button);

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
