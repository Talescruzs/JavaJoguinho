package teste;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class GameDraw {
    private SpriteBatch batch;
    
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
    public void draw(Integer stage, Integer avatarSelect, ArrayList<Bolinha> bolinhas, ArrayList<Personagem> pList, ArrayList<Avatar> avatars){
        batch.begin();
        drawFundo(avatars, avatarSelect);
        if(stage == 0){
            drawBolinha(bolinhas);
        }
        else if(stage == 1){
            drawBts(0);
        }
        else if(stage == 2){ // TODO
            // Quiz
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

    public void dispose(){
        fundo.dispose();
        batch.dispose();
    }
}
