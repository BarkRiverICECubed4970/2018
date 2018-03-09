package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.Robot;

public class ClimbMotor extends Subsystem {

    public void initDefaultCommand() {
    }
    
    public void extendTape(double dutyCycle) {
    	Robot.m_climber.set(dutyCycle);
    }
    
    public void reelTape(double dutyCycle) {
    	Robot.m_climber.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	Robot.m_climber.set(0.0);
    }    
}

