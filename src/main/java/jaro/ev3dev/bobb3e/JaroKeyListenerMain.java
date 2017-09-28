package jaro.ev3dev.bobb3e;

import ev3dev.sensors.EV3Key;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class JaroKeyListenerMain {

    public static void main(final String[] args){

        System.out.println("Adding key listener");
        new EV3Key(EV3Key.BUTTON_ALL).addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final Key key) {
                System.out.format("%s pressed%n", key);
            }
            @Override
            public void keyReleased(final Key key) {
                System.out.format("%s released%n", key);
            }
        });

        System.out.println("Entering wait for Escape ...");
        new EV3Key(EV3Key.BUTTON_BACKSPACE).waitForPressAndRelease();
        System.out.println("DONE");

    }
}
