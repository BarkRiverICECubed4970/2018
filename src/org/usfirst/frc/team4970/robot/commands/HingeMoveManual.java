package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;

import utils.Constants;

/**
 *
 */
public class HingeMoveManual extends Command {

	public HingeMoveManual() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.hingeManualDutyCycle = SmartDashboard.getNumber("Hinge Manual Move Duty Cycle", Constants.hingeManualDutyCycle);
    	
    	Robot._hingeMotor.moveHingeManual(Constants.hingeManualDutyCycle);

    	HingeMotor._hingeState = HingeMotor.HingeState.HINGE_MANUAL_MODE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._hingeMotor.moveHingeManual(Constants.hingeManualDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot._hingeMotor.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
