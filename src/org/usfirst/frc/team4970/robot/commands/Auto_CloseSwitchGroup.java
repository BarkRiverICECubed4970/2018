package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_CloseSwitchGroup extends CommandGroup {
    public Auto_CloseSwitchGroup(char robotLocation) {
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
    	
   		/* switch is on our side... place the cube there */
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to close switch inches", Constants.autoDriveToCloseSwitchInches)));
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Degrees to turn", Constants.turnDegrees)));
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to fence from switch zone", Constants.autoDriveToFenceFromSwitchZone)));
		
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
    }
}