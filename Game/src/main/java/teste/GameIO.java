package teste;

import java.util.ArrayList;

public class GameIO {
    ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private BtSelecionar btSelecionar;
    private Integer telaTamx, telaTamy;
    private Integer stage, idLocal;

    public GameIO(Integer telaTamx, Integer telaTamy, ArrayList<Bolinha> bolinhas, BtSelecionar btSelecionar){
        this.bolinhas = bolinhas;
        this.telaTamx = telaTamx;
        this.telaTamy = telaTamy;
        this.btSelecionar = btSelecionar;
        this.stage = 0;
        this.idLocal = 0;
    }

    public Integer bolinhaMenuClick(Integer px, Integer py){
        // System.out.println("Clique em: X=" + px + " Y=" + py);
        for (Bolinha b : this.bolinhas) {
            if(px>=b.getX() && px<=(b.getX()+50) && py<=(this.telaTamy-b.getY()) && py>=(this.telaTamy-(b.getY()+50)) && stage == 0){
                System.out.println(b.getIdLocal());
                this.stage = 1;
                this.idLocal = b.getIdLocal();
            }
        }
        return this.idLocal;
    }
    public Integer selectClick(Integer px, Integer py){
        if(px>=btSelecionar.getPosx() && px<=(btSelecionar.getPosx()+btSelecionar.getTamx()) && py<=(this.telaTamy-btSelecionar.getPosy()) && py>=(this.telaTamy-(btSelecionar.getPosy()+btSelecionar.getTamy())) && stage == 1){
            System.out.println("Selecionou "+this.idLocal);
            this.stage = 2;
            return this.idLocal;
        }
        return 0;
    }
}
