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
    	
  //  	addParallel(new ReleaseArmSpring());
    	
   		/* switch is on our side... place the cube there */
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive past switch inches", Constants.autoDrivePastSwitchInches), false));
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous 90 degree turn", Constants.autoNinetyDegrees), false));
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive across scale inches", Constants.autoDriveAcrossScaleInches), false));
		addSequential(new TurnDegrees(degreeMultiplier * SmartDashboard.getNumber("Autonomous opposite scale degrees to turn", Constants.autoOppositeScaleTurnDegrees), false));

    	addParallel(new ArmToScaleGroup());
    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to null zone", Constants.autoDriveToNullZone), false));
    	
		addSequential(new CubeOutputTimed());
   		addSequential(new StopArm());
    }
}
