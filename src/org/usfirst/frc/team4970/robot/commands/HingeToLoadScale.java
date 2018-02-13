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
public class HingeToLoadScale extends Command {

	private boolean _cancelCommand = false;
	
	public HingeToLoadScale() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_cancelCommand = false;
    	
    	Constants.hingeToScalePidSetpoint = SmartDashboard.getNumber("Hinge To Scale PID Setpoint", Constants.hingeToScalePidSetpoint);

    	/* do not lower hinge unless arm is at switch height, start height, or intake height */
//    	if ((ArmMotor._armState == ArmMotor.ArmState.ARM_SCALE_HEIGHT) ||
//       			(ArmMotor._armState == ArmMotor.ArmState.ARM_INTAKE_HEIGHT))
//       	if (true)
//   		{
   			/* as soon as this command is invoked, consider the hinge down in case the
   			 * command is interrupted before it can finish */
   			HingeMotor._hingeState = HingeMotor.HingeState.HINGE_DOWN;

   	    	Robot._hingeMotor.lowerHinge(Constants.hingeToScalePidSetpoint);
 //  		} else {
  //  		_cancelCommand = true;
   //		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if (Robot._hingeMotor.getClosedLoopError() <= (int)Constants.hingeMotorAllowableClosedLoopError)
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
