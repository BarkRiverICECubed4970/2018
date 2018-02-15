package utils;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	
	/* CAN Addresses for Motor Controllers */
	public static final int leftRearDriveMotorCanAddress = 2;
	public static final int leftFrontDriveMotorCanAddress = 3;
	public static final int rightRearDriveMotorCanAddress = 4;
	public static final int rightFrontDriveMotorCanAddress = 5;
	public static final int armMotorCanAddress = 6;
	public static final int hingeMotorCanAddress = 7;
	public static final int intakeMotor1CanAddress = 8;
	public static final int intakeMotor2CanAddress = 9;
	public static final int climbMotorCanAddress = 10;
	public static final int gyroControllerCanAddress = 12;
	
    public static double turnDegrees = -60.0;
    public static double switchDegrees = 90.0;
    public static double driveInchesForTest = 60.0;
    public static double autoDriveStraightAutoInches = 72.0;
    public static double autoDriveToCloseSwitchInches = 36.0;
    public static double autoDrivePastSwitchInches = 36.0;
    public static double autoDriveAcrossSwitchInches = 36.0;
    public static double scaleInches = 100.0;
    public static double straightDriveDutyCycle = 0.4;
    public static double armDownMaxDriveDutyCycle = 0.6;
    public static double armUpMaxDriveDutyCycle = 0.4;
    public static double straightDriveRateLimit = 0.01;
    /* counts per revolution on output shaft * inches per revolution from tires
     * 
     *  6 inch diameter wheels. 
     *  inches/rotation = 6 * pi = 18.849555
     *  counts/inch = counts/rotation / inches/rotation
     *  
     *  */
    public static double driveEncoderCountsPerInch = 53.05;
    
    
    public static double turnDegreesTimeout = 3.0;

    public static double gyroPidKp = 0.05;
    public static double gyroPidKi = 0.0;
    public static double gyroPidKd = 0.0;
    public static double gyroPidMinIn = -60.0;
    public static double gyroPidMaxIn = 60.0;
    public static double gyroPidMinOut = -1.0;
    public static double gyroPidMaxOut = 1.0;
    public static double gyroPidTolerance = 4.0;
    public static double gyroPidMaxSetpoint = 8;
    
    public static double hingeMotorPidKp = 0.75;
    public static double hingeMotorLowerPidKp = 0.3;
    public static double hingeMotorPidKi = 0.0;
    public static double hingeMotorPidKd = 0.0;
    public static double hingeMotorAllowableClosedLoopError = 10;
    public static double raiseHingePidSetpoint = 0.0;
    public static double hingeToScalePidSetpoint = 1500.0;
    public static double lowerHingePidSetpoint = 1400.0;
    public static double lowerHingeTimeout = 1.0;
    public static double hingeSecondsFromNeutral = 0.5;
    public static double hingeMotorPeakVoltage = 0.7;

//    public static double raiseHingePidSetpoint = 0.3;
//    public static double lowerHingePidSetpoint = 0.3;

    public static double intakeMotorPidKp = 0.5;
    public static double intakeMotorPidKi = 0.0;
    public static double intakeMotorPidKd = 0.0;
    public static double intakeMotorAllowableClosedLoopError = 10;
    public static double cubeOutputAutoTimeout = 1.0;
    
    
    public static double armMotorPidKp = 3.0;
    public static double armMotorLowerPidKp = 2.0;
    public static double armMotorPidKi = 0.0;
    public static double armMotorPidKd = 0.0;
    public static double armMotorRaisePidKf = 0.0;
    public static double armMotorLowerPidKf = 0.0;
    public static double armMotorAllowableClosedLoopError = 10;
    public static double armSecondsFromNeutral = 0.5;
    public static double intakePositionArmPidSetpoint = 0.0;
    public static double switchPositionArmPidSetpoint = 500.0;
    public static double armMotorLowerArmPidEntryPoint = 200.0;
    public static double scalePositionArmPidSetpoint = 1200.0;
    public static double armMotorPeakRaiseVoltage = 0.7;
    public static double armMotorPeakLowerVoltage = 0.5;
    
    public static double intakeCubeDutyCycle = 1.0;
    public static double outputCubeDutyCycle = 1.0;
    public static double extendTapeDutyCycle = 0.3;
    public static double reelTapeDutyCycle = 0.3;

    public static final int timeoutMs = 10;
    
    public Constants() {
    	
    	postConstants();
    	
	    new Thread(() -> {
		   	while (true) {
		   		updateSmartDashboard();
		   		Timer.delay(0.5);
		   	}
		}).start();
    }
    
    /* post the constants to the shuffleboard */
	private void postConstants() {
		/* drive train */
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Inches to drive for test", driveInchesForTest);
		SmartDashboard.putNumber("Autonomous drive inches", autoDriveStraightAutoInches);
		SmartDashboard.putNumber("Autonomous drive to close switch inches", autoDriveToCloseSwitchInches);
		SmartDashboard.putNumber("Autonomous drive past switch inches", autoDrivePastSwitchInches);
		SmartDashboard.putNumber("Autonomous drive across switch inches", autoDriveAcrossSwitchInches);

		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive duty cycle", straightDriveDutyCycle);
		SmartDashboard.putNumber("Straight drive rate limit", straightDriveRateLimit);
		SmartDashboard.putNumber("Arm Up Max Drive DutyCycle",armUpMaxDriveDutyCycle);
		SmartDashboard.putNumber("Arm Down Max Drive DutyCycle",armDownMaxDriveDutyCycle);
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

    	/* Arm motor */
    	SmartDashboard.putNumber("Arm PID KP", armMotorPidKp);
    	SmartDashboard.putNumber("Arm Lower PID KP", armMotorLowerPidKp);
    	SmartDashboard.putNumber("Arm PID KI", armMotorPidKi);
    	SmartDashboard.putNumber("Arm PID KD", armMotorPidKd);
    	SmartDashboard.putNumber("Arm Raise PID KF", armMotorRaisePidKf);
    	SmartDashboard.putNumber("Arm Lower PID KF", armMotorLowerPidKf);
    	SmartDashboard.putNumber("Arm PID Allowable Error", armMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Arm Intake PID Setpoint", intakePositionArmPidSetpoint);
    	SmartDashboard.putNumber("Arm Switch PID Setpoint", switchPositionArmPidSetpoint);
    	SmartDashboard.putNumber("Arm Scale PID Setpoint", scalePositionArmPidSetpoint);   
    	SmartDashboard.putNumber("Arm PID Ramp", armSecondsFromNeutral);
    	SmartDashboard.putNumber("Arm Raise Peak Voltage", armMotorPeakRaiseVoltage);
    	SmartDashboard.putNumber("Arm Lower Peak Voltage", armMotorPeakLowerVoltage);
    	SmartDashboard.putNumber("Arm Lower PID Entry Point", armMotorLowerArmPidEntryPoint);
    	
    	
    	/* Intake motor */
    	SmartDashboard.putNumber("Intake Cube Duty Cycle", intakeCubeDutyCycle);
    	SmartDashboard.putNumber("Output Cube Duty Cycle", outputCubeDutyCycle);   
    	SmartDashboard.putNumber("Intake PID KP", intakeMotorPidKp);
    	SmartDashboard.putNumber("Intake PID KI", intakeMotorPidKi);
    	SmartDashboard.putNumber("Intake PID KD", intakeMotorPidKd);
    	SmartDashboard.putNumber("Intake PID Allowable Error", intakeMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Cube Output Auto Timeout", cubeOutputAutoTimeout);
		
		/* Hinge motor */
    	SmartDashboard.putNumber("Hinge PID KP", hingeMotorPidKp);
    	SmartDashboard.putNumber("Hinge Lower PID KP", hingeMotorLowerPidKp);
    	SmartDashboard.putNumber("Hinge PID KI", hingeMotorPidKi);
    	SmartDashboard.putNumber("Hinge PID KD", hingeMotorPidKd);
    	SmartDashboard.putNumber("Hinge PID Allowable Error", hingeMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Raise Hinge PID Setpoint", raiseHingePidSetpoint);
    	SmartDashboard.putNumber("Lower Hinge PID Setpoint", lowerHingePidSetpoint);  
    	SmartDashboard.putNumber("Hinge To Scale PID Setpoint", hingeToScalePidSetpoint);  
    	SmartDashboard.putNumber("Hinge PID Ramp", hingeSecondsFromNeutral);
    	SmartDashboard.putNumber("Hinge Peak Voltage", hingeMotorPeakVoltage);
    	SmartDashboard.putNumber("Lower Hinge Timeout", lowerHingeTimeout);


    	/* Climbing */
    	SmartDashboard.putNumber("Extend Tape Duty Cycle", extendTapeDutyCycle);   
    	SmartDashboard.putNumber("Reel Tape Duty Cycle", reelTapeDutyCycle);   
    	
    	/* CAN Addresses for Talons */
    	SmartDashboard.putNumber("Left Rear Drive Motor CAN Address", leftRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Left Front Drive Motor CAN Address", leftFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Rear Drive Motor CAN Address", rightRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Front Drive Motor CAN Address", rightFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Intake Motor 1 CAN Address", intakeMotor1CanAddress);   
    	SmartDashboard.putNumber("Intake Motor 2 CAN Address", intakeMotor2CanAddress);   
    	SmartDashboard.putNumber("Hinge Motor CAN Address", hingeMotorCanAddress);   
    	SmartDashboard.putNumber("Arm Motor CAN Address", armMotorCanAddress);   
    	SmartDashboard.putNumber("Climb Motor CAN Address", climbMotorCanAddress);   
    	SmartDashboard.putNumber("Gyro Controller CAN Address", gyroControllerCanAddress);   
	}
    
	/* periodically publish outputs */
    private void updateSmartDashboard() {

    	/* game data */
    	if (Robot.gameData != null)
    	{
    		SmartDashboard.putString("Game Data", Robot.gameData);    		
    	}
    	
		/* joystick */
		SmartDashboard.putNumber("Joystick forward", Robot.m_oi.joystick.getRawAxis(1));
		SmartDashboard.putNumber("Joystick rotate", Robot.m_oi.joystick.getRawAxis(0));

		/* drive train */
		SmartDashboard.putNumber("Pigeon fused heading", Robot._driveTrain.getGyroHeading());
		SmartDashboard.putData(Robot._driveTrain._gyro);
		SmartDashboard.putData(Robot._driveTrain._gyroPid);

		SmartDashboard.putNumber("Right Encoder Count", Robot._driveTrain.getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder Count", Robot._driveTrain.getLeftEncoderCount());
		SmartDashboard.putNumber("Gyro PID output value", Robot._driveTrain.getPidOutput());
    	SmartDashboard.putNumber("Drive Encoder Counts Per Inch", driveEncoderCountsPerInch);

    	/* Arm motor */
    	SmartDashboard.putNumber("Arm Encoder Count", Robot._armMotor.getEncoderCount());
    	SmartDashboard.putNumber("Arm Closed Loop Error", Robot._armMotor.getClosedLoopError());
    	SmartDashboard.putNumber("Arm Motor Output Voltage", Robot._armMotor.getMotorOutputVoltage());
    	SmartDashboard.putString("Arm State", Robot._armMotor.getState());
    	
		/* Hinge motor */
    	SmartDashboard.putNumber("Hinge Encoder Count", Robot._hingeMotor.getEncoderCount());
    	SmartDashboard.putNumber("Hinge Closed Loop Error", Robot._hingeMotor.getClosedLoopError());
    	SmartDashboard.putNumber("Hinge Motor Output Voltage", Robot._hingeMotor.getMotorOutputVoltage());
    	SmartDashboard.putString("Hinge State", Robot._hingeMotor.getState());
    	
    	SmartDashboard.putNumber("Intake Encoder Count", Robot._intakeMotor.getEncoderCount());
    	SmartDashboard.putNumber("Intake Closed Loop Error", Robot._intakeMotor.getClosedLoopError());
    	SmartDashboard.putNumber("Intake Motor Output Voltage", Robot._intakeMotor.getMotorOutputVoltage());

    	Constants.armUpMaxDriveDutyCycle = SmartDashboard.getNumber("Arm Up Max Drive DutyCycle",Constants.armUpMaxDriveDutyCycle);
    	Constants.armDownMaxDriveDutyCycle = SmartDashboard.getNumber("Arm Down Max Drive DutyCycle",Constants.armDownMaxDriveDutyCycle);
	}
}
