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
public class HingeToLoadScale extends Command {

	private boolean _cancelCommand = false;
	
	public HingeToLoadScale() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	
    	Constants.hingeToScalePidSetpoint = SmartDashboard.getNumber("Hinge To Scale PID Setpoint", Constants.hingeToScalePidSetpoint);

    	setTimeout(Constants.raiseHingeTimeout);
	    
    	/* do not move hinge into scale position unless arm is at scale height */
    	if (ArmMotor._armState == ArmMotor.ArmState.ARM_SCALE_HEIGHT)
    	{
	   		/* as soon as this command is invoked, consider the hinge out at this position in case the
	   		 * command is interrupted before it can finish */
	   		HingeMotor._hingeState = HingeMotor.HingeState.HINGE_LOAD_SCALE;
	
			/* use the raise hinge command, so the P term is strong enough */
   	    	Robot._hingeMotor.raiseHinge(Constants.hingeToScalePidSetpoint);
  		} else {
  			_cancelCommand = true;
  		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) ||
	    (_cancelCommand) || 
	    ((Math.abs(Robot._hingeMotor.getEncoderCount() - Constants.hingeToScalePidSetpoint))
    			<= (int)Constants.hingeMotorAllowableClosedLoopError))
    	{
    		return true;
    	} else {
    		return false;
    	}    	
   	}

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._hingeMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
