/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.DriveTrain;

import utils.Constants;

/**
 * An example command.  You can replace me with your own command.
 */
public class TimedDriveStraight extends Command {
	
	private double timeout;
	
	public TimedDriveStraight(double timedDriveTimeout) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
		
		timeout = timedDriveTimeout;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Constants.straightDriveDutyCycle = SmartDashboard.getNumber("Straight drive duty cycle", Constants.straightDriveDutyCycle);	

    	setTimeout(timeout);
		
		Robot._driveTrain.resetEncoders();
		
		Robot._driveTrain.setupGyroPID();
		/* redundant... the setup function should call this */
    	Robot._driveTrain.setGyroPidSetpoint(Robot._driveTrain.getGyroHeading());
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (isTimedOut());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.STOP);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
