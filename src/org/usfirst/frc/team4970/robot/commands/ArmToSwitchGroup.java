package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmToSwitchGroup extends CommandGroup {
    public ArmToSwitchGroup() {
    	addSequential(new RaiseHinge());
   		addSequential(new ArmToSwitchPosition());
    }
}