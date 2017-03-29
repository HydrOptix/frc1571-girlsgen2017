package org.usfirst.frc.team1571.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

public class DriveSystem extends Subsystem {
	private final CANTalon rightMaster = RobotMap.driveTalonRightMaster;
	private final CANTalon rightSlave = RobotMap.driveTalonRightSlave;
	
	private final CANTalon leftMaster = RobotMap.driveTalonLeftMaster;
	private final CANTalon leftSlave = RobotMap.driveTalonLeftSlave;
	
	private final ADXRS450_Gyro gyro = RobotMap.steeringGyro;
	
	private final Encoder leftEncoder = RobotMap.driveLeftEncoder;
	private final Encoder rightEncoder = RobotMap.driveRightEncoder;

	public void initDefaultCommand() {
	}
	
	public void tankDrive(double speed, double steering) {
		rightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.set(rightMaster.getDeviceID());
		
		leftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.set(leftMaster.getDeviceID());
		
		if(steering == 0) {
			rightMaster.set(speed * RobotMap.driveSpeed); //Set the drive motors to the last detected straight speed
			leftMaster.set(speed * -1 * RobotMap.driveSpeed);
		} else if(steering > 0) {
			leftMaster.set(speed * -1 * RobotMap.driveSpeed);
			rightMaster.set((speed - (Math.abs(speed) * steering * RobotMap.maxSteering)) * RobotMap.driveSpeed);
		} else if(steering < 0) {
			rightMaster.set(speed * RobotMap.driveSpeed);
			leftMaster.set((speed - (Math.abs(speed) * steering * RobotMap.maxSteering * -1)) * -1 * RobotMap.driveSpeed);
		}
		
	}
	
	public void stationaryTurn(double speed) {
		rightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.set(rightMaster.getDeviceID());
		
		leftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.set(leftMaster.getDeviceID());
		
		rightMaster.set(speed * RobotMap.turnSpeed);
		leftMaster.set(speed * RobotMap.turnSpeed);
	}
	
	public void allStop() {
		rightMaster.set(0);
		leftMaster.set(0);
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public double getGyroRate() {
		return gyro.getRate();
	}
	
	public int getEncoderCounts(String side) {
		if(side == "LEFT") {
			return leftEncoder.get();
		} else if(side == "RIGHT") {
			return rightEncoder.get();
		} else {
			return 0;
		}
	}
	
	public double getEncoderDistance(String side) {
		if(side == "LEFT") {
			return leftEncoder.getDistance();
		} else if(side == "RIGHT") {
			return rightEncoder.getDistance();
		} else {
			return 0;
		}
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void calibrateGyro() {
		gyro.calibrate();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void report() {
		SmartDashboard.putNumber("Left Drive Encoder", leftEncoder.get());
		SmartDashboard.putNumber("Right Drive Encoder", rightEncoder.get());
		SmartDashboard.putNumber("Gyro Angle", getGyroAngle());
	}
	
}
