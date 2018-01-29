package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.CalibrationManager;

public class HingeMotor extends Subsystem {

	WPI_TalonSRX m_hinge = new WPI_TalonSRX(CalibrationManager.hingeMotorCanAddress);

	public HingeMotor() {
	   	m_hinge.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}
	
    public void initDefaultCommand() {
    }
    
    public void raiseHinge(double dutyCycle) {
    	m_hinge.set(dutyCycle);
    }
    
    public void lowerHinge(double dutyCycle) {
    	m_hinge.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	m_hinge.set(0.0);
    } 
    
    public double getEncoderCount() {
    	return m_hinge.getSelectedSensorPosition(0);
    }
}

