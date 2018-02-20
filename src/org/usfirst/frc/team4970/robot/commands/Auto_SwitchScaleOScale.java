package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import utils.Constants;

import org.usfirst.frc.team4970.robot.Robot;

/**
 *
 */
public class Auto_SwitchScaleOScale extends Command {

	private char _location;
	
	public Auto_SwitchScaleOScale(char robotLocation) {
		_location = robotLocation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {   
    	if (Robot.gameData == null)
		{
			Robot.gameData = DriverStation.getInstance().getGameSpecificMessage();	
		}
    
    	if ((Robot.gameData != null) && (Robot.gameData.length() > 0))
    	{
    		if (_location == Robot.gameData.charAt(0))
	    	{
	    		new Auto_CloseSwitchGroup(_location);
	    	} else if (_location == Robot.gameData.charAt(1))
	    	{
	    		new Auto_CloseScaleGroup(_location);
	    	} else
	    	{
	    		new Auto_FarScaleGroup(_location);	    		
	    	}
    	} else
    	{
    		new Auto_DriveForward();
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
