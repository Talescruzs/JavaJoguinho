package teste;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyGame extends ApplicationAdapter implements InputProcessor {
    private Personagem p1, p2;
    private Integer tamx, tamy;

    private GameController gController;

    public MyGame(int tamx, int tamy){
        this.tamx = tamx;
        this.tamy = tamy;
    }

    @Override
    public void create() {

        this.gController = new GameController(
            new Texture(Gdx.files.internal("Game/src/main/resources/img/mapaMenu.jpg")), 
            this.tamx, 
            this.tamy
        );

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        gController.move();
        gController.render();
    }

    @Override
    public void dispose() {
        // Libera os recursos
        // imageFundo.dispose();
        // gameDraw.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        gController.tecla(keycode);

        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        gController.tecla(keycode);

        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        // Evento ao digitar uma tecla (ex.: texto)
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { 
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
