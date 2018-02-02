package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.subsystems.DriveTrain.DriveTrainControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class HingeMotor extends Subsystem {

	public enum HingeState
	{
		HINGE_UP, HINGE_DOWN
	};
	
	public static HingeState _hingeState = HingeState.HINGE_UP;
	
	WPI_TalonSRX m_hinge = new WPI_TalonSRX(Constants.hingeMotorCanAddress);
	
	public HingeMotor() {
	   	m_hinge.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.timeoutMs);

	   	m_hinge.config_kP(0, Constants.hingeMotorPidKp, Constants.timeoutMs);
	   	m_hinge.config_kI(0, Constants.hingeMotorPidKi, Constants.timeoutMs);
	   	m_hinge.config_kD(0, Constants.hingeMotorPidKd, Constants.timeoutMs);
	   	
	   	m_hinge.configAllowableClosedloopError(0, 0, Constants.timeoutMs);	   	
	}
	
    public void initDefaultCommand() {
    }
    
    public void moveHinge(double setPoint) {
    	m_hinge.set(ControlMode.Position, setPoint);
    }
    
    public void stop() {
    	m_hinge.set(0.0);
    } 
    
    public double getEncoderCount() {
    	return m_hinge.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_hinge.getClosedLoopError(0);
    }
}

