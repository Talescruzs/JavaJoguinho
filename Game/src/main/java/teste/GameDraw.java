package teste;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class GameDraw {
    private Texture fundo;
    ArrayList<Dino> pList = new ArrayList<>();
    ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private BtSelecionar btSelecionar;
    private SpriteBatch batch;
    private Integer stage;
    private Integer posFundox, posFundoy;
    private Integer tamx, tamy;

    public GameDraw(Integer tamx, Integer tamy, Texture fundo, ArrayList<Dino> pList, ArrayList<Bolinha> bolinhas, BtSelecionar btSelecionar) {
        this.fundo = fundo;
        this.pList = pList;
        this.bolinhas = bolinhas;
        this.btSelecionar = btSelecionar;
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

    protected void drawFundo(){
        batch.draw(this.fundo, this.posFundox, this.posFundoy, this.tamx, this.tamy);
    }
    protected void drawP(){
        for (Dino p : this.pList) {
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
    protected void drawBolinha(){
        for (Bolinha b : this.bolinhas) {
            batch.draw(b.getImg(), b.getX(), b.getY());
        }
    }
    protected void drawjogar(){
        batch.draw(btSelecionar.getImg(), btSelecionar.getPosx(), btSelecionar.getPosy());
    }
    public void draw(){
        batch.begin();
        drawFundo();
        if(this.stage == 0){
            drawBolinha();
        }
        if(this.stage == 1){
            drawjogar();
        }
        if(this.stage == 2){
            drawP();
        }
        batch.end();
    }

    public void dispose(){
        fundo.dispose();
        batch.dispose();
    }
}
