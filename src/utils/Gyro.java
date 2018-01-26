package utils;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GyroBase;

public class Gyro extends GyroBase {
	
	private static PigeonIMU _pigeon;
	private double [] xyz_dps = new double[3];
	
	public Gyro (WPI_TalonSRX _talon)
	{
		_pigeon = new PigeonIMU(_talon);
	}
	
    public double getRate()
    {
    	_pigeon.getRawGyro(xyz_dps);
    	return xyz_dps[2];
    }
    
    public double getAngle()
    {
    	return _pigeon.getFusedHeading();
    }
    
    public void calibrate()
    {
    	return;
    }
    
    public void reset() 
    {
    	_pigeon.setFusedHeading(0.0, 0);
    }
}
