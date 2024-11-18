package teste;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import teste.Dino;




public class MyGame extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private BitmapFont font;
    private String fundo;
    private Texture imageFundo;
    private Dino p1, p2;
    private Texture imageP1, imageP2;

    public MyGame(String fundo, int id1){
        this.fundo = fundo;
        this.p1 = new Dino(id1, 0, 0);
        this.p2 = new Dino(id1, 0, 0);
    }

    @Override
    public void create() {
        // Inicializa o objeto para renderizar imagens e texto
        batch = new SpriteBatch();

        // Cria uma fonte padrão
        font = new BitmapFont(); // Usa a fonte padrão embutida no libGDX

        imageFundo = new Texture(Gdx.files.internal(this.fundo));

        imageP1 = new Texture(Gdx.files.internal(this.p1.getImg1()));

        imageP2 = new Texture(Gdx.files.internal(this.p2.getImg2()));
        
        font.getData().setScale(2); // Aumenta o tamanho da fonte

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Inicia o processo de desenho
        batch.begin();

        p1.move();
        p2.move();

        batch.draw(imageFundo, 0, 0);
        batch.draw(imageP1, this.p1.getPx(), this.p1.getPy());
        batch.draw(imageP2, this.p2.getPx(), this.p2.getPy());

        // Escreve texto na posição (200, 300)
        font.draw(batch, "Olá, Mundo!", 200, 300);

        // Finaliza o processo de desenho
        batch.end();
    }

    @Override
    public void dispose() {
        // Libera os recursos
        batch.dispose();
        font.dispose();
        imageFundo.dispose();
        imageP1.dispose();
        imageP2.dispose();
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
        // Imprime as coordenadas do clique
        System.out.println("Clique em: X=" + screenX + " Y=" + screenY);

        // // Atualiza a posição da imagem para o local do clique
        // imageX = screenX - image.getWidth() / 2;
        // imageY = Gdx.graphics.getHeight() - screenY - image.getHeight() / 2; // Ajusta eixo Y

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
