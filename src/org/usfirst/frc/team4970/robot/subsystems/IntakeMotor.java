package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.Constants;

public class IntakeMotor extends Subsystem {

	WPI_TalonSRX m_intake1 = new WPI_TalonSRX(Constants.intakeMotor1CanAddress);
	WPI_TalonSRX m_intake2 = new WPI_TalonSRX(Constants.intakeMotor2CanAddress);
	
	public IntakeMotor() {
		m_intake2.follow(m_intake1);
	}
	
    public void initDefaultCommand() {
    }
    
    public void intakeCube(double dutyCycle) {
    	m_intake1.set(dutyCycle);
    }
    
    public void outputCube(double dutyCycle) {
    	m_intake1.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	m_intake1.set(0.0);
    }
    
}

