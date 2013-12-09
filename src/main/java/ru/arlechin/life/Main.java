package ru.arlechin.life;

/**
 * @author arlechin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        App.screen = new Screen(App.WORLD_WIDTH, App.WORLD_HEIGHT);

        App.world = new World();
        App.world.init(App.WORLD_WIDTH, App.WORLD_HEIGHT, App.screen);

        Resource resource = new Resource();

        App.world.loadPattern(resource.getPattern());

        System.setProperty("sun.awt.noerasebackground", "true");

        while (true) {
            try {
                App.world.makeTurn();
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }
}
