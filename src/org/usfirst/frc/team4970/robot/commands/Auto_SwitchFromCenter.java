package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_SwitchFromCenter extends CommandGroup {
    public Auto_SwitchFromCenter(char switchLocation) {
    	double degreeMultiplier = 1.0;
    	
    	/* 
    	 * Assume the switch is on the left side.
    	 */
    	if (switchLocation == 'R')
    	{
    		degreeMultiplier = -1.0;
    	}
    	
  //  	addParallel(new ReleaseArmSpring());
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), false));
    	addParallel(new ArmToSwitchGroup());
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to fence from center", Constants.autoDriveToFenceFromCenter), false));
		
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
    }
}