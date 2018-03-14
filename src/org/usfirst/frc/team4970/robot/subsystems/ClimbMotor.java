package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.commands.OperateWinch;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ClimbMotor extends Subsystem {

    public enum SolenoidState
    {
	WINCH_LOCKED, WINCH_UNLOCKED
    };
	
    public enum WinchState
    {
	WINCH_START, WINCH_OUT
    };
	
    private static SolenoidState _solenoidState = SolenoidState.WINCH_LOCKED;
    private static WinchState _winchState = WinchState.WINCH_START;
    private static double _winchExtendCounter = 0;
	
	WPI_TalonSRX m_climber = new WPI_TalonSRX(Constants.climbMotorCanAddress);

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new OperateWinch());
    }
    
    public void lockWinch()
    {
		Robot.m_solenoid.set(0.0);
		_solenoidState = SolenoidState.WINCH_LOCKED;
    }
	
    public void unlockWinch()
    {
		Robot.m_solenoid.set(1.0);
		Timer.delay(Constants.unlockWinchTimeout);
		_solenoidState = SolenoidState.WINCH_UNLOCKED;
    }
	
    public void extendWinch(double dutyCycle) {
		if (_solenoidState == SolenoidState.WINCH_LOCKED)
		{
		    unlockWinch();
		}
		m_climber.set(dutyCycle);
	        _winchExtendCounter++;
	    	
	        if (_winchExtendCounter > Constants.winchOutCount)
		{
		    _winchState = WinchState.WINCH_OUT;
		}
    }
    
    public void reelWinch(double dutyCycle) {
	/* don't allow the winch to be reeled in until it has been extended */
    	if (_winchState == WinchState.WINCH_OUT)
    	{	
    		lockWinch();
    		m_climber.set(dutyCycle);
    	}
    }
    
    public void stop() {
    	m_climber.set(0.0);
    }  
	
    public double getWinchOutCount() {
        return _winchExtendCounter;
    }
}

