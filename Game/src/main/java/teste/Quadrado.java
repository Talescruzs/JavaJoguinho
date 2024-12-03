package teste;

public class Quadrado {
    private Pos cimaEsquerda, cimaDireita, baixoEsquerda, baixoDireita;
    public Quadrado(Pos cimaEsquerda, Pos cimaDireita, Pos baixoEsquerda, Pos baixoDireita){
        this.cimaEsquerda = cimaEsquerda;
        this.cimaDireita = cimaDireita;
        this.baixoEsquerda = baixoEsquerda;
        this.baixoDireita = baixoDireita;
    }

    public Quadrado(float cimaEsquerdax, float cimaEsquerday, float baixoDireitax, float baixoDireitay){
        this.cimaEsquerda = new Pos(cimaEsquerdax, cimaEsquerday);
        this.cimaDireita = new Pos(baixoDireitax, cimaEsquerday);
        this.baixoEsquerda = new Pos(cimaEsquerdax, baixoDireitay);
        this.baixoDireita = new Pos(baixoDireitax, baixoDireitay);
    }

    public boolean conflito(Quadrado q) {
        float esquerda1 = this.cimaEsquerda.getX();
        float direita1 = this.cimaDireita.getX();
        float cima1 = this.cimaEsquerda.getY();
        float baixo1 = this.baixoEsquerda.getY();
    
        float esquerda2 = q.getCE().getX();
        float direita2 = q.getCD().getX();
        float cima2 = q.getCE().getY();
        float baixo2 = q.getBE().getY();

        // System.out.println("Quadrado atual:");
        // System.out.println("Esquerda: " + esquerda1 + ", Direita: " + direita1 + ", Cima: " + cima1 + ", Baixo: " + baixo1);
        // System.out.println("Outro quadrado:");
        // System.out.println("Esquerda: " + esquerda2 + ", Direita: " + direita2 + ", Cima: " + cima2 + ", Baixo: " + baixo2);

        if (direita1 < esquerda2 || // Quadrado atual está à esquerda do outro
            esquerda1 > direita2 || // Quadrado atual está à direita do outro
            cima1 < baixo2 ||       // Quadrado atual está abaixo do outro
            baixo1 > cima2) {       // Quadrado atual está acima do outro
            // System.out.println("Sem conflito");
            return false; // Não há colisão
        }
        // System.out.println("Conflito");
    
        return true; // Há colisão
    }

    public void moveToDir(float d){
        this.cimaEsquerda.moveX(d);
        this.cimaDireita.moveX(d);
        this.baixoEsquerda.moveX(d);
        this.baixoDireita.moveX(d);
    }

    public void moveToEsq(float d){
        this.cimaEsquerda.moveX(-d);
        this.cimaDireita.moveX(-d);
        this.baixoEsquerda.moveX(-d);
        this.baixoDireita.moveX(-d);
    }

    public void moveToUp(double d){
        this.cimaEsquerda.moveY(d);
        this.cimaDireita.moveY(d);
        this.baixoEsquerda.moveY(d);
        this.baixoDireita.moveY(d);
    }

    public void setQuadrado(float cimaEsquerdax, float cimaEsquerday, float baixoDireitax, float baixoDireitay){
        this.cimaEsquerda = new Pos(cimaEsquerdax, cimaEsquerday);
        this.cimaDireita = new Pos(baixoDireitax, cimaEsquerday);
        this.baixoEsquerda = new Pos(cimaEsquerdax, baixoDireitay);
        this.baixoDireita = new Pos(baixoDireitax, baixoDireitay);
    }

    public void setCE(Pos cimaEsquerda){ this.cimaEsquerda = cimaEsquerda; }
    public void setCD(Pos cimaDireita){ this.cimaDireita = cimaDireita; }
    public void setBE(Pos baixoEsquerda){ this.baixoEsquerda = baixoEsquerda; }
    public void setBD(Pos baixoDireita){ this.baixoDireita = baixoDireita; }
    public Pos getCE(){ return this.cimaEsquerda; }
    public Pos getCD(){ return this.cimaDireita; }
    public Pos getBE(){ return this.baixoEsquerda; }
    public Pos getBD(){ return this.baixoDireita; }

}
