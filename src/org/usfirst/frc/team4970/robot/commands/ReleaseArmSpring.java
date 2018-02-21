package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;

import utils.Constants;

/**
 *
 */
public class ReleaseArmSpring extends Command {

	public ReleaseArmSpring() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Constants.armReleaseSpringDutyCycle = SmartDashboard.getNumber("Arm Release Spring Duty Cycle", Constants.armReleaseSpringDutyCycle);
		Constants.armReleaseSpringTimeout = SmartDashboard.getNumber("Arm Release Spring Timeout", Constants.armReleaseSpringTimeout);

    	setTimeout(Constants.armReleaseSpringTimeout);
    	
     	Robot._armMotor.lowerArmPercentOutputMode(Constants.armReleaseSpringDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
       	return (isTimedOut());
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
