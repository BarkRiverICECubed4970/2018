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
	public static final int climbMotorCanAddress = 13;
	public static final int solenoidMotorCanAddress = 12;
	
    public static double autoCloseScaleDegrees = -40.0;
    public static double driveInchesForTest = 36.0;
    public static double timedDriveTimeout = 1.0;
    public static double autoStraightDriveToCloseScaleInches = 240.0;
    public static double autoAngleDriveToCloseScaleInches = 80.0;
    public static double autoDrivePastSwitchInches = 174.0;
    public static double autoDriveAcrossSwitchInches = 168.0;
    public static double autoDriveAcrossScaleInches = 286.0;
    public static double autoDriveToNullZone = 8.0;
    public static double autoDriveToOppositeSwitchZone = 20.0;
    public static double autoDriveToFenceFromSwitchZone = 20.0;
    public static double autoOppositeScaleTurnDegrees = -120.0;
    public static double autoReverseDriveInches = 36.0;
    public static double autoTurnDegreesFromCenter = -25.0;
    public static double autoTurnDegreesToCloseScale = -30.0;
    public static double autoDriveToSwitchFromCenterTimeout = 2.5;
    public static double scaleInches = 100.0;
    public static double armDownMaxDriveDutyCycle = 1.0;
    public static double armUpMaxDriveDutyCycle = 0.75;
    public static double straightDriveRateLimit = 0.01;
    public static double straightDriveInchesForRampDownBegin = 24.0;	
    public static double driveStraightAngleForTest = 0.0;

    /* tuned or in progress parameters */
    public static double straightDriveStartDutyCycle = 0.4;
    public static double straightDriveFinalDutyCycle = 0.25;

    public static double autoDriveToFenceFromCenter = 90.0;

    public static double autoSwitchFromCenterLeftDegreeAdder = 2.0;
	public static double autoSwitchFromCenterLeftDistanceAdder = 20.0;

    public static double autoDriveStraightAutoInches = 80.0;
    public static double autoStraightDriveToCloseSwitchInches = 95.0;
    public static double autoAngleDriveToCloseSwitchInches = 20.0;
    public static double autoSwitchTurnDegreesFromSide = -20.0;

    
    /* counts per revolution on output shaft * inches per revolution from tires
     * 
     *  6 inch diameter wheels. 
     *  inches/rotation = 6 * pi = 18.849555
     *  counts/inch = counts/rotation / inches/rotation
     *  
     *  */
    
	// -1408 counts on right encoder per forward rev
	// 714 counts on left encoder per forward rev
    // use left encoder for now
    // 714 counts per 18.85 inches = 37.87
    public static double driveEncoderCountsPerInch = 74.7;
    
    
//    public static double turnDegreesTimeout = 90.0;
    public static double turnDegreesTimeout = 3.0;
    public static double turnDegrees = -90.0;
    
    public static double gyroStraightPidKp = 0.04;
    public static double gyroTurnPidKp = 0.08;
    public static double gyroTurnArmUpPidKp = 0.085;
    public static double gyroReversePidKp = 0.05;
    public static double gyroPidKi = 0.0;
    public static double gyroPidKd = 0.0;
    public static double gyroPidMinIn = -120.0;
    public static double gyroPidMaxIn = 120.0;
    public static double gyroPidMinOut = -1.0;
    public static double gyroPidMaxOut = 1.0;
    public static double gyroPidTolerance = 2.0;
    public static double gyroPidMaxSetpoint = 8;
    
    public static double hingeMotorPidKp = 0.75;
    public static double hingeMotorLowerPidKp = 0.3;
    public static double hingeMotorPidKi = 0.0;
    public static double hingeMotorPidKd = 0.0;
    public static double hingeMotorAllowableClosedLoopError = 50;
    public static double raiseHingePidSetpoint = 0.0;
    public static double hingeToScalePidSetpoint = 1500.0;
    public static double lowerHingePidSetpoint = 2000.0;
    public static double lowerHingeTimeout = 1.0;
    public static double raiseHingeTimeout = 1.0;
    public static double hingeSecondsFromNeutral = 0.5;
    public static double hingeMotorPeakVoltage = 0.7;
    public static double hingeManualDutyCycle = 0.4;

//    public static double raiseHingePidSetpoint = 0.3;
//    public static double lowerHingePidSetpoint = 0.3;

    public static double intakeMotorPidKp = 0.5;
    public static double intakeMotorPidKi = 0.0;
    public static double intakeMotorPidKd = 0.0;
    public static double intakeMotorAllowableClosedLoopError = 10;
    public static double cubeOutputAutoTimeout = 2.0;
    
    
    public static double armMotorPidKp = 5.0;
    public static double armMotorLowerPidKp = 5.0;
    public static double armMotorPidKi = 0.0;
    public static double armMotorPidKd = 0.0;
    public static double armMotorRaisePidKf = 0.0;
    public static double armMotorLowerPidKf = 0.0;
    public static double armMotorAllowableClosedLoopError = 10;
    public static double armSecondsFromNeutral = 0.5;
    public static double intakePositionArmPidSetpoint = 50.0;
    public static double switchPositionArmPidSetpoint = 500.0;
    public static double armMotorLowerArmPidEntryPoint = 400.0;
    public static double scalePositionArmPidSetpoint = 1200.0;
    public static double armMotorPeakRaiseVoltage = 0.7;
    public static double armMotorPeakLowerVoltage = 0.5;
    public static double armToSwitchTimeout = 2.0;
    public static double armToScaleTimeout = 3.0;
    public static double armToIntakeTimeout = 5.0;
    public static double armReleaseSpringDutyCycle = -0.65;
    public static double armReleaseSpringTimeout = 0.25;
    public static double armManualDutyCycle = 0.3;
    
    public static double intakeCubeDutyCycle = 0.3;
    public static double outputCubeDutyCycle = 0.4;
    public static double outputScaleCubeDutyCycle = 0.6;
    public static double unlockWinchTimeout = 0.25;
    public static double winchOutMaxDriveDutyCycle = 0.3;
    public static double winchExtendMaxDutyCycle = -1.0;
    public static double winchReelMaxDutyCycle = 1.0;
    
    /* 
       how many times the extend winch command has been called
       before reelWinch() can be called (to prevent sucking the hook 
       in and destroying the assembly)
    */
    public static double winchOutCount = 75.0;
    /*
       how many times the reel winch command has been called
       before the winch can be locked (solenoid released) once the 
       climb button has been let go
    */
//    public static double winchReelCount = 75.0;	
    public static double winchReelCount = 1.0;	
	
    public static final int timeoutMs = 10;
    
    public Constants() {
    	
    	postConstants();
    	
	    new Thread(() -> {
		   	while (true) {
		   		updateSmartDashboard();
		   		Timer.delay(1.0);
		   	}
		}).start();
    }
    
    /* post the constants to the shuffleboard */
	private void postConstants() {
		/* drive train */
		SmartDashboard.putNumber("Inches to drive for test", driveInchesForTest);
		SmartDashboard.putNumber("Timed Drive Timeout", timedDriveTimeout);
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Autonomous drive inches", autoDriveStraightAutoInches);
		SmartDashboard.putNumber("Autonomous straight drive to close switch inches", autoStraightDriveToCloseSwitchInches);
		SmartDashboard.putNumber("Autonomous angle drive to close switch inches", autoAngleDriveToCloseSwitchInches);
		SmartDashboard.putNumber("Autonomous straight drive to close scale inches", autoStraightDriveToCloseScaleInches);
		SmartDashboard.putNumber("Autonomous angle drive to close scale inches", autoAngleDriveToCloseScaleInches);
		SmartDashboard.putNumber("Autonomous drive past switch inches", autoDrivePastSwitchInches);
		SmartDashboard.putNumber("Autonomous drive across switch inches", autoDriveAcrossSwitchInches);
		SmartDashboard.putNumber("Autonomous drive across scale inches", autoDriveAcrossScaleInches);
		SmartDashboard.putNumber("Autonomous opposite scale degrees to turn", autoOppositeScaleTurnDegrees);
		SmartDashboard.putNumber("Autonomous drive to null zone", autoDriveToNullZone);
		SmartDashboard.putNumber("Autonomous drive to opposite switch zone", autoDriveToOppositeSwitchZone);
		SmartDashboard.putNumber("Autonomous drive to fence from switch zone", autoDriveToFenceFromSwitchZone);
		SmartDashboard.putNumber("Autonomous turn degrees from center", autoTurnDegreesFromCenter);
		SmartDashboard.putNumber("Autonomous switch turn degrees from side", autoSwitchTurnDegreesFromSide);
		SmartDashboard.putNumber("Autonomous turn degrees to close scale", autoTurnDegreesToCloseScale);
		SmartDashboard.putNumber("Autonomous close scale turn", autoCloseScaleDegrees);
		SmartDashboard.putNumber("Autonomous drive to fence from center", autoDriveToFenceFromCenter);
		SmartDashboard.putNumber("Autonomous drive to switch from center timeout", autoDriveToSwitchFromCenterTimeout);
		SmartDashboard.putNumber("Autonomous reverse drive inches", autoReverseDriveInches);
		SmartDashboard.putNumber("Autonomous switch from center left degree adder", autoSwitchFromCenterLeftDegreeAdder);
		SmartDashboard.putNumber("Autonomous switch from center left distance adder", autoSwitchFromCenterLeftDistanceAdder);
				
		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive start duty cycle", straightDriveStartDutyCycle);
		SmartDashboard.putNumber("Straight drive final duty cycle", straightDriveFinalDutyCycle);
		SmartDashboard.putNumber("Straight drive rate limit", straightDriveRateLimit);
		SmartDashboard.putNumber("Straight drive ramp down inches", straightDriveInchesForRampDownBegin);
		SmartDashboard.putNumber("Arm Up Max Drive DutyCycle",armUpMaxDriveDutyCycle);
		SmartDashboard.putNumber("Arm Down Max Drive DutyCycle",armDownMaxDriveDutyCycle);
		SmartDashboard.putNumber("Gyro Turn Degrees PID KP", Constants.gyroTurnPidKp);
    	SmartDashboard.putNumber("Gyro Turn Degrees Arm Up PID KP", Constants.gyroTurnArmUpPidKp);    		    			    			
    	SmartDashboard.putNumber("Gyro Drive Reverse PID KP", Constants.gyroReversePidKp);    		    		
    	SmartDashboard.putNumber("Gyro Drive Straight PID KP", Constants.gyroStraightPidKp);    		
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
    	SmartDashboard.putNumber("Drive straight angle for test", driveStraightAngleForTest);

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
		SmartDashboard.putNumber("Arm To Switch Timeout", Constants.armToSwitchTimeout);
		SmartDashboard.putNumber("Arm To Scale Timeout", Constants.armToScaleTimeout);
		SmartDashboard.putNumber("Arm To Intake Timeout", Constants.armToIntakeTimeout);
		SmartDashboard.putNumber("Arm Release Spring Duty Cycle", Constants.armReleaseSpringDutyCycle);
		SmartDashboard.putNumber("Arm Release Spring Timeout", Constants.armReleaseSpringTimeout);
      	SmartDashboard.putNumber("Arm Manual Move Duty Cycle", armManualDutyCycle);


    	
    	/* Intake motor */
    	SmartDashboard.putNumber("Intake Cube Duty Cycle", intakeCubeDutyCycle);
    	SmartDashboard.putNumber("Output Cube Duty Cycle", outputCubeDutyCycle);   
    	SmartDashboard.putNumber("Output Scale Cube Duty Cycle", outputScaleCubeDutyCycle);   
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
    	SmartDashboard.putNumber("Raise Hinge Timeout", raiseHingeTimeout);
    	SmartDashboard.putNumber("Hinge Manual Move Duty Cycle", hingeManualDutyCycle);


    	/* Climbing */
    	SmartDashboard.putNumber("Winch Extend Max Duty Cycle", winchExtendMaxDutyCycle);   
    	SmartDashboard.putNumber("Winch Reel Max Duty Cycle", winchReelMaxDutyCycle);   
    	SmartDashboard.putNumber("Winch Out Max Drive Duty Cycle", winchOutMaxDriveDutyCycle);   
    	
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
    	SmartDashboard.putNumber("Solenoid Motor CAN Address", solenoidMotorCanAddress);   
	}
    
	/* periodically publish outputs */
    private void updateSmartDashboard() {

    	/* game data */
    	if (Robot.gameData != null)
    	{
    		SmartDashboard.putString("Game Data", Robot.gameData);    		
    	}
    	
    	/* 
    	 * put an instance of the PDP to shuffleboard... this may help to see
    	 * issues with motors, etc...
    	 */
//    	SmartDashboard.putData("Power Distribution Panel", Robot.pdp);
    	
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

        SmartDashboard.putNumber("Winch Extend Counter", Robot._climbMotor.getWinchOutCount());
        SmartDashboard.putNumber("Winch Reel Counter", Robot._climbMotor.getWinchReelCount());
	    
    	Constants.armUpMaxDriveDutyCycle = SmartDashboard.getNumber("Arm Up Max Drive DutyCycle",Constants.armUpMaxDriveDutyCycle);
    	Constants.armDownMaxDriveDutyCycle = SmartDashboard.getNumber("Arm Down Max Drive DutyCycle",Constants.armDownMaxDriveDutyCycle);

		Constants.armReleaseSpringDutyCycle = SmartDashboard.getNumber("Arm Release Spring Duty Cycle", Constants.armReleaseSpringDutyCycle);
		Constants.armReleaseSpringTimeout = SmartDashboard.getNumber("Arm Release Spring Timeout", Constants.armReleaseSpringTimeout);
		
		Constants.winchOutMaxDriveDutyCycle = SmartDashboard.getNumber("Winch Out Max Drive Duty Cycle", Constants.winchOutMaxDriveDutyCycle);
    	winchExtendMaxDutyCycle = SmartDashboard.getNumber("Winch Extend Max Duty Cycle", winchExtendMaxDutyCycle);   
    	winchReelMaxDutyCycle = SmartDashboard.getNumber("Winch Reel Max Duty Cycle", winchReelMaxDutyCycle);   
	}
}
