package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;

import utils.Constants;

/**
 *
 */
public class ArmMoveManual extends Command {

	public ArmMoveManual() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      	Constants.armManualDutyCycle = SmartDashboard.getNumber("Arm Manual Move Duty Cycle", Constants.armManualDutyCycle);
    	
    	Robot._armMotor.moveArmPercentOutputMode(Constants.armManualDutyCycle);

    	ArmMotor._armState = ArmMotor.ArmState.ARM_MOVING;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._armMotor.moveArmPercentOutputMode(Constants.armManualDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._armMotor.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
