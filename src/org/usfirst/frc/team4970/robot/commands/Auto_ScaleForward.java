package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4970.robot.Robot;

/**
 *
 */
public class Auto_ScaleForward extends Command {

	private char _location;
	private CommandGroup _commandGroup;
	
	public Auto_ScaleForward(char robotLocation) {
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
    		if (_location == Robot.gameData.charAt(1))
	    	{
    			_commandGroup = new Auto_CloseScaleGroup(_location);
	    	} else
	    	{
	    		_commandGroup = new Auto_DriveForward();	    		
	    	}
    	} else
    	{
    		_commandGroup = new Auto_DriveForward();
    	}
    	
    	_commandGroup.start();
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
