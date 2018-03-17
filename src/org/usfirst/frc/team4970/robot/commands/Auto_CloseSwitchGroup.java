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
    	
   		/* switch is on our side... place the cube there */
    	addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous straight drive to close switch inches", Constants.autoStraightDriveToCloseSwitchInches), 
				 	  -20.0, false));		

//   		addSequential(new TurnDegrees(-80.0));

   		//   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous angle drive to close switch inches", Constants.autoAngleDriveToCloseSwitchInches), 
//				 	  degreeMultiplier*SmartDashboard.getNumber("Autonomous switch turn degrees from side", Constants.autoSwitchTurnDegreesFromSide), 
//				 	  false));		
		addSequential(new CubeOutputTimed(SmartDashboard.getNumber("Output Cube Duty Cycle", Constants.outputCubeDutyCycle)));
   		addSequential(new StopArm());
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Autonomous reverse drive inches", Constants.autoReverseDriveInches), 
   					  -degreeMultiplier*SmartDashboard.getNumber("Autonomous switch turn degrees from side", Constants.autoSwitchTurnDegreesFromSide), 
   					  false));
    }
}
