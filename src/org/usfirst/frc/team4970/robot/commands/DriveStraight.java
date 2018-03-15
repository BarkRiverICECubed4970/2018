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
	
//	private double encoderAvg;
	private double encoderCount;
	private double encoderTarget;
	private double encoderRampDownTarget;
	private double inchesToDrive;
	private boolean testButton;
	private double startDutyCycle;
	private double endDutyCycle;
	private double dutyCycle;
	private double encoderRampDownRatio;
	private double rampDownInches;
	private double _gyroPidSetpoint;
	
	public DriveStraight(double inches, double gyroPidSetpoint, boolean testOverride) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
		
		inchesToDrive = inches;
		testButton = testOverride;
		_gyroPidSetpoint = gyroPidSetpoint;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		startDutyCycle = SmartDashboard.getNumber("Straight drive start duty cycle", Constants.straightDriveStartDutyCycle);	
		endDutyCycle = SmartDashboard.getNumber("Straight drive final duty cycle", Constants.straightDriveFinalDutyCycle);	
		rampDownInches = SmartDashboard.getNumber("Straight drive ramp down inches", Constants.straightDriveInchesForRampDownBegin);
		Constants.driveEncoderCountsPerInch = SmartDashboard.getNumber("Drive Encoder Counts Per Inch", Constants.driveEncoderCountsPerInch);

		if (testButton == true)
		{
			inchesToDrive = SmartDashboard.getNumber("Inches to drive for test", Constants.driveInchesForTest);
			_gyroPidSetpoint = SmartDashboard.getNumber("Drive straight angle for test", Constants.driveStraightAngleForTest);
		}
		
		Robot._driveTrain.resetEncoders();
//		encoderAvg = 0;
		
		/* target encoder count to drive to */
		encoderTarget = Constants.driveEncoderCountsPerInch * inchesToDrive;
		/* encoder count to begin slowing down */
		encoderRampDownTarget = Math.max(0.0, (encoderTarget - Constants.driveEncoderCountsPerInch * rampDownInches));
		/* how much to decrease the duty cycle per inch (but in terms of encoder counts) */
		encoderRampDownRatio = (startDutyCycle - endDutyCycle)/(rampDownInches * Constants.driveEncoderCountsPerInch);
		
		Robot._driveTrain.setupGyroPID(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT);
		Robot._driveTrain.setDriveTrainBrakeMode(true);
		Robot._driveTrain.setGyroPidSetpoint(_gyroPidSetpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		dutyCycle = startDutyCycle;
		encoderCount = Robot._driveTrain.getRightEncoderCount();
		if (encoderCount > encoderRampDownTarget)
		{
		     dutyCycle = startDutyCycle - encoderRampDownRatio * (encoderCount - encoderRampDownTarget);
		     dutyCycle = Math.max(dutyCycle, endDutyCycle);
		}
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT, dutyCycle);
    	// continue to set this, since this function will ramp the setpoint
    	Robot._driveTrain.setGyroPidSetpoint(_gyroPidSetpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
//		encoderAvg = (-(double)Robot._driveTrain.getLeftEncoderCount() + -(double)Robot._driveTrain.getRightEncoderCount())/2.0;
//		return (encoderAvg = (Constants.driveEncoderCountsPerInch * inchesToDrive));

		return (Robot._driveTrain.getRightEncoderCount() > encoderTarget);
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
