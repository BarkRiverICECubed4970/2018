package utils;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Gyro {
	
	private static PigeonIMU _pigeon;
	
	public Gyro (WPI_TalonSRX _talon)
	{
		_pigeon = new PigeonIMU(_talon);
	}
	
    public double captureTargetHeading()
    {
    	return _pigeon.getFusedHeading();
    }

}
