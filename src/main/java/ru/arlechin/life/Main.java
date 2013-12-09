package ru.arlechin.life;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author arlechin
 */
public class Main {
    public static void main(String[] args) throws Exception {

        App.screen = new Screen(App.WIDTH, App.HEIGHT);

        App.world = new World();
        App.world.init(100, 100);

        System.setProperty("sun.awt.noerasebackground", "true");

        initGlider();

        System.out.print("Hello, World!" + "\n");

        while (true) {
            try {
                App.world.turn();
                App.screen.redraw(App.world.getCurrentGeneration());
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }

    }

    private static void initGlider() {
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

//    static class Task implements Runnable {
//        @Override
//        public void run() {
//            App.screen.redraw(App.world.getCurrentGeneration());
//        }
//    }

}
