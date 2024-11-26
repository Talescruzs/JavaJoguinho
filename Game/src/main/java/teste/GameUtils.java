package teste;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class GameUtils {
    private Texture fundo;
    ArrayList<Dino> pList = new ArrayList<>();
    private SpriteBatch batch;

    public GameUtils(Texture fundo, ArrayList<Dino> pList) {
        this.fundo = fundo;
        this.pList = pList;

        batch = new SpriteBatch();
    }
    
    public void setFundo(Texture fundo){
        this.fundo = fundo;
    }

    public void drawFundo(Integer x, Integer y){
        batch.begin();
        batch.draw(this.fundo, x, y);
        batch.end();
    }
    public void drawP(){
        batch.begin();
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
        batch.end();
        
    }
}
