/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import org.usfirst.frc.team4970.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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
		Stop, Joystick, Pigeon
	};
	
	private DriveTrainControl _driveTrainControl = DriveTrainControl.Stop;
    private static double forward;
    private static double rotate;

	PigeonIMU _pigeon;
	
    /* drive motors and differential drive */
	TalonSRX m_leftFront = new TalonSRX(2);
	TalonSRX m_leftRear = new TalonSRX(3);
	TalonSRX m_rightFront = new TalonSRX(4);
	TalonSRX m_rightRear = new TalonSRX(5);
    
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);
	
    private final DifferentialDrive _robotDrive = new DifferentialDrive(m_left, m_right);
    
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

	    	case Stop:
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;

	    	default:
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;
    	}

    	robotDrive41.arcadeDrive(forward, rotate, false);
    	
    }
}
