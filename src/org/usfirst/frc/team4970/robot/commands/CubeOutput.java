package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.CalibrationManager;

/**
 *
 */
public class CubeOutput extends Command {

	public CubeOutput() {
        requires(Robot._intakeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CalibrationManager.outputCubeDutyCycle = SmartDashboard.getNumber("Output Cube Duty Cycle", CalibrationManager.outputCubeDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._intakeMotor.outputCube(CalibrationManager.outputCubeDutyCycle);
    }

    protected boolean isFinished() {
   		return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._intakeMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
