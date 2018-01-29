package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.CalibrationManager;

public class ArmMotor extends Subsystem {

	WPI_TalonSRX m_arm = new WPI_TalonSRX(CalibrationManager.armMotorCanAddress);

	public ArmMotor() {
	   	m_arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}
	
    public void initDefaultCommand() {
    }
    
    public void raiseArm(double dutyCycle) {
    	m_arm.set(dutyCycle);
    }
    
    public void lowerArm(double dutyCycle) {
    	m_arm.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	m_arm.set(0.0);
    }
    
    public double getEncoderCount() {
    	return m_arm.getSelectedSensorPosition(0);
    }
}

