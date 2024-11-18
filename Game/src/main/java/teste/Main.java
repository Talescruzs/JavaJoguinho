package teste;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Game;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Meu Jogo";
        config.width = 1600;
        config.height = 500;

        new LwjglApplication(new MyGame("Game/src/main/resources/img/QuartaColonia.jpg", 1), config);
    }
}
