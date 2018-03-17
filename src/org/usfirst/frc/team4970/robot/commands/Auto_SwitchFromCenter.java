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
    	double degreeAdder = 0.0;
    	double distanceAdder = 0.0;
    	
    	/* 
    	 * Assume the switch is on the left side.
    	 */
    	if (switchLocation == 'L')
    	{
    		degreeMultiplier = -1.0;

    		degreeAdder = SmartDashboard.getNumber("Autonomous switch from center left degree adder", Constants.autoSwitchFromCenterLeftDegreeAdder);
    		distanceAdder = SmartDashboard.getNumber("Autonomous switch from center left distance adder", Constants.autoSwitchFromCenterLeftDistanceAdder);
    	}
    	
    	addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new DriveStraight(distanceAdder + SmartDashboard.getNumber("Autonomous drive to fence from center", Constants.autoDriveToFenceFromCenter), 
   										degreeAdder + degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), 
   										false));
   		addSequential(new TurnDegrees(degreeMultiplier*15.0,false));
   		addSequential(new CubeOutputTimed(SmartDashboard.getNumber("Output Cube Duty Cycle", Constants.outputCubeDutyCycle)));
   		addSequential(new StopArm());
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Autonomous reverse drive inches", Constants.autoReverseDriveInches), 
   											   -degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), 
   											   false));
    }
}