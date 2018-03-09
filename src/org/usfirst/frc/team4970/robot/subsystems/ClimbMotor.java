package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.commands.OperateWinch;

public class ClimbMotor extends Subsystem {

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new OperateWinch());
    }
    
    public void operateWinch() {
    	Robot.m_climber.set(Robot.m_oi.joystick.getRawAxis(5));
    }
    
    public void stop() {
    	Robot.m_climber.set(0.0);
    }    
}

