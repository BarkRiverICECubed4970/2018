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
		HINGE_UP, HINGE_DOWN, HINGE_LOAD_SCALE, HINGE_MANUAL_MODE
	};
	
	public static HingeState _hingeState = HingeState.HINGE_UP;
	
	public WPI_TalonSRX m_hinge = new WPI_TalonSRX(Constants.hingeMotorCanAddress);
	
	public HingeMotor() {
	   	m_hinge.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);
	   	
	   	m_hinge.configNominalOutputForward(0, Constants.timeoutMs);
	   	m_hinge.configNominalOutputReverse(0, Constants.timeoutMs);
	   	m_hinge.configPeakOutputForward(1.0, Constants.timeoutMs);
	   	m_hinge.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	   	
//	   	m_hinge.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
	   	
		m_hinge.setNeutralMode(NeutralMode.Brake);
		
		Constants.hingeSecondsFromNeutral = SmartDashboard.getNumber("Hinge PID Ramp", Constants.hingeSecondsFromNeutral);
		m_hinge.configClosedloopRamp(Constants.hingeSecondsFromNeutral, Constants.timeoutMs);

		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
//		int absolutePosition = m_hinge.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
		
		/* set the quadrature (relative) sensor to match absolute */
//		m_hinge.setSelectedSensorPosition(absolutePosition, 0, Constants.timeoutMs);

		/* 
		 * for now, forget about the absolute position, just set the relative position
		 * to 0 upon startup. This means the robot must be in the same position each
		 * time it's powered up!!
		 */
		m_hinge.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    }
    
    public void moveHingeManual(double percentOutput) {
	m_hinge.set(ControlMode.PercentOutput, percentOutput);
    }
	
    public void hold()
    {
	moveHinge(getEncoderCount());    
    }
	
    public void moveHinge(double setPoint) {
    	m_hinge.set(ControlMode.Position, setPoint);
    }
    
    public void raiseHinge(double setPoint) {
    	Constants.hingeMotorPidKp = SmartDashboard.getNumber("Hinge PID KP", Constants.hingeMotorPidKp);
    	Constants.hingeMotorPidKi = SmartDashboard.getNumber("Hinge PID KI", Constants.hingeMotorPidKi);
    	Constants.hingeMotorPidKd = SmartDashboard.getNumber("Hinge PID KD", Constants.hingeMotorPidKd);
    	Constants.hingeMotorAllowableClosedLoopError = SmartDashboard.getNumber("Hinge PID Allowable Error", Constants.hingeMotorAllowableClosedLoopError);
    	
    	m_hinge.config_kP(0, Constants.hingeMotorPidKp, Constants.timeoutMs);
	   	m_hinge.config_kI(0, Constants.hingeMotorPidKi, Constants.timeoutMs);	  
	   	m_hinge.config_kD(0, Constants.hingeMotorPidKd, Constants.timeoutMs);	   	
	   	m_hinge.configAllowableClosedloopError(0, (int)Constants.hingeMotorAllowableClosedLoopError, Constants.timeoutMs);	   	

	   	Constants.hingeMotorPeakVoltage = SmartDashboard.getNumber("Hinge Peak Voltage", Constants.hingeMotorPeakVoltage);
	   	m_hinge.configPeakOutputForward(Constants.hingeMotorPeakVoltage, Constants.timeoutMs);
	   	m_hinge.configPeakOutputReverse(-Constants.hingeMotorPeakVoltage, Constants.timeoutMs);

    	moveHinge(setPoint);
    }
    
    public void lowerHinge(double setPoint) {
    	Constants.hingeMotorLowerPidKp = SmartDashboard.getNumber("Hinge Lower PID KP", Constants.hingeMotorLowerPidKp);
    	Constants.hingeMotorPidKi = SmartDashboard.getNumber("Hinge PID KI", Constants.hingeMotorPidKi);
    	Constants.hingeMotorPidKd = SmartDashboard.getNumber("Hinge PID KD", Constants.hingeMotorPidKd);
    	Constants.hingeMotorAllowableClosedLoopError = SmartDashboard.getNumber("Hinge PID Allowable Error", Constants.hingeMotorAllowableClosedLoopError);
    	
    	m_hinge.config_kP(0, Constants.hingeMotorLowerPidKp, Constants.timeoutMs);
	   	m_hinge.config_kI(0, Constants.hingeMotorPidKi, Constants.timeoutMs);	  
	   	m_hinge.config_kD(0, Constants.hingeMotorPidKd, Constants.timeoutMs);	   	
	   	m_hinge.configAllowableClosedloopError(0, (int)Constants.hingeMotorAllowableClosedLoopError, Constants.timeoutMs);	   	

	   	Constants.hingeMotorPeakVoltage = SmartDashboard.getNumber("Hinge Peak Voltage", Constants.hingeMotorPeakVoltage);
	   	m_hinge.configPeakOutputForward(Constants.hingeMotorPeakVoltage, Constants.timeoutMs);
	   	m_hinge.configPeakOutputReverse(-Constants.hingeMotorPeakVoltage, Constants.timeoutMs);

    	moveHinge(setPoint);
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
    
    public void resetEncoder() {
    	m_hinge.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
}

