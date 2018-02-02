package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.subsystems.HingeMotor.HingeState;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class ArmMotor extends Subsystem {

	public enum ArmState
	{
		ARM_START_HEIGHT, ARM_INTAKE_HEIGHT, ARM_SWITCH_HEIGHT, ARM_SCALE_HEIGHT, ARM_MOVING
	};
	
	public static ArmState _armState = ArmState.ARM_START_HEIGHT;
	
	WPI_TalonSRX m_arm = new WPI_TalonSRX(Constants.armMotorCanAddress);

	public ArmMotor() {
	   	m_arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.timeoutMs);

	   	m_arm.config_kP(0, Constants.hingeMotorPidKp, Constants.timeoutMs);
	   	m_arm.config_kI(0, Constants.hingeMotorPidKi, Constants.timeoutMs);
	   	m_arm.config_kD(0, Constants.hingeMotorPidKd, Constants.timeoutMs);
	   	
	   	m_arm.configAllowableClosedloopError(0, 0, Constants.timeoutMs);	   	
	}
	
    public void initDefaultCommand() {
    }
    
    public void raiseArm(double setpoint) {
    	m_arm.set(ControlMode.Position, setpoint);
    }
    
    public void lowerArm(double setpoint) {
    	m_arm.set(ControlMode.Position, setpoint);
    }
    
    public void stop() {
    	m_arm.set(0.0);
    }
    
    public double getEncoderCount() {
    	return m_arm.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_arm.getClosedLoopError(0);
    }
}

