package teste;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class GameDraw {
    private Texture fundo;
    private BtSelecionar btSelecionar, btVoltar;
    private SpriteBatch batch;
    private Integer stage;
    private Integer posFundox, posFundoy;
    private Integer tamx, tamy;

    public GameDraw(Integer tamx, Integer tamy, Texture fundo, BtSelecionar btSelecionar, BtSelecionar btVoltar) {
        this.fundo = fundo;
        this.btSelecionar = btSelecionar;
        this.btVoltar = btVoltar;
        this.stage = 0;
        this.posFundox = 0;
        this.posFundoy = 0;
        this.tamx = tamx;
        this.tamy = tamy;

        batch = new SpriteBatch();
    }
    
    public void setFundo(Texture fundo, Integer x, Integer y, Integer tamx, Integer tamy){
        this.fundo = fundo;
        this.posFundox = x;
        this.posFundoy = y;
        this.tamx = tamx;
        this.tamy = tamy;
    }

    public void goMenu(){
        this.stage = 0;
    }

    public void goLocal(){
        this.stage = 1;
    }

    public void goBattle(){
        this.stage = 2;
    }

    public Integer getStage(){ return this.stage; }

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
    protected void drawjogar(){
        batch.draw(btSelecionar.getImg(), btSelecionar.getPosx(), btSelecionar.getPosy());
        batch.draw(btVoltar.getImg(), btSelecionar.getPosx(), btSelecionar.getPosy());
    }
    public void draw(Integer stage, Integer avatarSelect, ArrayList<Bolinha> bolinhas, ArrayList<Personagem> pList, ArrayList<Avatar> avatars){
        batch.begin();
        drawFundo(avatars, avatarSelect);
        if(stage == 0){
            drawBolinha(bolinhas);
        }
        if(stage == 1){
            drawjogar();
        }
        if(stage == 2){
            drawP(pList);
        }
        batch.end();
    }

    // public void draw(ArrayList<Bolinha> bolinhas, ArrayList<Personagem> pList, ArrayList<Avatar> avatars){
    //     batch.begin();
    //     drawFundo(avatars);
    //     if(this.stage == 0){
    //         drawBolinha(bolinhas);
    //     }
    //     if(this.stage == 1){
    //         drawjogar();
    //     }
    //     if(this.stage == 2){
    //         drawP(pList);
    //     }
    //     batch.end();
    // }
    
    public void dispose(){
        fundo.dispose();
        batch.dispose();
    }
}
