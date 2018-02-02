package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;

import utils.Constants;

/**
 *
 */
public class LowerHinge extends Command {

	private boolean _cancelCommand = false;
	
	public LowerHinge() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	
    	Constants.lowerHingePidSetpoint = SmartDashboard.getNumber("Lower Hinge PID Setpoint", Constants.lowerHingePidSetpoint);

    	/* do not lower hinge unless arm is at switch height, start height, or intake height */
    	if ((ArmMotor._armState == ArmMotor.ArmState.ARM_MOVING) ||
   			(ArmMotor._armState == ArmMotor.ArmState.ARM_SWITCH_HEIGHT))
   		{
    		_cancelCommand = true;
   		} else {
   			/* as soon as this command is invoked, consider the hinge open in case the
   			 * command is interrupted before it can finish */
   			HingeMotor._hingeState = HingeMotor.HingeState.HINGE_DOWN;
   		}
   	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._hingeMotor.lowerHinge(Constants.lowerHingePidSetpoint);
    }

    protected boolean isFinished() {
   		return ((_cancelCommand) || 
   				(Robot._armMotor.getClosedLoopError() <= (int)Constants.armMotorAllowableClosedLoopError));
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
