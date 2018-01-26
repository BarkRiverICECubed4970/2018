/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import org.usfirst.frc.team4970.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4970.robot.Robot;

import utils.Gyro;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem implements PIDOutput {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public enum DriveTrainControl
	{
		Stop, Joystick, Drive_Straight, Turn_Degrees
	};
	
	private DriveTrainControl _driveTrainControl = DriveTrainControl.Stop;
    private static double PID_rotateValue;
    private static double forward;
    private static double rotate;
    private static double dutyCycleLimit;
    
    private double gyroAngle;
    private double potentialError;

    /* drive motors and differential drive */
	WPI_TalonSRX m_leftRear = new WPI_TalonSRX(2);
	WPI_TalonSRX m_leftFront = new WPI_TalonSRX(3);
	WPI_TalonSRX m_rightRear = new WPI_TalonSRX(4);
	WPI_TalonSRX m_rightFront = new WPI_TalonSRX(5);
    
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);
	
    private final DifferentialDrive _robotDrive = new DifferentialDrive(m_left, m_right);

	private Gyro _gyro = new Gyro(m_leftRear);
	
    private final PIDController gyroPid = new PIDController(0.010, 0, 0, _gyro, this);
	
	public DriveTrain()
	{
    	m_leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	m_rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());		
	}
	
    public void controlLoop(DriveTrainControl commandInControl)
    {
    	_driveTrainControl = commandInControl;
    	
    	switch (_driveTrainControl) {
    	
	    	case Joystick:
    			forward = Robot.m_oi.joystick.getRawAxis(1);
	    		rotate = Robot.m_oi.joystick.getRawAxis(0);
	    		break;

	    	case Turn_Degrees:
	    		forward = 0.0;
	    		rotate = PID_rotateValue;	    		
	    		break;
	    		
	    	case Drive_Straight:
	    		forward = Robot.straightDriveDutyCycle;
	    		rotate = PID_rotateValue;
	    		break;
	    		
	    	case Stop:
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;

	    	default:
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;
    	}

    	dutyCycleLimit = SmartDashboard.getNumber("Max Drive DutyCycle",1.0);

		if (Math.abs(forward) > dutyCycleLimit)
    	{
    		forward = Math.copySign(dutyCycleLimit, forward);    	
    	}
		
		if (Math.abs(rotate) > dutyCycleLimit)
    	{
    		rotate = Math.copySign(dutyCycleLimit, rotate);    	
    	}
  
		/* try this to potentially turn better with only high gear */
//		if (_driveTrainControl == DriveTrainControl.Turn_Degrees)
//		{
//			if (rotate < 0.0)
//			{
//				_robotDrive.tankDrive(Math.abs(rotate), 0.0);
//			} else
//			{
//				_robotDrive.tankDrive(0.0, Math.abs(rotate));				
//			}
//		} else
		{
			_robotDrive.arcadeDrive(forward, rotate, false);
		}
    }
    
    public int getRightEncoderCount()
    {
    	return m_rightFront.getSelectedSensorPosition(0);
    }
    
    public int getLeftEncoderCount()
    {
    	return m_leftFront.getSelectedSensorPosition(0);
    }
    
    public double getGyroHeading()
    {
    	return _gyro.getAngle();
    }
    
    public void setupGyroPID()
    {
    	Robot.gyroPidKp = SmartDashboard.getNumber("Gyro PID KP", Robot.gyroPidKp);
    	Robot.gyroPidKi = SmartDashboard.getNumber("Gyro PID KI", Robot.gyroPidKi);
    	Robot.gyroPidKd = SmartDashboard.getNumber("Gyro PID KD", Robot.gyroPidKd);
    	Robot.gyroPidMinIn = SmartDashboard.getNumber("Gyro PID Min Input", Robot.gyroPidMinIn);
    	Robot.gyroPidMaxIn = SmartDashboard.getNumber("Gyro PID Max Input", Robot.gyroPidMaxIn);
    	Robot.gyroPidMinOut = SmartDashboard.getNumber("Gyro PID Min Output", Robot.gyroPidMinOut);
    	Robot.gyroPidMaxOut = SmartDashboard.getNumber("Gyro PID Max Output", Robot.gyroPidMaxOut);
    	Robot.gyroPidTolerance = SmartDashboard.getNumber("Gyro PID Tolerance", Robot.gyroPidTolerance);
    	Robot.gyroPidMaxSetpoint = SmartDashboard.getNumber("Gyro PID Max Setpoint", Robot.gyroPidMaxSetpoint);

    	gyroPid.reset();
		gyroPid.setPID(Robot.gyroPidKp, Robot.gyroPidKi , Robot.gyroPidKd);
		gyroPid.setInputRange(Robot.gyroPidMinIn, Robot.gyroPidMaxIn);
		gyroPid.setOutputRange(Robot.gyroPidMinOut, Robot.gyroPidMaxOut);
		gyroPid.setAbsoluteTolerance(Robot.gyroPidTolerance);
		gyroPid.setSetpoint(0.0);
		
		/*
		 *  commands should be calling this, but call this just in case
		 *  since the pidSetpoint is initialized to 0.0 for ramping purposes
		 */
		_gyro.reset();
		
		gyroPid.enable();
    }

    public void setGyroPidSetpoint(double setPoint)
    {
    	gyroAngle = _gyro.getAngle();
    	potentialError = setPoint - gyroAngle;
    	
    	/* 
    	 * the first check represents the possible error if the PID were fed
    	 * the input parameter "setPoint". If this potential error is too large, 
    	 * then reduce it to something that won't go wild with a more aggressive 
    	 * kP term
    	 */
    	if ((Math.abs(potentialError)) > Robot.gyroPidMaxSetpoint)
    	{    		
    		/*
    		 * if setpoint is greater than angle, then add the max setpoint to the
    		 * current angle, or else subtract the max setpoint from the current
    		 * angle
    		 */
    		gyroPid.setSetpoint((Math.copySign(Robot.gyroPidMaxSetpoint, potentialError) + gyroAngle));
    	} else
    	{
    		gyroPid.setSetpoint(setPoint);    	    		
    	}
    }
    
    static int onTargetCount = 0;
    static final int onTargetThresh = 10;
    public boolean gyroPidOnTarget()
    {
    	if (Math.abs(gyroPid.getError()) < Robot.gyroPidTolerance)
    	{
    		onTargetCount++;
    		if (onTargetCount >= onTargetThresh)
    		{
    			onTargetCount = 0;
    			return true;
    		}
    	} else 
    	{
    		onTargetCount = 0;
    		return false;
    	}
    	
    	return false;
    }
    
    public void resetOnTargetCount()
    {
    	onTargetCount = 0;
    }
    
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		PID_rotateValue = output;
	}
	
	public double getPidOutput()
	{
		return PID_rotateValue;
	}
}
