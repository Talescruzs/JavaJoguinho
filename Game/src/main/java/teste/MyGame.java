package teste;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture image;
    private String imgPath1;

    public MyGame(String imgPath1){
        this.imgPath1 = imgPath1;
    }

    @Override
    public void create() {
        // Inicializa o objeto para renderizar imagens e texto
        batch = new SpriteBatch();

        // Cria uma fonte padrão
        font = new BitmapFont(); // Usa a fonte padrão embutida no libGDX
        image = new Texture(Gdx.files.internal(this.imgPath1)); // Certifique-se de que a imagem está na pasta "assets/"
        font.getData().setScale(2); // Aumenta o tamanho da fonte
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Inicia o processo de desenho
        batch.begin();

        // Escreve texto na posição (200, 300)
        font.draw(batch, "Olá, Mundo!", 200, 300);
        // Desenha a imagem na posição (100, 150)
        batch.draw(image, 100, 150);

        // Finaliza o processo de desenho
        batch.end();
    }

    @Override
    public void dispose() {
        // Libera os recursos
        batch.dispose();
        font.dispose();
    }
}
