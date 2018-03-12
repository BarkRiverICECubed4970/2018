package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.commands.OperateWinch;

public class ClimbMotor extends Subsystem {

    public enum SolenoidState
    {
	WINCH_LOCKED, WINCH_UNLOCKED
    };
	
    private static SolenoidState _solenoidState = SolenoidState.WINCH_LOCKED;
	
    WPI_TalonSRX m_solenoid = new WPI_TalonSRX(Constants.solenoidMotorCanAddress);

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new OperateWinch());
    }
    
    public void lockWinch()
    {
	m_soloenoid.set(1.0);
	_solenoidState = SolenoidState.WINCH_LOCKED);
    }
	
    public void unlockWinch()
    {
	m_soloenoid.set(0.0);
	timerdelay(halfsecond);
	_solenoidState = SolenoidState.WINCH_UNLOCKED);
    }
	
    public void extendWinch(double dutyCycle) {
	if (_solenoidState == SolenoidState.WINCH_LOCKED)
	{
	    unlockWinch();
	}
	Robot.m_climber.set(dutyCycle);
    }
    
    public void reelWinch(double dutyCycle) {
	lockWinch();
	Robot.m_climber.set(dutyCycle);
    }
    
    public void stop() {
    	Robot.m_climber.set(0.0);
    }    
}

