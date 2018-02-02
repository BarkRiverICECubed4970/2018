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
public class RaiseHinge extends Command {

	private boolean _cancelCommand = false;
	
	public RaiseHinge() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	
    	Constants.raiseHingePidSetpoint = SmartDashboard.getNumber("Raise Hinge PID Set Point", Constants.raiseHingePidSetpoint);

    	/* do not lower hinge unless arm is at switch height, start height, or intake height */
    	if ((ArmMotor._armState == ArmMotor.ArmState.ARM_MOVING) ||
   			(ArmMotor._armState == ArmMotor.ArmState.ARM_SWITCH_HEIGHT))
   		{
    		_cancelCommand = true;
   		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._hingeMotor.raiseHinge(CalibrationManager.raiseHingeMaxDutyCycle);
    }

    protected boolean isFinished() {
    	if (Robot._hingeMotor.getClosedLoopError() <= (int)Constants.hingeMotorAllowableClosedLoopError)
    	{
    		/* as soon as this command is invoked, consider the hinge open in case the
    		 * command is interrupted before it can finish */
    		HingeMotor._hingeState = HingeMotor.HingeState.HINGE_UP;
    		return true;
    	} else if (_cancelCommand) {
    		return true;
    	} else {
    		return false;
    	}    	
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
