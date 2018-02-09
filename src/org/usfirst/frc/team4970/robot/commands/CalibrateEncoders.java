package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4970.robot.Robot;

/**
 *
 */
public class CalibrateEncoders extends Command {

	boolean isComplete;
	
	public CalibrateEncoders() {
        requires(Robot._hingeMotor);
        requires(Robot._armMotor);
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	isComplete = false;
    	Robot._armMotor.calibrateAbsolutePosition();
    	Robot._hingeMotor.calibrateAbsolutePosition();
    	isComplete = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	return isComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
