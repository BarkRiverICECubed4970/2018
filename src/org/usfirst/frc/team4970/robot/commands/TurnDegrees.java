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

/**
 * An example command.  You can replace me with your own command.
 */
public class TurnDegrees extends Command {

	private double desiredAngle;
	
	public TurnDegrees(double degrees) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
		
		desiredAngle = degrees;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
    	Robot.turnDegreesTimeout = SmartDashboard.getNumber("Turn Degrees Timeout", Robot.turnDegreesTimeout);
    	if (SmartDashboard.getNumber("Turn Degrees Override", 0.0) == 1.0)
    	{
    		desiredAngle = SmartDashboard.getNumber("Turn Degrees", desiredAngle);
    	}
    	setTimeout(Robot.turnDegreesTimeout);
    	Robot._driveTrain.resetOnTargetCount();
    	Robot._driveTrain.setupGyroPID();
    	// redundant, since setupGyroPID() does this already
    	Robot._driveTrain.setGyroPidSetpoint(0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.Turn_Degrees);
    	// continue to set this, since this function will ramp the setpoint
    	Robot._driveTrain.setGyroPidSetpoint(desiredAngle);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Robot._driveTrain.gyroPidOnTarget() || isTimedOut());
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.Stop);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
