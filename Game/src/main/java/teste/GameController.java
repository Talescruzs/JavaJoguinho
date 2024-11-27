package teste;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameController {
    private Integer tamx, tamy, stage, avatarAtualSelec;
    private Texture fundoBase;
    
    private ArrayList<Personagem> personagens = new ArrayList<>();
    private ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private ArrayList<Avatar> avatars = new ArrayList<>();
    private Locais local;

    private GameDraw gameDraw;
    private GameIO gameIO;
    

    public GameController(Texture fundoBase,int tamx, int tamy){
        this.tamx = tamx;
        this.tamy = tamy;
        this.fundoBase = fundoBase;
        this.stage = 0;
        this.gameDraw = new GameDraw(
            this.tamx, 
            this.tamy, 
            fundoBase, 
            new BtSelecionar(0, 500, 200),
            new BtSelecionar(1, 500, 200)
        );
        gameDraw.setFundo(fundoBase, 250, 0, 1000, 837);
        this.gameIO = new GameIO(
            this.tamx, 
            this.tamy, 
            this.bolinhas, 
            new BtSelecionar(0, 500, 200)
        );
        createBolinhas();
        createAvatars();
        this.avatarAtualSelec = 1;
    }

    public void render(){
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1); // Define a cor de fundo (preto)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.gameDraw.draw(this.stage, this.avatarAtualSelec-1, this.bolinhas, this.personagens, this.avatars);
    }

    public void click(int screenX, int screenY, int pointer, int button){
        Integer idLocal;
        // System.out.println("clicou");
        if(this.stage == 0){
            idLocal = gameIO.bolinhaMenuClick(screenX, screenY);
            if(idLocal != 0){
                goDetalhes(idLocal);
            }
        }
        else if(this.stage == 1){

            idLocal = gameIO.selectClick(screenX, screenY);
            if(idLocal != 0){
                this.local = new Locais(idLocal);
                createPersonagem(this.local.getPersonagem());
                System.out.println(this.avatarAtualSelec);
                
                changeAvatar();
                if(this.avatarAtualSelec == 3){
                    goPvp();
                }
                else{
                    goMenu();
                }
            }
        }
    }

    private void createBolinhas(){
        Bolinha bolinha = new Bolinha(725, 425, 1); // Quarta Colônia
        this.bolinhas.add(bolinha);
        bolinha = new Bolinha(625, 600, 2); // Missões
        this.bolinhas.add(bolinha);
        bolinha = new Bolinha(1125, 525, 3); // Torres
        this.bolinhas.add(bolinha);
    }
    private void createAvatars(){
        Avatar avatar = new Avatar(true, this.tamx, this.tamy);
        this.avatars.add(avatar);
        avatar = new Avatar(false, this.tamx, this.tamy);
        this.avatars.add(avatar);
    }
    private void createPersonagem(Integer id){
        Personagem p;
        if(this.avatarAtualSelec == 1){
            p = new Personagem(id, 0, 100);
        }
        else if(this.avatarAtualSelec == 2) {
            p = new Personagem(id, 500, 100);
        }
        else{
            return;
        }
        this.personagens.add(p); 
        // changeAvatar();
        // this.gameDraw.changeAvatar();
    }

    private void goMenu(){
        gameDraw.setFundo(fundoBase, 250, 0, 1000, 837);
        this.stage = 0;
    }

    private void goDetalhes(Integer idLocal){
        this.local = new Locais(idLocal);
        gameDraw.setFundo(new Texture(Gdx.files.internal(this.local.getImagens().get(0))), 0, 100, this.tamx, this.tamy-200);
        this.stage = 1;
    }

    private void goPvp(){
        System.err.println("aaaaaa");
        gameDraw.setFundo(new Texture(Gdx.files.internal(this.local.getImagens().get(0))), 0, 100, this.tamx, this.tamy-200);
        this.stage = 2;
    }

    private void changeAvatar(){
        // System.err.println(this.avatarAtualSelec);
        this.avatars.get(0).setFrameIni();
        this.avatars.get(1).setFrameIni();
        this.avatarAtualSelec ++;
    }
}