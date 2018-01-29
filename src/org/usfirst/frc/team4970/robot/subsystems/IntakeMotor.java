package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.CalibrationManager;

public class IntakeMotor extends Subsystem {

	WPI_TalonSRX m_intake = new WPI_TalonSRX(CalibrationManager.intakeMotorCanAddress);

    public void initDefaultCommand() {
    }
    
    public void intakeCube(double dutyCycle) {
    	m_intake.set(dutyCycle);
    }
    
    public void outputCube(double dutyCycle) {
    	m_intake.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	m_intake.set(0.0);
    }
    
}

