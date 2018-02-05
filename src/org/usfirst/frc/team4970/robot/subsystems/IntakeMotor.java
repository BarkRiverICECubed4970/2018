package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class IntakeMotor extends Subsystem {

	private double intakeDc = 0.0;
	private double outputDc = 0.0;
	
	WPI_TalonSRX m_intake1 = new WPI_TalonSRX(Constants.intakeMotor1CanAddress);
	WPI_TalonSRX m_intake2 = new WPI_TalonSRX(Constants.intakeMotor2CanAddress);
	
	public IntakeMotor() {
		m_intake2.follow(m_intake1);
	}
	
    public void initDefaultCommand() {
    }
    
    public void intakeCube(double maxDutyCycle) {
    	intakeDc = Robot.m_oi.joystick.getRawAxis(3);
    	if (intakeDc > maxDutyCycle)
    	{
    		intakeDc = maxDutyCycle;
    	}
    	m_intake1.set(intakeDc);
    }
    
    public void outputCube(double maxDutyCycle) {
    	outputDc = Robot.m_oi.joystick.getRawAxis(2);
 
    	if (outputDc > maxDutyCycle)
    	{
    		outputDc = maxDutyCycle;
    	}
    	
    	if (HingeMotor._hingeState == HingeMotor.HingeState.HINGE_DOWN)
    	{
    		m_intake1.set(-1.0*outputDc);
    	} else {
    		m_intake1.set(outputDc);    		
    	}
    }
    
    public void stop() {
    	m_intake1.set(0.0);
    }
    
}

