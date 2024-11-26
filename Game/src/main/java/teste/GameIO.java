package teste;

import java.util.ArrayList;

public class GameIO {
    ArrayList<Bolinha> bolinhas = new ArrayList<>();
    private Integer telaTamx, telaTamy;
    private Integer stage;

    public GameIO(Integer telaTamx, Integer telaTamy, ArrayList<Bolinha> bolinhas){
        this.bolinhas = bolinhas;
        this.telaTamx = telaTamx;
        this.telaTamy = telaTamy;
        this.stage = 0;
    }

    public Integer bolinhaMenuClick(Integer px, Integer py){
        System.out.println("Clique em: X=" + px + " Y=" + py);
        for (Bolinha b : this.bolinhas) {
            if(px>=b.getX() && px<=(b.getX()+50) && py<=(this.telaTamy-b.getY()) && py>=(this.telaTamy-(b.getY()+50)) && stage == 0){
                System.out.println("Quarta Colonia");
                this.stage = 1;
                return b.getIdLocal();
            }
        }
        return 0;
    }
}
