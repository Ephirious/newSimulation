package key_reader;

import simulation.Simulation;

import java.util.Scanner;

public class KeyReader implements Runnable {
    public static final char KEY_PAUSE = 'p';
    public static final char KEY_RESTART = 'r';
    public static final char KEY_CONTINUE = 'c';
    public static final char KEY_QUIT = 'q';

    private final Simulation simulation;

    public KeyReader(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        Scanner keyScanner = new Scanner(System.in);

        while (!simulation.isClosed()) {
            if (keyScanner.hasNext()) {
                String answer = keyScanner.next();

                for (int i = 0; i < answer.length(); i++) {
                    switch (answer.charAt(i)) {
                        case KEY_PAUSE -> simulation.pause();
                        case KEY_CONTINUE -> simulation.unpause();
                        case KEY_QUIT -> simulation.close();
                        case KEY_RESTART -> simulation.restart();
                    }
                }
            }
        }
    }
}
