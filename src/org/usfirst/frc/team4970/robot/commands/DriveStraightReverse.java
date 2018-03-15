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
public class DriveStraightReverse extends Command {
	
//	private double encoderAvg;
	private double inchesToDrive;
	private double _gyroPidSetpoint;
	private boolean testButton;
	
	public DriveStraightReverse(double inches, double gyroPidSetpoint, boolean testOverride) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);

		
		inchesToDrive = inches;
		_gyroPidSetpoint = gyroPidSetpoint;
		testButton = testOverride;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Constants.straightDriveStartDutyCycle = SmartDashboard.getNumber("Straight drive start duty cycle", Constants.straightDriveStartDutyCycle);	
		Constants.driveEncoderCountsPerInch = SmartDashboard.getNumber("Drive Encoder Counts Per Inch", Constants.driveEncoderCountsPerInch);

		if (testButton == true)
		{
			inchesToDrive = SmartDashboard.getNumber("Inches to drive for test", Constants.driveInchesForTest);
			_gyroPidSetpoint = SmartDashboard.getNumber("Drive straight angle for test", Constants.driveStraightAngleForTest);
		}
		
		Robot._driveTrain.resetEncoders();
//		encoderAvg = 0;
		
		Robot._driveTrain.setupGyroPID(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT_REVERSE);
		Robot._driveTrain.setGyroPidSetpoint(_gyroPidSetpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT_REVERSE, Constants.straightDriveStartDutyCycle);
		// continue to set this, since this function will ramp the setpoint
    	Robot._driveTrain.setGyroPidSetpoint(_gyroPidSetpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
//		encoderAvg = ((double)Robot._driveTrain.getLeftEncoderCount() + (double)Robot._driveTrain.getRightEncoderCount())/2.0;
		
//		return (encoderAvg >= (Constants.driveEncoderCountsPerInch * inchesToDrive));
		return (Robot._driveTrain.getRightEncoderCount() < -(Constants.driveEncoderCountsPerInch * inchesToDrive));
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.STOP, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
