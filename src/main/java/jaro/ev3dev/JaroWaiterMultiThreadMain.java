package jaro.ev3dev;

import ev3dev.sensors.Button;
import ev3dev.sensors.EV3Key;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifies functionality regarding issue #
 */
public class JaroWaiterMultiThreadMain {

    private static final Logger log = LoggerFactory.getLogger(JaroWaiterMultiThreadMain.class);

    public static void main(final String[] args){
        log.info("Demo");

//        new Thread(() -> {
//            System.out.println("Waiting for UP press + release ...");
//            Button.UP.waitForPressAndRelease();
//            System.out.println("... UP waiter woken up");
//        }).start();
//
//        new Thread(() -> {
//            System.out.println("Waiting for DOWN press + release ...");
//            Button.DOWN.waitForPressAndRelease();
//            System.out.println("... DOWN waiter woken up");
//        }).start();

//        System.out.println("Main waits for ESCAPE press ...");
//        Button.ESCAPE.waitForPress();

        new EV3Key(EV3Key.BUTTON_ALL).addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final Key key) {
                System.out.format("%s PRESSED%n", key);
            }
            @Override
            public void keyReleased(final Key key) {
                System.out.format("%s RELEASED%n", key);
            }
        });

        System.out.println("Waiting ...");
        Button.waitForAnyPress();

        System.out.println("DONE");
    }
}
