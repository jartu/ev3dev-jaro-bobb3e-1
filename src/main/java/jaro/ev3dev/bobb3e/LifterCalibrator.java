package jaro.ev3dev.bobb3e;

import ev3dev.actuators.lego.motors.EV3MediumRegulatedMotor;
import ev3dev.sensors.ev3.EV3IRSensor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifterCalibrator {

    private static final Logger log = LoggerFactory.getLogger(LifterCalibrator.class);

    private static final double LIFTER_POSITION_SEARCH_MAX_SPEED_RATIO = 0.1;
    private static final long LIFTER_MAX_UP_MOVEMENT_MILLIS = 3_000L;

    public static void main(final String[] args) throws Exception {

        // the lifter motor instance
        final EV3MediumRegulatedMotor lifterMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        final float maxLifterSpeed = lifterMotor.getMaxSpeed();
        log.info("Max Lifter speed: {}", maxLifterSpeed);
        final int initialLifterPos = lifterMotor.getTachoCount();
        log.info("Initial Lifter position: {}", initialLifterPos);

        // the IR sensor (to determine the Lifter upper position)
        final EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
        final SampleProvider irSampleProvider = irSensor.getDistanceMode();
        final int irSampleSize = irSampleProvider.sampleSize();
        final float[] irSample = new float[irSampleSize];
        final int initialIRDistance = getIRDistance(irSampleProvider, irSample);
        log.info("Initial IR distance: {}", initialIRDistance);

        // moving the Lifter up until it's detected by the IR sensor (if not detected already)
        if (initialIRDistance == Integer.MAX_VALUE) {
            // start moving the Lifter up
            final int lifterUpSpeed = (int) Math.round(maxLifterSpeed * LIFTER_POSITION_SEARCH_MAX_SPEED_RATIO);
            lifterMotor.setSpeed(lifterUpSpeed);
            lifterMotor.forward();
            log.info("Started Lifter UP movement (speed: {})", lifterUpSpeed);

            // moving the Lifter up until the IR sensor notices it (or until a timeout is reached)
            final long startMillis = System.currentTimeMillis();
            int irDistance = -1;
            long endMillis = -1L;
            int tachoCount = -1, prevTachoCount = -1;
            while (((endMillis = System.currentTimeMillis()) - startMillis) < LIFTER_MAX_UP_MOVEMENT_MILLIS &&
                    (irDistance = getIRDistance(irSampleProvider, irSample)) == Integer.MAX_VALUE &&
                    (tachoCount = lifterMotor.getTachoCount()) != prevTachoCount || tachoCount < 10) {
                log.debug("(distance: {}, tacho count: {})", irDistance, tachoCount);
                prevTachoCount = tachoCount;
                Delay.msDelay(5L);
            }
            log.info("Stopped Lifter after {} [ms] with IR distance {} and prev/curr tacho count {}/{}",
                    (endMillis - startMillis), irDistance, prevTachoCount, tachoCount);

            // going back a little in case with got the Lifter pressed against the IR sensor
            lifterMotor.backward();
            Delay.msDelay(500L);
            lifterMotor.stop();

        } else {
            log.info("Avoiding Lifter UP moving as IR detects something");
        }


//        final int speed = (int)Math.round(maxLifterSpeed * 0.1);
//        lifterMotor.setSpeed(speed);
//        lifterMotor.backward();
//        log.info("Started Lifter DOWN movement");
//        final long startMillis = System.currentTimeMillis();
//        while ((System.currentTimeMillis() - startMillis) < 3_000L) {
//            log.info("Lifter position: {}", lifterMotor.getTachoCount());
//            Delay.msDelay(100L);
//        }
//        log.info("Final position: {} -> {}", initialLifterPos, lifterMotor.getTachoCount());
//
//        lifterMotor.forward();
//        Delay.msDelay(200L);
//        lifterMotor.stop();


    }

    private static int getIRDistance(final SampleProvider sampleProvider, final float[] sample) {
        sampleProvider.fetchSample(sample, 0);
        return (int)Math.round(sample[0]);
    }

}
