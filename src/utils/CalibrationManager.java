package utils;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrationManager {
	
	/* CAN Addresses for Motor Controllers */
	public static final int leftRearDriveMotorCanAddress = 2;
	public static final int leftFrontDriveMotorCanAddress = 3;
	public static final int rightRearDriveMotorCanAddress = 4;
	public static final int rightFrontDriveMotorCanAddress = 5;
	public static final int intakeMotorCanAddress = 6;
	public static final int hingeMotorCanAddress = 7;
	public static final int armMotorCanAddress = 8;
	public static final int climbMotorCanAddress = 9;
	
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
    public static double raiseHingeMaxDutyCycle = 0.3;
    public static double lowerHingeMaxDutyCycle = 0.3;
    public static double raiseArmMaxDutyCycle = 0.3;
    public static double lowerArmMaxDutyCycle = 0.3;
    public static double extendTapeDutyCycle = 0.3;
    public static double reelTapeDutyCycle = 0.3;

    public CalibrationManager() {
	    new Thread(() -> {
		   	while (true) {
		   		updateSmartDashboard();
		   		Timer.delay(0.5);
		   	}
		}).start();	    
    }
    
	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Joystick forward", Robot.m_oi.joystick.getRawAxis(1));
		SmartDashboard.putNumber("Joystick rotate", Robot.m_oi.joystick.getRawAxis(0));
		SmartDashboard.putNumber("Pigeon fused heading", Robot._driveTrain.getGyroHeading());
		SmartDashboard.putData(Robot._driveTrain._gyro);
		SmartDashboard.putData(Robot._driveTrain._gyroPid);
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Inches to drive", driveInches);
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
    	SmartDashboard.putNumber("Intake Cube Duty Cycle", intakeCubeDutyCycle);
    	SmartDashboard.putNumber("Output Cube Duty Cycle", outputCubeDutyCycle);   
    	SmartDashboard.putNumber("Raise Hinge Max Duty Cycle", raiseHingeMaxDutyCycle);
    	SmartDashboard.putNumber("Lower Hinge Max Duty Cycle", lowerHingeMaxDutyCycle);   
    	SmartDashboard.putNumber("Raise Arm Max Duty Cycle", raiseArmMaxDutyCycle);
    	SmartDashboard.putNumber("Lower Arm Max Duty Cycle", lowerArmMaxDutyCycle);   
    	SmartDashboard.putNumber("Extend Tape Duty Cycle", extendTapeDutyCycle);   
    	SmartDashboard.putNumber("Reel Tape Duty Cycle", reelTapeDutyCycle);   
    	SmartDashboard.putNumber("Left Rear Drive Motor CAN Address", leftRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Left Front Drive Motor CAN Address", leftFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Rear Drive Motor CAN Address", rightRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Front Drive Motor CAN Address", rightFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Intake Motor CAN Address", intakeMotorCanAddress);   
    	SmartDashboard.putNumber("Hinge Motor CAN Address", hingeMotorCanAddress);   
    	SmartDashboard.putNumber("Arm Motor CAN Address", armMotorCanAddress);   
    	SmartDashboard.putNumber("Climb Motor CAN Address", climbMotorCanAddress);   
		SmartDashboard.putNumber("Arm Encoder Count", Robot._armMotor.getEncoderCount());
		SmartDashboard.putNumber("Hinge Encoder Count", Robot._hingeMotor.getEncoderCount());

	}
}
