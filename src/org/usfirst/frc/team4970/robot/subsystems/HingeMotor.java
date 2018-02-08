package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class HingeMotor extends Subsystem {

	public enum HingeState
	{
		HINGE_UP, HINGE_DOWN
	};
	
	public static HingeState _hingeState = HingeState.HINGE_UP;
	
	public WPI_TalonSRX m_hinge = new WPI_TalonSRX(Constants.hingeMotorCanAddress);
	
	public HingeMotor() {
	   	m_hinge.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.timeoutMs);
	   	
	   	m_hinge.configNominalOutputForward(0, Constants.timeoutMs);
	   	m_hinge.configNominalOutputReverse(0, Constants.timeoutMs);
	   	m_hinge.configPeakOutputForward(1.0, Constants.timeoutMs);
	   	m_hinge.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	   	
		m_hinge.setNeutralMode(NeutralMode.Brake);
	}
	
    public void initDefaultCommand() {
    }
    
    public void moveHinge(double setPoint) {
    	Constants.hingeMotorPidKp = SmartDashboard.getNumber("Hinge PID KP", Constants.hingeMotorPidKp);
    	Constants.hingeMotorPidKi = SmartDashboard.getNumber("Hinge PID KP", Constants.hingeMotorPidKi);
    	Constants.hingeMotorPidKd = SmartDashboard.getNumber("Hinge PID KP", Constants.hingeMotorPidKd);
    	Constants.hingeMotorAllowableClosedLoopError = SmartDashboard.getNumber("Hinge PID Allowable Error", Constants.hingeMotorAllowableClosedLoopError);
    	
    	m_hinge.config_kP(0, Constants.hingeMotorPidKp, Constants.timeoutMs);
	   	m_hinge.config_kI(0, Constants.hingeMotorPidKi, Constants.timeoutMs);	  
	   	m_hinge.config_kD(0, Constants.hingeMotorPidKd, Constants.timeoutMs);	   	
	   	m_hinge.configAllowableClosedloopError(0, (int)Constants.hingeMotorAllowableClosedLoopError, Constants.timeoutMs);	   	

    	m_hinge.set(ControlMode.Position, setPoint);
    }
    
    public void raiseHinge(double dutyCycle) {
    	m_hinge.set(ControlMode.PercentOutput, -dutyCycle);
    }
    
    public void lowerHinge(double dutyCycle) {
    	m_hinge.set(ControlMode.PercentOutput, dutyCycle);
    }
    
    public void stop() {
    	m_hinge.set(ControlMode.PercentOutput, 0.0);
    } 
    
    public double getEncoderCount() {
    	return m_hinge.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_hinge.getClosedLoopError(0);
    }
    
    public double getMotorOutputVoltage() {
    	return m_hinge.getMotorOutputVoltage();
    }
    
    public String getState() {
    	return _hingeState.toString();
    }
}

