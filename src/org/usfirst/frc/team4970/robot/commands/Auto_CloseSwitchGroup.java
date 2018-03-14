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
    	
 //   	addParallel(new ReleaseArmSpring());
    	
   		/* switch is on our side... place the cube there */
    	addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new TimedDriveStraight(SmartDashboard.getNumber("Autonomous drive to switch from center timeout", Constants.autoDriveToSwitchFromCenterTimeout), degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from side", Constants.autoTurnDegreesFromSide)));
		
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Inches to drive for test", Constants.driveInchesForTest), -degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), false));
    }
}
