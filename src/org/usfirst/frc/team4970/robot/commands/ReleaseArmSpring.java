package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import utils.Constants;

import org.usfirst.frc.team4970.robot.Robot;

/**
 *
 */
public class ReleaseArmSpring extends Command {

	public ReleaseArmSpring() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(Constants.armReleaseSpringTimeout);
     	Robot._armMotor.unlockArm();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
       	return isTimedOut();
   	}

    // Called once after isFinished returns true
    protected void end() {
    	Robot._armMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
