package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;

import utils.Constants;

/**
 *
 */
public class ArmToSwitchPosition extends Command {

	private boolean _cancelCommand = false;
	
	public ArmToSwitchPosition() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	Constants.switchPositionArmPidSetpoint = SmartDashboard.getNumber("Switch Position Arm PID Setpoint", Constants.switchPositionArmPidSetpoint);

    	/* don't attempt to move the arm up or down when the hinge is not closed */
    	if (HingeMotor._hingeState != HingeMotor.HingeState.HINGE_UP)
    	{
    		_cancelCommand = true;
    	} else {
        	/* indicate that the arm is about to move, so the hinge cannot */
        	ArmMotor._armState = ArmMotor.ArmState.ARM_MOVING;    		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._armMotor.moveArm(Constants.switchPositionArmPidSetpoint);
    }

    protected boolean isFinished() {
    	if (Robot._armMotor.getClosedLoopError() <= (int)Constants.armMotorAllowableClosedLoopError)
    	{
    		/* don't consider the hinge up until command completes */
    		ArmMotor._armState = ArmMotor.ArmState.ARM_SWITCH_HEIGHT;
    		return true;
    	} else if (_cancelCommand)
    	{
    		return true;
    	} else {
    		return false;
    	}    	
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
