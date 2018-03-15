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
    	if (switchLocation == 'L')
    	{
    		degreeMultiplier = -1.0;
    	}
    	
    	addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to fence from center", Constants.autoDriveToFenceFromCenter), 
   										degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from side", Constants.autoTurnDegreesFromSide), 
   										false));
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Autonomous reverse drive inches", Constants.autoReverseDriveInches), 
   											   -degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), 
   											   false));
    }
}