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
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import org.usfirst.frc.team4970.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import org.usfirst.frc.team4970.robot.Robot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public enum DriveTrainControl
	{
		Stop, Joystick, Drive_Straight, Turn_Degrees
	};
	
	private DriveTrainControl _driveTrainControl = DriveTrainControl.Stop;
    private static double forward;
    private static double rotate;
    private static double dutyCycleLimit;
    
    /* drive motors and differential drive */
	WPI_TalonSRX m_leftRear = new WPI_TalonSRX(2);
	WPI_TalonSRX m_leftFront = new WPI_TalonSRX(3);
	WPI_TalonSRX m_rightRear = new WPI_TalonSRX(4);
	WPI_TalonSRX m_rightFront = new WPI_TalonSRX(5);
    
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);
	
    private final DifferentialDrive _robotDrive = new DifferentialDrive(m_left, m_right);

	public PigeonIMU _pigeon = new PigeonIMU(m_leftRear);
	
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
	    		
	    		break;
	    		
	    	case Drive_Straight:
	    		forward = Robot.straightDriveDutyCycle;
//	    		rotate = PID_rotateValue;
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
    	
    	_robotDrive.arcadeDrive(forward, rotate, false);
    	
    }
    
    public double captureTargetHeading()
    {
    	return _pigeon.getFusedHeading();
    }
    
    public int getRightEncoderCount()
    {
    	return m_rightFront.getSelectedSensorPosition(0);
    }
    
    public int getLeftEncoderCount()
    {
    	return m_leftFront.getSelectedSensorPosition(0);
    }
}
