package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_CloseScaleGroup extends CommandGroup {
    public Auto_CloseScaleGroup(char robotLocation) {
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
    	
//    	addParallel(new ReleaseArmSpring());
    	
	/* hold the hinge up while driving */
    	addSequential(new RaiseHinge());
	    
    	addSequential(new Auto_ArmToScalePosition());
    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous straight drive to close scale inches", Constants.autoStraightDriveToCloseScaleInches), 
				0.0, false));
    	addSequential(new TurnDegrees(degreeMultiplier*SmartDashboard.getNumber("Autonomous close scale turn", Constants.autoCloseScaleDegrees), false, 2.0));
    	addSequential(new DriveStraight(2.0, 0.0, false));
    	addSequential(new DriveStraight(2.0, 0.0, false));
		addSequential(new CubeOutputTimed(SmartDashboard.getNumber("Output Scale Cube Duty Cycle", Constants.outputScaleCubeDutyCycle)));
   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Autonomous reverse drive inches", Constants.autoReverseDriveInches), 
				   	  -degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees to close scale", Constants.autoTurnDegreesToCloseScale), 
				   	  false));
   		addSequential(new StopArm());
    }
}
