package teste;

import java.util.ArrayList;

public class GameIO {
    ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private BtSelecionar btSelecionar, btVoltar;
    private Integer telaTamx, telaTamy;
    private Integer idLocal;

    public GameIO(Integer telaTamx, Integer telaTamy, ArrayList<Bolinha> bolinhas, BtSelecionar btSelecionar, BtSelecionar btVoltar){
        this.bolinhas = bolinhas;
        this.telaTamx = telaTamx;
        this.telaTamy = telaTamy;
        this.btSelecionar = btSelecionar;
        this.btVoltar = btVoltar;
        this.idLocal = 0;
    }

    public Integer bolinhaMenuClick(Integer px, Integer py){
        for (Bolinha b : this.bolinhas) {
            if(px>=b.getX() && px<=(b.getX()+50) && py<=(this.telaTamy-b.getY()) && py>=(this.telaTamy-(b.getY()+50))){
                this.idLocal = b.getIdLocal();
                return this.idLocal;
            }
        }
        return 0;
    }
    public Integer selectClick(Integer px, Integer py){
        if(px>=btSelecionar.getPosx() && px<=(btSelecionar.getPosx()+btSelecionar.getTamx()) && py<=(this.telaTamy-btSelecionar.getPosy()) && py>=(this.telaTamy-(btSelecionar.getPosy()+btSelecionar.getTamy()))){
            return this.idLocal;
        }
        if(px>=btVoltar.getPosx() && px<=(btVoltar.getPosx()+btVoltar.getTamx()) && py<=(this.telaTamy-btVoltar.getPosy()) && py>=(this.telaTamy-(btVoltar.getPosy()+btVoltar.getTamy()))){
            return -1;
        }
        return 0;
    }
}
