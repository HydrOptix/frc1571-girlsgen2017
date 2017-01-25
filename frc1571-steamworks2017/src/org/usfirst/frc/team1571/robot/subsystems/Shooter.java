package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private CANTalon shooterTalon = RobotMap.shooterTalon;

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
	public void setSpeed(double speed) {
		shooterTalon.set(speed);
	}
}
