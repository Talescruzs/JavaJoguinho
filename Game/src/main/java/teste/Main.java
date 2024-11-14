package teste;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Game;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Meu Jogo";
        config.width = 1000;
        config.height = 1000;

        new LwjglApplication(new MyGame("Game/src/main/resources/img/Bagualossauro1.jpeg"), config);
    }
}
