package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_FarScaleGroup extends CommandGroup {
    public Auto_FarScaleGroup(char robotLocation) {
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
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive past switch inches", Constants.autoDrivePastSwitchInches)));
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Degrees to turn", Constants.turnDegrees)));
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive across switch inches", Constants.autoDriveAcrossSwitchInches)));
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous opposite scale degrees to turn", Constants.autoOppositeScaleTurnDegrees)));

    	addParallel(new ArmToScaleGroup());
    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to null zone", Constants.autoDriveToNullZone)));
    	
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
    }
}