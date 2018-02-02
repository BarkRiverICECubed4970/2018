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
    
    public static double hingeMotorPidKp = 0.001;
    public static double hingeMotorPidKi = 0.0;
    public static double hingeMotorPidKd = 0.0;
    public static double hingeMotorAllowableClosedLoopError = 5;
    public static double raiseHingePidSetpoint = 0.0;
    public static double lowerHingePidSetpoint = 100.0;
    
    public static double armMotorPidKp = 0.001;
    public static double armMotorPidKi = 0.0;
    public static double armMotorPidKd = 0.0;
    public static double armMotorAllowableClosedLoopError = 5;
    public static double intakePositionArmPidSetpoint = 0.0;
    public static double switchPositionArmPidSetpoint = 100.0;
    public static double scalePositionArmPidSetpoint = 200.0;
    
    /* counts per revolution on output shaft * inches per revolution from tires
     * 
     *  6 inch diameter wheels. 
     *  inches/rotation = 6 * pi = 18.849555
     *  counts/inch = counts/rotation / inches/rotation
     *  
     *  */
    public static double driveEncoderCountsPerInch = 53.05;

    public static double turnDegreesTimeout = 3.0;

    public static double intakeCubeDutyCycle = 0.3;
    public static double outputCubeDutyCycle = 0.3;
    public static double extendTapeDutyCycle = 0.3;
    public static double reelTapeDutyCycle = 0.3;

    public static final int timeoutMs = 10;
    
    public Constants() {
	    new Thread(() -> {
		   	while (true) {
		   		updateSmartDashboard();
		   		Timer.delay(0.5);
		   	}
		}).start();
    }
    
	private void updateSmartDashboard() {

		/* joystick */
		SmartDashboard.putNumber("Joystick forward", Robot.m_oi.joystick.getRawAxis(1));
		SmartDashboard.putNumber("Joystick rotate", Robot.m_oi.joystick.getRawAxis(0));

		/* drive train */
		SmartDashboard.putNumber("Pigeon fused heading", Robot._driveTrain.getGyroHeading());
		SmartDashboard.putData(Robot._driveTrain._gyro);
		SmartDashboard.putData(Robot._driveTrain._gyroPid);
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Inches to drive", driveInches);
		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive duty cycle", straightDriveDutyCycle);
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

    	/* Arm motor */
    	SmartDashboard.putNumber("Arm PID KP", armMotorPidKp);
    	SmartDashboard.putNumber("Arm PID KI", armMotorPidKi);
    	SmartDashboard.putNumber("Arm PID KD", armMotorPidKd);
    	SmartDashboard.putNumber("Arm PID Allowable Error", armMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Arm Encoder Count", Robot._armMotor.getEncoderCount());
    	SmartDashboard.putNumber("Intake Position Arm PID Setpoint", intakePositionArmPidSetpoint);
    	SmartDashboard.putNumber("Switch Position Arm PID Set Point", switchPositionArmPidSetpoint);
    	SmartDashboard.putNumber("Scale Position Arm PID Set Point", scalePositionArmPidSetpoint);   
    	
    	/* Intake motor */
    	SmartDashboard.putNumber("Intake Cube Duty Cycle", intakeCubeDutyCycle);
    	SmartDashboard.putNumber("Output Cube Duty Cycle", outputCubeDutyCycle);   
		
		/* Hinge motor */
    	SmartDashboard.putNumber("Hinge PID KP", hingeMotorPidKp);
    	SmartDashboard.putNumber("Hinge PID KI", hingeMotorPidKi);
    	SmartDashboard.putNumber("Hinge PID KD", hingeMotorPidKd);
    	SmartDashboard.putNumber("Hinge PID Allowable Error", hingeMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Raise Hinge PID Set Point", raiseHingePidSetpoint);
    	SmartDashboard.putNumber("Lower Hinge PID Set Point", lowerHingePidSetpoint);   
    	SmartDashboard.putNumber("Hinge Encoder Count", Robot._hingeMotor.getEncoderCount());

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
		
	}
}
