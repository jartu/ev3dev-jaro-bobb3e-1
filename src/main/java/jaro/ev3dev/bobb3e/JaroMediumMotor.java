package jaro.ev3dev.bobb3e;

import ev3dev.actuators.lego.motors.BaseRegulatedMotor;
import ev3dev.actuators.lego.motors.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class JaroMediumMotor {

    public static void main(final String[] args) throws Exception {
        System.out.println("Medium motor test");

        final EV3MediumRegulatedMotor medium = new EV3MediumRegulatedMotor(MotorPort.A);

        final BaseRegulatedMotor motor = medium;

//        motor.

//        lifterMotor.forward();

//        log.info("Started...");
//
//        Thread.sleep(10_000L);
//
//        lifterMotor.stop();
//
//        log.info("Stopped");

    }
}
