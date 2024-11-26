package teste;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Meu Jogo";
        config.width = 1600;
        config.height = 837;

        new LwjglApplication(new MyGame(1), config);
    }
}
