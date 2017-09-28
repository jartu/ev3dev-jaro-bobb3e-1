package jaro.ev3dev;

import ev3dev.sensors.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifies functionality regarding issue #
 */
public class JaroWaiterMain {

    private static final Logger log = LoggerFactory.getLogger(JaroWaiterMain.class);

    public static void main(final String[] args){
        log.info("Demo");

        Button.UP.waitForPress();
        log.info("Pressed UP");

        Button.DOWN.waitForPress();
        log.info("Pressed DOWN");

        Button.LEFT.waitForPress();
        log.info("Pressed LEFT");

        Button.RIGHT.waitForPress();
        log.info("Pressed RIGHT");

        Button.ESCAPE.waitForPress();
        log.info("Pressed ESCAPE");

        Button.ENTER.waitForPress();
        log.info("Pressed ENTER");

    }
}
