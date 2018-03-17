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
   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive past switch inches", Constants.autoDrivePastSwitchInches), 0.0, false));
//   		addSequential(new TurnDegrees(degreeMultiplier*-90.0, false));
   		addSequential(new RaiseHinge());
   		addSequential(new ArmToIntakePosition());   		

   		addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive across scale inches", Constants.autoDriveAcrossScaleInches), degreeMultiplier * -90.0, false));
		addSequential(new TurnDegrees(-degreeMultiplier * SmartDashboard.getNumber("Autonomous opposite scale degrees to turn", Constants.autoOppositeScaleTurnDegrees), false));

//		addSequential(new ArmToScalePosition());   		

//    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to null zone", Constants.autoDriveToNullZone), 0.0, false));
//    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive to null zone", Constants.autoDriveToNullZone), 0.0, false));
    	
//		addSequential(new CubeOutputTimed(1.0));
//   		addSequential(new DriveStraightReverse(SmartDashboard.getNumber("Autonomous reverse drive inches", Constants.autoReverseDriveInches), 
//					  -degreeMultiplier*SmartDashboard.getNumber("Autonomous switch turn degrees from side", Constants.autoSwitchTurnDegreesFromSide), 
//					  false));
//		addSequential(new StopArm());

    }
}
