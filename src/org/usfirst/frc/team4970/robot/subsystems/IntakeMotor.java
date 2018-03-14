package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class IntakeMotor extends Subsystem {

	private double intakeDc = 0.0;
	private double outputDc = 0.0;
	
	WPI_TalonSRX m_intake1 = new WPI_TalonSRX(Constants.intakeMotor1CanAddress);
	WPI_TalonSRX m_intake2 = new WPI_TalonSRX(Constants.intakeMotor2CanAddress);
	
	public IntakeMotor() {
		m_intake2.setInverted(true);
//		m_intake2.follow(m_intake1);
		
		m_intake1.setNeutralMode(NeutralMode.Brake);
		m_intake2.setNeutralMode(NeutralMode.Brake);
		
    	m_intake1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);
	   	
    	m_intake1.configNominalOutputForward(0, Constants.timeoutMs);
    	m_intake1.configNominalOutputReverse(0, Constants.timeoutMs);
    	m_intake1.configPeakOutputForward(1.0, Constants.timeoutMs);
    	m_intake1.configPeakOutputReverse(-1.0, Constants.timeoutMs);
    	
    	m_intake2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);
	   	
    	m_intake2.configNominalOutputForward(0, Constants.timeoutMs);
    	m_intake2.configNominalOutputReverse(0, Constants.timeoutMs);
    	m_intake2.configPeakOutputForward(1.0, Constants.timeoutMs);
    	m_intake2.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    	hold();
    }
    
    public void intakeCube(double maxDutyCycle) {
    	intakeDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(4));

//    	if (intakeDc > maxDutyCycle)
//    	{
//    		intakeDc = maxDutyCycle;
//    	}
    	
//    	intakeDc = 1.0;
    	m_intake1.set(ControlMode.PercentOutput, intakeDc);
    	m_intake2.set(intakeDc);
    }
    
    public void intakeCubeSlow(double dutyCycle) {
    	m_intake1.set(ControlMode.PercentOutput, dutyCycle);
    	m_intake2.set(dutyCycle);
    }

    public void outputCube(double maxDutyCycle) {
    	outputDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(3));
 
//    	if (outputDc > maxDutyCycle)
//    	{
//    		outputDc = maxDutyCycle;
//    	}
    	
//    	outputDc = 1.0;
    	if (HingeMotor._hingeState == HingeMotor.HingeState.HINGE_DOWN) 
    	{
    		m_intake1.set(ControlMode.PercentOutput, -1.0*outputDc);
    		m_intake2.set(-1.0*outputDc);
    	} else {
    		m_intake1.set(ControlMode.PercentOutput, outputDc);    		
    		m_intake2.set(outputDc);    		
    	}
    }

    public void outputCubeAuton(double maxDutyCycle) {
    		m_intake1.set(ControlMode.PercentOutput, maxDutyCycle);    		
    		m_intake2.set(ControlMode.PercentOutput, maxDutyCycle);    		
    }

    public void outputCubeSlow(double dutyCycle) {
    	if (HingeMotor._hingeState == HingeMotor.HingeState.HINGE_DOWN) 
    	{
    		m_intake1.set(ControlMode.PercentOutput, -1.0*dutyCycle);
    		m_intake2.set(-1.0*dutyCycle);
    	} else {
    		m_intake1.set(ControlMode.PercentOutput, dutyCycle);    		
    		m_intake2.set(dutyCycle);    		
    	}
    }
 
    public void stop() {
    	m_intake1.set(ControlMode.PercentOutput, 0.0);
    	m_intake2.set(ControlMode.PercentOutput, 0.0);
    }

    public void hold() {
    	m_intake1.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    	m_intake2.setSelectedSensorPosition(0, 0, Constants.timeoutMs);

    	Constants.intakeMotorPidKp = SmartDashboard.getNumber("Intake PID KP", Constants.intakeMotorPidKp);
    	Constants.intakeMotorPidKi = SmartDashboard.getNumber("Intake PID KI", Constants.intakeMotorPidKi);
    	Constants.intakeMotorPidKd = SmartDashboard.getNumber("Intake PID KD", Constants.intakeMotorPidKd);
    	Constants.intakeMotorAllowableClosedLoopError = SmartDashboard.getNumber("Intake PID Allowable Error", Constants.intakeMotorAllowableClosedLoopError);
    	
    	m_intake1.config_kP(0, Constants.intakeMotorPidKp, Constants.timeoutMs);
    	m_intake1.config_kI(0, Constants.intakeMotorPidKi, Constants.timeoutMs);	  
    	m_intake1.config_kD(0, Constants.intakeMotorPidKd, Constants.timeoutMs);	   	
    	m_intake1.configAllowableClosedloopError(0, (int)Constants.intakeMotorAllowableClosedLoopError, Constants.timeoutMs);	   	

//    	m_intake1.set(ControlMode.Position, m_intake1.getSelectedSensorPosition(0));
    	
    	m_intake2.config_kP(0, Constants.intakeMotorPidKp, Constants.timeoutMs);
    	m_intake2.config_kI(0, Constants.intakeMotorPidKi, Constants.timeoutMs);	  
    	m_intake2.config_kD(0, Constants.intakeMotorPidKd, Constants.timeoutMs);	   	
    	m_intake2.configAllowableClosedloopError(0, (int)Constants.intakeMotorAllowableClosedLoopError, Constants.timeoutMs);	   	

//    	m_intake2.set(ControlMode.Position, m_intake2.getSelectedSensorPosition(0));
    	m_intake2.set(ControlMode.Position, 0);
    	m_intake1.set(ControlMode.Position, 0);
    }
    
    public double getEncoderCount() {
    	return m_intake1.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_intake1.getClosedLoopError(0);
    }
    
    public double getMotorOutputVoltage() {
    	return m_intake1.getMotorOutputVoltage();
    }

}

