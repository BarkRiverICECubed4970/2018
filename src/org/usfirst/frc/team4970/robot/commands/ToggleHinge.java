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
public class ToggleHinge extends Command {

	private boolean _cancelCommand = false;
	private HingeMotor.HingeState currentHingeState;
	
	public ToggleHinge() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentHingeState = HingeMotor._hingeState;
    	if (currentHingeState == HingeMotor.HingeState.HINGE_DOWN)
    	{	
	    	Constants.raiseHingePidSetpoint = SmartDashboard.getNumber("Raise Hinge PID Setpoint", Constants.raiseHingePidSetpoint);
	    	Robot._hingeMotor.raiseHinge(Constants.raiseHingePidSetpoint);
			System.out.println("raise hinge");
    	} else
    	{
        	_cancelCommand = false;
        	
        	Constants.lowerHingePidSetpoint = SmartDashboard.getNumber("Lower Hinge PID Setpoint", Constants.lowerHingePidSetpoint);
    		Constants.lowerHingeTimeout = SmartDashboard.getNumber("Lower Hinge Timeout", Constants.lowerHingeTimeout);

        	setTimeout(Constants.lowerHingeTimeout);
        	
        	/* do not lower hinge unless arm is at intake height */
        	if ((ArmMotor._armState == ArmMotor.ArmState.ARM_INTAKE_HEIGHT) ||
       			(ArmMotor._armState == ArmMotor.ArmState.ARM_LOCKED))
       		{
       			/* as soon as this command is invoked, consider the hinge down in case the
       			 * command is interrupted before it can finish */
       			HingeMotor._hingeState = HingeMotor.HingeState.HINGE_DOWN;

       	    	Robot._hingeMotor.lowerHinge(Constants.lowerHingePidSetpoint);
       		} else {
        		_cancelCommand = true;
       		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if (currentHingeState == HingeMotor.HingeState.HINGE_DOWN)
    	{	

	    	if (Robot._hingeMotor.getEncoderCount() <= (Constants.raiseHingePidSetpoint + Constants.hingeMotorAllowableClosedLoopError))
	//        	if (Robot._hingeMotor.getClosedLoopError() <= (int)Constants.hingeMotorAllowableClosedLoopError)
	    	{
	    		/* don't consider the hinge up until command completes */
	    		HingeMotor._hingeState = HingeMotor.HingeState.HINGE_UP;
	    		
	    		return true;
	    	} else {
	    		return false;
	    	}    	
    	} else {
           	return ((_cancelCommand) || isTimedOut());
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (currentHingeState == HingeMotor.HingeState.HINGE_UP)
    	{
    		Robot._hingeMotor.stop();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
