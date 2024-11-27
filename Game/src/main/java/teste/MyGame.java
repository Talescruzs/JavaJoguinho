package teste;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MyGame extends ApplicationAdapter implements InputProcessor {
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
        gController.dispose();
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
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { 
        gController.click(screenX, screenY, pointer, button);
        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
