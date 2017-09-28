package jaro.ev3dev;

import ev3dev.sensors.Battery;
import ev3dev.sensors.ev3.EV3IRSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class JaroIRSensor {

    private static int HALF_SECOND = 500;

    public static void main(final String[] args) throws Exception {
        System.out.println("IR sensor test");

        final EV3IRSensor ir1 = new EV3IRSensor(SensorPort.S1);

        final SampleProvider sp = ir1.getDistanceMode();
        int distanceValue = 0;
        for(int i = 0; i <= 50; i++) {

            float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            distanceValue = (int)sample[0];

            System.out.format("[%d] %d%n", i, distanceValue);

            Delay.msDelay(HALF_SECOND);
        }

        System.out.println(Battery.getInstance().getVoltage());
    }

}
