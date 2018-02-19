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
public class DriveStraight extends Command {
	
	private double encoderAvg;
	private double inchesToDrive;
	
	public DriveStraight(double inches) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
		
		inchesToDrive = inches;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Constants.straightDriveDutyCycle = SmartDashboard.getNumber("Straight drive duty cycle", Constants.straightDriveDutyCycle);	
		Constants.driveEncoderCountsPerInch = SmartDashboard.getNumber("Drive Encoder Counts Per Inch", Constants.driveEncoderCountsPerInch);
		
		Robot._driveTrain.resetEncoders();
		encoderAvg = 0;
		
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
//		encoderAvg = (-(double)Robot._driveTrain.getLeftEncoderCount() + -(double)Robot._driveTrain.getRightEncoderCount())/2.0;
//		return (encoderAvg = (Constants.driveEncoderCountsPerInch * inchesToDrive));

		return (-1.0* Robot._driveTrain.getRightEncoderCount() > (Constants.driveEncoderCountsPerInch * inchesToDrive));
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
