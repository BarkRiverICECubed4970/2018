package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class ReelTape extends Command {

	public ReelTape() {

        requires(Robot._climbMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.reelTapeDutyCycle = SmartDashboard.getNumber("Reel Tape Duty Cycle", Constants.reelTapeDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._climbMotor.reelTape(Constants.reelTapeDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._climbMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
