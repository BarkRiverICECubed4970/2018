/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4970.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final DriveTrain _driveTrain
			= new DriveTrain();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

    public static double turnDegrees = -60.0;
    public static double driveInches = 48;
    public static double straightDriveDutyCycle = 0.4;
    
    public static double gyroPidKp = 0.05;
    public static double gyroPidKi = 0.0;
    public static double gyroPidKd = 0.0;
    public static double gyroPidMinIn = -60.0;
    public static double gyroPidMaxIn = 60.0;
    public static double gyroPidMinOut = -1.0;
    public static double gyroPidMaxOut = 1.0;
    public static double gyroPidTolerance = 4.0;
    public static double gyroPidMaxSetpoint = 8;
    
    /* counts per revolution on output shaft * inches per revolution from tires */
    public static double driveEncoderCountsPerInch = 1000;

    public static double turnDegreesTimeout = 3.0;

    
    /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();

		
//		m_chooser.addDefault("Default Auto", new DriveWithJoystick());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		new Thread(() -> {
	    	while (true) {
	    		updateSmartDashboard();
	    		Timer.delay(0.5);
	    	}
	    }).start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Joystick forward", Robot.m_oi.joystick.getRawAxis(1));
		SmartDashboard.putNumber("Joystick rotate", Robot.m_oi.joystick.getRawAxis(0));
		SmartDashboard.putNumber("Pigeon fused heading", Robot._driveTrain.getGyroHeading());
		SmartDashboard.putNumber("Degrees to turn", Robot.turnDegrees);
		SmartDashboard.putNumber("Inches to drive", Robot.driveInches);
		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive duty cycle", Robot.straightDriveDutyCycle);
		SmartDashboard.putNumber("Max Drive DutyCycle",1.0);
		SmartDashboard.putNumber("Right Encoder Count", Robot._driveTrain.getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder Count", Robot._driveTrain.getLeftEncoderCount());
		SmartDashboard.putNumber("Gyro PID output value", Robot._driveTrain.getPidOutput());
    	SmartDashboard.putNumber("Gyro PID KP", gyroPidKp);
    	SmartDashboard.putNumber("Gyro PID KI", gyroPidKi);
    	SmartDashboard.putNumber("Gyro PID KD", gyroPidKd);
    	SmartDashboard.putNumber("Gyro PID Min Input", gyroPidMinIn);
    	SmartDashboard.putNumber("Gyro PID Max Input", gyroPidMaxIn);
    	SmartDashboard.putNumber("Gyro PID Min Output", gyroPidMinOut);
    	SmartDashboard.putNumber("Gyro PID Max Output", gyroPidMaxOut);
    	SmartDashboard.putNumber("Gyro PID Tolerance", gyroPidTolerance);
    	SmartDashboard.putNumber("Gyro PID Max Setpoint", gyroPidMaxSetpoint);
    	SmartDashboard.putNumber("Drive Encoder Counts Per Inch", driveEncoderCountsPerInch);
    	SmartDashboard.putNumber("Turn Degrees Timeout", turnDegreesTimeout);

	}
}
