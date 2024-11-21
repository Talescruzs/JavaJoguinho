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
import teste.Locais;




public class MyGame extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private BitmapFont font;
    private String menuFundo, fundo, bolinha;
    private Texture imageMenuFundo, imageFundo, imageBolinha;
    private Dino p1, p2;
    private Texture imageP1, imageP2;
    private int stage = 0;

    public MyGame(String fundo, int id1){
        this.fundo = fundo;
        this.menuFundo = "Game/src/main/resources/img/mapaMenu.jpg";
        this.bolinha = "Game/src/main/resources/img/bolinha(1).png";
        
        this.p1 = new Dino(id1, 0, 0);
        this.p2 = new Dino(id1, 0, 0);
    }

    private void pvp(){
        p1.move();
        p2.move();

        batch.draw(imageFundo, 0, 0);
        // batch.draw(imageP1, this.p1.getPx(), this.p1.getPy());
        // batch.draw(imageP2, this.p2.getPx(), this.p2.getPy());
        if (this.p1.whereGo() == 1) {
            batch.draw(imageP1, this.p1.getPx(), this.p1.getPy(), this.p1.getTamx(), this.p1.getTamy());
        } else {
            batch.draw(imageP1, 
                       this.p1.getPx() + this.p1.getTamx(), // Ajusta a posição para espelhar
                       this.p1.getPy(), 
                       -this.p1.getTamx(), // Largura negativa espelha no eixo Y
                       this.p1.getTamy());
        }

        if (this.p2.whereGo() == 1) {
            batch.draw(imageP2, this.p2.getPx(), this.p2.getPy(), this.p2.getTamx(), this.p2.getTamy());
        } else {
            batch.draw(imageP2, 
                       this.p2.getPx() + this.p2.getTamx(), // Ajusta a posição para espelhar
                       this.p2.getPy(), 
                       -this.p2.getTamx(), // Largura negativa espelha no eixo Y
                       this.p2.getTamy());
        }
    }

    private void menu(){
        batch.draw(imageMenuFundo, 250, 0);
        batch.draw(imageBolinha, 725, 425); // quarta colonia
        batch.draw(imageBolinha, 625, 600); // missões
        batch.draw(imageBolinha, 1125, 525); // torres
    }

    @Override
    public void create() {
        // Inicializa o objeto para renderizar imagens e texto
        batch = new SpriteBatch();

        // Cria uma fonte padrão
        font = new BitmapFont(); // Usa a fonte padrão embutida no libGDX

        imageFundo = new Texture(Gdx.files.internal(this.fundo));
        imageMenuFundo = new Texture(Gdx.files.internal(this.menuFundo));
        imageBolinha = new Texture(Gdx.files.internal(this.bolinha));

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

        if(this.stage == 0){
            menu();
        }

        if(this.stage == 1){
            pvp();
        }

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
        imageMenuFundo.dispose();
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
        // if(screenX>=100 && screenX<=500 && screenY>=100 && screenY<=500 && stage == 0){
        //     this.stage = 1;
        // }

        if(screenX>=725 && screenX<=775 && screenY<=(837-425) && screenY>=(837-475) && stage == 0){
            System.out.println("Quarta Colonia");
            this.stage = 1;
        }
        else if(screenX>=625 && screenX<=675 && screenY<=(837-600) && screenY>=(837-650) && stage == 0){
            System.out.println("Missões");
            this.stage = 1;
        }
        else if(screenX>=1125 && screenX<=1175 && screenY<=(837-525) && screenY>=(837-575) && stage == 0){
            System.out.println("Torres");
            this.stage = 1;
        }

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