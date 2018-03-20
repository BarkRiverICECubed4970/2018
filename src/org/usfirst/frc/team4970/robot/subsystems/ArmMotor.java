package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class ArmMotor extends Subsystem {

	public enum ArmState
	{
		ARM_LOCKED, ARM_START_HEIGHT, ARM_INTAKE_HEIGHT, ARM_SWITCH_HEIGHT, ARM_SCALE_HEIGHT, ARM_MOVING
	};
	
	public static ArmState _armState = ArmState.ARM_LOCKED;
	
	WPI_TalonSRX m_arm = new WPI_TalonSRX(Constants.armMotorCanAddress);

	public ArmMotor() {
		m_arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);

//		m_arm.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
		
	   	m_arm.configNominalOutputForward(0, Constants.timeoutMs);
	   	m_arm.configNominalOutputReverse(0, Constants.timeoutMs);
	   	m_arm.configPeakOutputForward(Constants.armMotorPeakRaiseVoltage, Constants.timeoutMs);
	   	m_arm.configPeakOutputReverse(-Constants.armMotorPeakRaiseVoltage, Constants.timeoutMs);

	   	m_arm.setSensorPhase(false);
	   	m_arm.setInverted(true);
	   	
	   	m_arm.setNeutralMode(NeutralMode.Brake);

		Constants.armSecondsFromNeutral = SmartDashboard.getNumber("Arm PID Ramp", Constants.armSecondsFromNeutral);
		m_arm.configClosedloopRamp(Constants.armSecondsFromNeutral, Constants.timeoutMs);

	   	/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
//		int absolutePosition = m_arm.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
		
		/* sensor phase inverted */
//		absolutePosition *= -1;

		/* set the quadrature (relative) sensor to match absolute */
//		m_arm.setSelectedSensorPosition(absolutePosition, 0, Constants.timeoutMs);

		/* 
		 * for now, forget about the absolute position, just set the relative position
		 * to 0 upon startup. This means the robot must be in the same position each
		 * time it's powered up!!
		 */
		m_arm.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    }
    
    private void moveArm(double setpoint) {
    	m_arm.set(ControlMode.Position, setpoint);
    }

    public void raiseArm(double setPoint) {  
    	
    	if (_armState == ArmState.ARM_LOCKED)
    	{
    		unlockArm();
    	}
    	
    	Constants.armMotorPidKp = SmartDashboard.getNumber("Arm PID KP", Constants.armMotorPidKp);
    	Constants.armMotorPidKi = SmartDashboard.getNumber("Arm PID KI", Constants.armMotorPidKi);
    	Constants.armMotorPidKd = SmartDashboard.getNumber("Arm PID KD", Constants.armMotorPidKd);
    	Constants.armMotorRaisePidKf = SmartDashboard.getNumber("Arm Raise PID KF", Constants.armMotorRaisePidKf);
    	Constants.armMotorAllowableClosedLoopError = SmartDashboard.getNumber("Arm PID Allowable Error", Constants.armMotorAllowableClosedLoopError);

	   	m_arm.config_kP(0, Constants.armMotorPidKp, Constants.timeoutMs);
	   	m_arm.config_kI(0, Constants.armMotorPidKi, Constants.timeoutMs);
	   	m_arm.config_kD(0, Constants.armMotorPidKd, Constants.timeoutMs);
	   	m_arm.config_kF(0, Constants.armMotorRaisePidKf, Constants.timeoutMs);

	   	Constants.armMotorPeakRaiseVoltage = SmartDashboard.getNumber("Arm Raise Peak Voltage", Constants.armMotorPeakRaiseVoltage);
	   	m_arm.configPeakOutputForward(Constants.armMotorPeakRaiseVoltage, Constants.timeoutMs);
	   	/* 
	   	 * no reason to try and push the arm down, let gravity do its job.
	   	 * except for the intake position, where the arm motor is working 
	   	 * against the shock (but this is handled in a different function)
	   	 */
	   	m_arm.configPeakOutputReverse(0, Constants.timeoutMs);

	   	m_arm.configAllowableClosedloopError(0, (int)Constants.armMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	

	   	moveArm(setPoint);
    }
    
    public void lowerArmInit()
    {
    	if (_armState == ArmState.ARM_LOCKED)
    	{
    		unlockArm();
    	}
    	
    	Constants.armMotorLowerPidKp = SmartDashboard.getNumber("Arm Lower PID KP", Constants.armMotorLowerPidKp);
    	Constants.armMotorPidKi = SmartDashboard.getNumber("Arm PID KI", Constants.armMotorPidKi);
    	Constants.armMotorPidKd = SmartDashboard.getNumber("Arm PID KD", Constants.armMotorPidKd);
    	Constants.armMotorLowerPidKf = SmartDashboard.getNumber("Arm Lower PID KF", Constants.armMotorLowerPidKf);
 	   	Constants.armMotorAllowableClosedLoopError = SmartDashboard.getNumber("Arm PID Allowable Error", Constants.armMotorAllowableClosedLoopError);
	   	m_arm.config_kP(0, Constants.armMotorLowerPidKp, Constants.timeoutMs);
	   	m_arm.config_kI(0, Constants.armMotorPidKi, Constants.timeoutMs);
	   	m_arm.config_kD(0, Constants.armMotorPidKd, Constants.timeoutMs);
	   	m_arm.config_kF(0, Constants.armMotorLowerPidKf, Constants.timeoutMs);
	   	
	   	Constants.armMotorPeakLowerVoltage = SmartDashboard.getNumber("Arm Lower Peak Voltage", Constants.armMotorPeakLowerVoltage);

	   	/*
	   	 * we are fighting a shock to press down to intake position... no need to 
	   	 * ever go up with the arm motor
	   	 */
	   	m_arm.configPeakOutputForward(0, Constants.timeoutMs);
	   	m_arm.configPeakOutputReverse(-Constants.armMotorPeakLowerVoltage, Constants.timeoutMs);

	   	m_arm.configAllowableClosedloopError(0, (int)Constants.armMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	
    }
    
    public void lowerArm(double setPoint) {

		if ((m_arm.getSelectedSensorPosition(0) < Constants.armMotorLowerArmPidEntryPoint) ||
			(m_arm.getSelectedSensorPosition(0) < setPoint))
		{	
			moveArm(setPoint);
		} else {
			stop();
		}
    }
    
    public void moveArmPercentOutputMode(double percentOutput)
    {
    	m_arm.set(ControlMode.PercentOutput, percentOutput);    	
    }
    
    public void hold ()
    {
	    moveArm(getEncoderCount());
    }
	
    public void unlockArm()
    {
   		moveArmPercentOutputMode(Constants.armReleaseSpringDutyCycle);
   		Timer.delay(Constants.armReleaseSpringTimeout);
   		stop();
   		_armState = ArmState.ARM_START_HEIGHT;
    }
    
    public void stop() {
    	m_arm.set(ControlMode.PercentOutput, 0.0);
    }
    
    public double getEncoderCount() {
    	return m_arm.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_arm.getClosedLoopError(0);
    }

    public double getMotorOutputVoltage() {
    	return m_arm.getMotorOutputVoltage();
    }
    
    public String getState() {
    	return _armState.toString();
    }
    
    public void resetEncoder() {
    	m_arm.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
}

