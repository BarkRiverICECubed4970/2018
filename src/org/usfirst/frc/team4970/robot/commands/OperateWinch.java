package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4970.robot.Robot;

/**
 *
 */
public class OperateWinch extends Command {

	public OperateWinch() {

        requires(Robot._climbMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._climbMotor.operateWinch();
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
