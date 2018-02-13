package org.usfirst.frc.team4970.robot.commands;
import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class EitherSwitch extends CommandGroup {
    public EitherSwitch(char robotLocation) {
    	double degreeMultiplier = 1.0;
    	
    	/* 
    	 * Assume we are starting on the left side.
    	 * If we are starting on the right side, perform
    	 * all turns exactly the opposite as our assumption
    	 */
    	if (robotLocation == 'R')
    	{
    		degreeMultiplier = -1.0;
    	}
    	
    	addParallel(new ArmToSwitchGroup());
    	
    	if ((Robot.gameData != null) && (Robot.gameData.length() > 0))
    	{
	    	if (robotLocation == Robot.gameData.charAt(0))
	    	{
	    		/* switch is on our side... place the cube there */
	    		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to close switch inches", Constants.autoDriveToCloseSwitchInches)));
	   			addSequential(new TurnDegrees(degreeMultiplier * Constants.switchDegrees));
	    		addSequential(new CubeOutputTimed());	   			
	    	} else {
	    		/* switch is not on our side... need to drive around the other side */
	    		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive past switch inches", Constants.autoDrivePastSwitchInches)));
	   			addSequential(new TurnDegrees(degreeMultiplier * Constants.switchDegrees));
	    		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive across switch inches", Constants.autoDriveAcrossSwitchInches)));
	   			addSequential(new TurnDegrees(degreeMultiplier * Constants.switchDegrees));
	    		addSequential(new CubeOutputTimed());	   				    		
	    	}
    	} else {
    		addSequential(new DriveStraight(Constants.autoDriveStraightAutoInches));
    	}
    }
}