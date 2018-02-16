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
public class ArmToScalePosition extends Command {

	private boolean _cancelCommand = false;
	
	public ArmToScalePosition() {
        requires(Robot._armMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	Constants.scalePositionArmPidSetpoint = SmartDashboard.getNumber("Arm Scale PID Setpoint", Constants.scalePositionArmPidSetpoint);

		Constants.armToScaleTimeout = SmartDashboard.getNumber("Arm To Scale Timeout", Constants.armToScaleTimeout);

    	setTimeout(Constants.armToScaleTimeout);

    	/* don't attempt to move the arm up or down when the hinge is not closed */
    	if ((HingeMotor._hingeState != HingeMotor.HingeState.HINGE_UP) ||
    		(ArmMotor._armState == ArmMotor.ArmState.ARM_SCALE_HEIGHT))
    	{
    		_cancelCommand = true;
    	} else {
        	/* indicate that the arm is about to move, so the hinge cannot */
        	ArmMotor._armState = ArmMotor.ArmState.ARM_MOVING;    		
        	Robot._armMotor.raiseArm(Constants.scalePositionArmPidSetpoint);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._armMotor.getEncoderCount() - Constants.scalePositionArmPidSetpoint))
    			<= (int)Constants.armMotorAllowableClosedLoopError))
    	{
    		/* don't consider the hinge up until command completes */
    		ArmMotor._armState = ArmMotor.ArmState.ARM_SCALE_HEIGHT;
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
//    	Robot._armMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
