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
    	
//    	addParallel(new ReleaseArmSpring());
//		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), false));
    	addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new TimedDriveStraight(SmartDashboard.getNumber("Autonomous drive to switch from center timeout", Constants.autoDriveToSwitchFromCenterTimeout), degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter)));
		
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Inches to drive for test", Constants.driveInchesForTest), -degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), false));
    }
}