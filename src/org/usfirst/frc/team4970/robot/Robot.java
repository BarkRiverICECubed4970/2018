/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4970.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4970.robot.subsystems.IntakeMotor;
import org.usfirst.frc.team4970.robot.subsystems.HingeMotor;
import org.opencv.core.Mat;
import org.usfirst.frc.team4970.robot.commands.Auto_DriveForward;
import org.usfirst.frc.team4970.robot.commands.Auto_EitherScale;
import org.usfirst.frc.team4970.robot.commands.Auto_EitherSwitch;
import org.usfirst.frc.team4970.robot.commands.Auto_SwitchScaleForward;
import org.usfirst.frc.team4970.robot.commands.Auto_SwitchScaleOScale;
import org.usfirst.frc.team4970.robot.commands.Auto_SwitchScaleOSwitch;
import org.usfirst.frc.team4970.robot.commands.DriveStraight;
import org.usfirst.frc.team4970.robot.commands.ReleaseArmSpring;
import org.usfirst.frc.team4970.robot.commands.TestAutoCommand;
import org.usfirst.frc.team4970.robot.subsystems.ArmMotor;
import org.usfirst.frc.team4970.robot.subsystems.ClimbMotor;
import utils.Constants;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final DriveTrain _driveTrain = new DriveTrain();
	public static final IntakeMotor _intakeMotor = new IntakeMotor();
	public static final HingeMotor _hingeMotor = new HingeMotor();
	public static final ArmMotor _armMotor = new ArmMotor();
	public static final ClimbMotor _climbMotor = new ClimbMotor();

	public static OI m_oi;

	public static String gameData;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public static Constants _calibrationManager;
	
	private static UsbCamera usbCamera;
	
    /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		m_oi = new OI();
		
		_calibrationManager = new Constants();
		
//		m_chooser.addDefault("All Positions: Drive Forward", new DriveStraight(Constants.autoDriveStraightAutoInches));
		m_chooser.addDefault("All Positions: Drive Forward", new Auto_DriveForward());

		m_chooser.addObject("Left Position: Switch Either Side", new Auto_EitherSwitch('L'));
		m_chooser.addObject("Right Position: Switch Either Side", new Auto_EitherSwitch('R'));
		m_chooser.addObject("Center Position: Switch Either Side", new Auto_EitherSwitch('C'));
		
		m_chooser.addObject("Left Position: Switch, Scale, Forward", new Auto_SwitchScaleForward('L'));
		m_chooser.addObject("Left Position: Switch, Scale, Opposite Scale", new Auto_SwitchScaleOScale('L'));
		m_chooser.addObject("Left Position: Switch, Scale, Opposite Switch", new Auto_SwitchScaleOSwitch('L'));
		m_chooser.addObject("Left Position: Scale Either Side", new Auto_EitherScale('L'));

		m_chooser.addObject("Right Position: Switch, Scale, Forward", new Auto_SwitchScaleForward('R'));
		m_chooser.addObject("Right Position: Switch, Scale, Opposite Scale", new Auto_SwitchScaleOScale('R'));
		m_chooser.addObject("Right Position: Switch, Scale, Opposite Switch", new Auto_SwitchScaleOSwitch('R'));
		m_chooser.addObject("Right Position: Scale Either Side", new Auto_EitherScale('R'));

		m_chooser.addObject("Display Position and Game Data", new TestAutoCommand());
        m_chooser.addObject("Release Spring and Do Nothing", new ReleaseArmSpring());
        // instantiate the command used for the autonomous period

		SmartDashboard.putData("Auto mode", m_chooser);	

		CameraServer.getInstance().startAutomaticCapture();

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
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		/* bump the arm down to release the spring */
		new ReleaseArmSpring();
		
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
}
