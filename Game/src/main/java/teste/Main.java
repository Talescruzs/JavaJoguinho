package teste;
public class Main {
    public static void main(String[] args) {
        try {
            // Criar um Dino a partir do ID
            Dino dino1 = new Dino(1);

            // Exibir as informações do Dino
            System.out.println("ID: " + dino1.getId());
            System.out.println("Tamanho X: " + dino1.getTamx());
            System.out.println("Tamanho Y: " + dino1.getTamy());
            System.out.println("Imagem: " + dino1.getImg());
            System.out.println("Ataque forte - Dano: " + dino1.getAtaques().get("forte").getDano());
            System.out.println("Ataque fraco - Dano: " + dino1.getAtaques().get("fraco").getDano());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
