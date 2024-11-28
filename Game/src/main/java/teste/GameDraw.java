package teste;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.ApplicationAdapter;
import java.util.ArrayList;

public class GameDraw {
    private SpriteBatch batch;
    private BitmapFont font;
    
    private Texture fundo;
    private BtSelecionar btSelecionar, btVoltar;

    private Integer posFundox, posFundoy, tamx, tamy;

    public GameDraw(Integer tamx, Integer tamy, Texture fundo, BtSelecionar btSelecionar, BtSelecionar btVoltar) {
        this.fundo = fundo;
        this.btSelecionar = btSelecionar;
        this.btVoltar = btVoltar;
        this.posFundox = 0;
        this.posFundoy = 0;
        this.tamx = tamx;
        this.tamy = tamy;

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK); // Cor do texto
        font.getData().setScale(2); // Tamanho do texto
    }
    
    // Modifica
    public void setFundo(Texture fundo, Integer x, Integer y, Integer tamx, Integer tamy){
        this.fundo = fundo;
        this.posFundox = x;
        this.posFundoy = y;
        this.tamx = tamx;
        this.tamy = tamy;
    }
    
    // Desenho
    public void draw(Integer stage, Integer avatarSelect, ArrayList<Bolinha> bolinhas, ArrayList<Personagem> pList, ArrayList<Avatar> avatars, Quiz quiz){
        batch.begin();
        drawFundo(avatars, avatarSelect);
        if(stage == 0){
            drawBolinha(bolinhas);
        }
        else if(stage == 1){
            drawBts(0);
        }
        else if(stage == 2){ // TODO
            ArrayList<Integer> posy = new ArrayList<>();
            Integer py;
            py = 100+35;
            posy.add(py);
            py = 206+35;
            posy.add(py);
            py = 313+35;
            posy.add(py);
            py = 421+35;
            posy.add(py);
            ArrayList<Integer> posx = new ArrayList<>();
            Integer px;
            px = 550;
            posx.add(px);
            posx.add(px);
            posx.add(px);
            posx.add(px);

            drawPalavras(quiz.getRespostas(), posx, posy);
            drawPalavras(quiz.getPergunta(), 390, 630);
            drawBolinha(bolinhas);
        }
        else if(stage == 3){
            drawP(pList);
        }
        batch.end();
    }
    protected void drawFundo(ArrayList<Avatar> avatars, Integer avatarSelect){
        batch.draw(this.fundo, this.posFundox, this.posFundoy, this.tamx, this.tamy);
        if(avatarSelect == 0 || avatarSelect == 1){
            avatars.get(avatarSelect).update();
        }
        for (Avatar a : avatars) {
            batch.draw(a.getFrame(), a.getX(), a.getY());
        }
    }
    protected void drawP(ArrayList<Personagem> pList){
        for (Personagem p : pList) {
            if (p.whereGo() == 1) {
                batch.draw(p.getFrame(), p.getPx(), p.getPy(), p.getTamx(), p.getTamy());
            } else {
                batch.draw(p.getFrame(), 
                           p.getPx() + p.getTamx(), // Ajusta a posição para espelhar
                           p.getPy(), 
                           -p.getTamx(), // Largura negativa espelha no eixo Y
                           p.getTamy());
            }
        }
        
    }
    protected void drawBolinha(ArrayList<Bolinha> bolinhas){
        for (Bolinha b : bolinhas) {
            batch.draw(b.getImg(), b.getX(), b.getY());
        }
    }
    protected void drawBts(Integer op){
        if(op == 0){
            batch.draw(btSelecionar.getImg(), btSelecionar.getPosx(), btSelecionar.getPosy());
            batch.draw(btVoltar.getImg(), btVoltar.getPosx(), btVoltar.getPosy());
        }
    }
    // Palavras
    private void drawPalavras(String palavra, Integer posx, Integer posy){
        font.draw(batch, palavra, posx, posy, 900, 10, true); // Texto, posição X, posição Y
    }
    private void drawPalavras(ArrayList<String> palavras, ArrayList<Integer> posx, ArrayList<Integer> posy){
        for (Integer i = 0; i < palavras.size(); i++) {
            drawPalavras(palavras.get(i), posx.get(i), posy.get(i));
        }
    }

    public void dispose(){
        fundo.dispose();
        batch.dispose();
    }
}
