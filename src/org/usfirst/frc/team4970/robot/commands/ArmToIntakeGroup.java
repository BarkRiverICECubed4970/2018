package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmToIntakeGroup extends CommandGroup {
    public ArmToIntakeGroup() {
    	addSequential(new RaiseHinge());
   		addSequential(new ArmToIntakePosition());
//   		addSequential(new LowerHinge());
    }
}