package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterTalon = RobotMap.shooterTalon;

	public void initDefaultCommand() {
	}
	
	public void setSpeed(double speed) {
		shooterTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterTalon.set(speed);
	}
	
	public void setVelocity(double velocity) {
		shooterTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterTalon.setPID(RobotMap.shooterP, RobotMap.shooterI, RobotMap.shooterD);
		shooterTalon.set(velocity);
	}
	
	public double getSpeed() {
		return shooterTalon.get();
	}
	
	public double getSpeedFromDistance(double distance) {
		return RobotMap.shooterSpeedFunctionA * Math.pow(distance, 2) + RobotMap.shooterSpeedFunctionB * distance + RobotMap.shooterSpeedFunctionC;
	}
	
	public double getVelocity() {
		return shooterTalon.getEncVelocity();
	}
}
