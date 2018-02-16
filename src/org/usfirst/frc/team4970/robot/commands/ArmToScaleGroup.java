package org.usfirst.frc.team4970.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmToScaleGroup extends CommandGroup {
    public ArmToScaleGroup() {
    	addSequential(new RaiseHinge());
   		addSequential(new ArmToScalePosition());
//   		addSequential(new HingeToLoadScale());
    }
}