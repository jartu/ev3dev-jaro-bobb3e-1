package jaro.ev3dev;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.Battery;
import ev3dev.sensors.Button;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MyFirstRobot {

    public static void main(final String[] args){

        System.out.println("Starting motor on A");
        final EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
//        System.out.println("Starting motor on B");
//        final EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(MotorPort.B);
//        mB.brake();

        mA.setSpeed(500);
        mA.forward();

//        mB.setSpeed(500);
//        mB.forward();

        Delay.msDelay(2000);
        mA.stop();
//        mB.stop();
        System.out.println("Stopped bobb3e");

        System.out.println(Battery.getInstance().getVoltage());

        Button.waitForAnyPress();

    }
}
