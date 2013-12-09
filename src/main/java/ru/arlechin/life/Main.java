package ru.arlechin.life;

/**
 * @author arlechin
 */
public class Main {
    public static void main(String[] args) throws Exception {

        App.screen = new Screen(App.WORLD_WIDTH, App.WORLD_HEIGHT);

        App.world = new World();
        App.world.init(App.WORLD_WIDTH, App.WORLD_HEIGHT, App.screen);

        System.setProperty("sun.awt.noerasebackground", "true");

        drawGlider();

        while (true) {
            try {
                App.world.makeTurn();
                Thread.sleep(20);
            } catch (InterruptedException e) {

            }
        }

    }

    private static void drawGlider() {
        App.world.setCell(0, 0, false);
        App.world.setCell(1, 0, false);
        App.world.setCell(2, 0, true);

        App.world.setCell(0, 1, true);
        App.world.setCell(1, 1, false);
        App.world.setCell(2, 1, true);

        App.world.setCell(0, 2, false);
        App.world.setCell(1, 2, true);
        App.world.setCell(2, 2, true);
    }

}
