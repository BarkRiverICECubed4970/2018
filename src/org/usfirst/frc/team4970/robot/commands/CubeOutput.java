package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class CubeOutput extends Command {

	public CubeOutput() {
        requires(Robot._intakeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.outputCubeDutyCycle = SmartDashboard.getNumber("Output Cube Duty Cycle", Constants.outputCubeDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._intakeMotor.outputCube(Constants.outputCubeDutyCycle);
    }

    protected boolean isFinished() {
   		return false;
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
