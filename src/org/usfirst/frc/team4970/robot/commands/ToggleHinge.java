package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;

/**
 *
 */
public class ToggleHinge extends Command {

	private Command _moveHinge;
	
	public ToggleHinge() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (HingeMotor._hingeState == HingeMotor.HingeState.HINGE_DOWN)
    	{	
    		_moveHinge = new RaiseHinge();
    		_moveHinge.start();
    	} else
    	{
        	/* do not lower hinge unless arm is at intake height or still locked */
        	if ((ArmMotor._armState == ArmMotor.ArmState.ARM_INTAKE_HEIGHT) ||
       			(ArmMotor._armState == ArmMotor.ArmState.ARM_LOCKED))
       		{
        		_moveHinge = new LowerHinge();
        		_moveHinge.start();
       		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
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
