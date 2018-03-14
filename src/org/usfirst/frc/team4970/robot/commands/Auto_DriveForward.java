package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utils.Constants;

/**
 *
 */
public class Auto_DriveForward extends CommandGroup {
    public Auto_DriveForward() {

    	addParallel(new ReleaseArmSpring());
    	addSequential(new DriveStraight(SmartDashboard.getNumber("Autonomous drive inches", Constants.autoDriveStraightAutoInches), 0.0, false));    	
    }
}