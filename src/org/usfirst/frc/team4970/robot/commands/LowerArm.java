package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.CalibrationManager;

/**
 *
 */
public class LowerArm extends Command {

	public LowerArm() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CalibrationManager.lowerArmMaxDutyCycle = SmartDashboard.getNumber("Lower Arm Max Duty Cycle", CalibrationManager.lowerArmMaxDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._armMotor.lowerArm(CalibrationManager.lowerArmMaxDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
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
