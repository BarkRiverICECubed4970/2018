package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;

import utils.Constants;

/**
 *
 */
public class StopHinge extends Command {

	private boolean _cancelCommand = false;
	
	public StopHinge() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   	    Robot._hingeMotor.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._hingeMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
