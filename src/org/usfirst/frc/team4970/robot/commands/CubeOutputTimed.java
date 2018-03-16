package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class CubeOutputTimed extends Command {

	private double timeout;
	private double dc;
	
	public CubeOutputTimed(double dutyCycle) {
        requires(Robot._intakeMotor);
        dc = dutyCycle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Constants.outputCubeDutyCycle = SmartDashboard.getNumber("Output Cube Duty Cycle", Constants.outputCubeDutyCycle);
    	timeout = SmartDashboard.getNumber("Cube Output Auto Timeout", Constants.cubeOutputAutoTimeout);    		

    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._intakeMotor.outputCubeAuton(dc);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._intakeMotor.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
