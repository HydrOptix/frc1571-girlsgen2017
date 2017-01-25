package org.usfirst.frc.team1571.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;



	
public class DriveSystem extends Subsystem {
	private CANTalon rightFront = RobotMap.driveTalonRightFront;
	private CANTalon rightBack = RobotMap.driveTalonLeftBack;
	
	private CANTalon leftFront = RobotMap.driveTalonLeftFront;
	private CANTalon leftBack = RobotMap.driveTalonLeftBack;
	
	private ADXRS450_Gyro gyro = RobotMap.steeringGyro;
	
	private Encoder leftEncoder = RobotMap.driveLeftEncoder;
	private Encoder rightEncoder = RobotMap.driveRightEncoder;
	
	private double lastStraightLeftSpeed = 1.00;
	private double lastStraightRightSpeed = 1.00;


	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
	public void tankDrive(double speed, double steering) {
		rightFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightBack.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightBack.set(rightFront.getDeviceID());
		
		leftFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftBack.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftBack.set(leftFront.getDeviceID());
		
		if(steering == 0) {
			double gyroRate = this.getGyroRate();
			if(Math.abs(gyroRate) > RobotMap.allowableGyroError ) {
				if(gyroRate > 0) {													//If the robot is rotating to the right
					if(lastStraightLeftSpeed < 1) { 										//If the robot was already steering left to correct
							lastStraightLeftSpeed += RobotMap.straightSteeringAdjustRate; 		//Increase the speed of the left side to compensate
					} else { 																//If the robot was already steering straight or steering to the right
						lastStraightRightSpeed -= RobotMap.straightSteeringAdjustRate;			//Decrease the speed of the right side to compensate
					}
					
				} else {																//If the robot is rotating to the left
					if(lastStraightRightSpeed < 1) {										//If the robot was already steering right to correct
						lastStraightRightSpeed += RobotMap.straightSteeringAdjustRate;			//Increase the speed of the right side to compensate
					} else {																//If the robot was already steering left to correct
						lastStraightLeftSpeed -= RobotMap.straightSteeringAdjustRate;
					}
				}
			}
			rightFront.set(lastStraightRightSpeed); //Set the drive motors to the last detected straight speed
			leftFront.set(lastStraightLeftSpeed);
		}
	}
	
	public void stationaryTurn(double speed) {
		rightFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightBack.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightBack.set(rightFront.getDeviceID());
		
		leftFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftBack.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftBack.set(leftFront.getDeviceID());
		
		rightFront.set(speed * RobotMap.turnSpeed * -1);
		leftFront.set(speed * RobotMap.turnSpeed);
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public double getGyroRate() {
		return gyro.getRate();
	}
	
	public double getEncoderCounts(String side) {
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
	
}
