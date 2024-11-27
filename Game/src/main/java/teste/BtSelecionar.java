package teste;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BtSelecionar {
    private Texture img;
    private Integer posx, posy;
    private Integer tamx, tamy;
    public BtSelecionar(String path, Integer posx, Integer posy, Integer tamx, Integer tamy){
        this.img = new Texture(Gdx.files.internal(path));
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
    }
    public BtSelecionar(Integer option, Integer posx, Integer posy){
        if(option == 0){
            this.img = new Texture(Gdx.files.internal("Game/src/main/resources/img/btJogar.jpg"));
            this.posx = posx;
            this.posy = posy;
            this.tamx = 500;
            this.tamy = 200;
        }
        else{
            this.img = new Texture(Gdx.files.internal("Game/src/main/resources/img/btVoltar.jpg"));
            this.posx = posx;
            this.posy = posy;
            this.tamx = 500;
            this.tamy = 200;
        }
    }
    public Texture getImg() { return this.img; }
    public Integer getPosx() { return this.posx; }
    public Integer getPosy() { return this.posy; }
    public Integer getTamx() { return this.tamx; }
    public Integer getTamy() { return this.tamy; }
}
