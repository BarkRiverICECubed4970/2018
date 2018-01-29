package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import utils.CalibrationManager;

public class ClimbMotor extends Subsystem {

	WPI_TalonSRX m_climb = new WPI_TalonSRX(CalibrationManager.climbMotorCanAddress);

    public void initDefaultCommand() {
    }
    
    public void extendTape(double dutyCycle) {
    	m_climb.set(dutyCycle);
    }
    
    public void reelTape(double dutyCycle) {
    	m_climb.set(-1.0*dutyCycle);
    }
    
    public void stop() {
    	m_climb.set(0.0);
    }    
}

