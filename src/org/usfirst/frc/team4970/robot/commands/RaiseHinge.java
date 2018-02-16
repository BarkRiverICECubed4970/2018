package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;

import utils.Constants;

/**
 *
 */
public class RaiseHinge extends Command {

	public RaiseHinge() {
        requires(Robot._hingeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.raiseHingePidSetpoint = SmartDashboard.getNumber("Raise Hinge PID Setpoint", Constants.raiseHingePidSetpoint);
		Constants.raiseHingeTimeout = SmartDashboard.getNumber("Raise Hinge Timeout", Constants.raiseHingeTimeout);

    	setTimeout(Constants.raiseHingeTimeout);
    	
    	Robot._hingeMotor.raiseHinge(Constants.raiseHingePidSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) ||
    		(Robot._hingeMotor.getEncoderCount() <= (Constants.raiseHingePidSetpoint + Constants.hingeMotorAllowableClosedLoopError)))
//        	if (Robot._hingeMotor.getClosedLoopError() <= (int)Constants.hingeMotorAllowableClosedLoopError)
    	{
    		/* don't consider the hinge up until command completes */
    		HingeMotor._hingeState = HingeMotor.HingeState.HINGE_UP;
    		
    		return true;
    	} else {
    		return false;
    	}    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
