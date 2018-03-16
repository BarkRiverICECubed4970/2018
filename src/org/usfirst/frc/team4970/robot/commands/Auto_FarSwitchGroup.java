package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_FarSwitchGroup extends CommandGroup {
    public Auto_FarSwitchGroup(char robotLocation) {
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
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive past switch inches", Constants.autoDrivePastSwitchInches), 0.0, false));
//		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous 90 degree turn", Constants.autoNinetyDegrees), false));
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive across switch inches", Constants.autoDriveAcrossSwitchInches), degreeMultiplier*SmartDashboard.getNumber("Autonomous 90 degree turn", Constants.autoNinetyDegrees), false));
   		addSequential(new Auto_ArmToSwitchPosition());
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to fence from switch zone", Constants.autoDriveToFenceFromSwitchZone), degreeMultiplier*SmartDashboard.getNumber("Autonomous turn degrees from center", Constants.autoTurnDegreesFromCenter), false));

		addSequential(new CubeOutputTimed(SmartDashboard.getNumber("Output Cube Duty Cycle", Constants.outputCubeDutyCycle)));
   		addSequential(new StopArm());
    }
}
