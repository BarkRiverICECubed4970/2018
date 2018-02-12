package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class TestAutoCommand extends Command {

	public TestAutoCommand() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(Robot.gameData.length() > 0)
        {
          SmartDashboard.putString("Game Data", Robot.gameData);
//		  if(Robot.gameData.charAt(0) == 'L')
//		  {
			//Put left auto code here
//		  } else {
			//Put right auto code here
//		  }
        }


    	Constants.extendTapeDutyCycle = SmartDashboard.getNumber("Extend Tape Duty Cycle", Constants.extendTapeDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._climbMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
