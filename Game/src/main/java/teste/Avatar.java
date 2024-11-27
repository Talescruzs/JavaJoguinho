package teste;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Avatar{
    private Texture img;
    private Integer idPersonagem, vidaTotal, vidaAtual, posx, posy; 
    private Animation animation;
    
    public Avatar(boolean isP1, Integer tamx, Integer tamy){
        this.idPersonagem = 0;
        this.vidaTotal = 0;
        this.vidaAtual = 0;
        this.posy = tamy-300;
        if(isP1){
            this.posx = 50;
            this.img = new Texture(Gdx.files.internal("Game/src/main/resources/img/IndetP1A.jpg"));
        }
        else{
            this.img = new Texture(Gdx.files.internal("Game/src/main/resources/img/IndetP2A.jpg"));
            this.posx = tamx-350;
        }
        this.animation = new Animation(new TextureRegion(this.img), 2, 30f);
    }
    public void setPersonagem(Integer id){
        Personagem personagem = new Personagem(id, 0, 0); 
        this.img = personagem.getAvatar();
    }
    public Integer getX(){ return this.posx; }
    public Integer getY(){ return this.posy; }
    // public Texture getImg(){ return this.img; }
    public void update(){ this.animation.update(1); }
    public TextureRegion getFrame(){ return this.animation.getFrame(); }
    public void setFrameIni(){ this.animation.setFrameIni(); }
}