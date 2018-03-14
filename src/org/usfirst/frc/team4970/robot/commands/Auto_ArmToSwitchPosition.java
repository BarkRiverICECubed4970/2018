package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;

import utils.Constants;

/**
 *
 */
public class Auto_ArmToSwitchPosition extends Command {

	public Auto_ArmToSwitchPosition() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.switchPositionArmPidSetpoint = SmartDashboard.getNumber("Arm Switch PID Setpoint", Constants.switchPositionArmPidSetpoint);

   		Robot._armMotor.raiseArm(Constants.switchPositionArmPidSetpoint);    			
        		
       	/* indicate that the arm is about to move, so the hinge cannot */
       	ArmMotor._armState = ArmMotor.ArmState.ARM_SWITCH_HEIGHT;    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
   		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._armMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
