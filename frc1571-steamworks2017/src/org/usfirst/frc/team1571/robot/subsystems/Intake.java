package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private CANTalon agitatorTalon = RobotMap.agitatorTalon;

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
	public void startIntake() {
		agitatorTalon.set(RobotMap.agitatorSpeed);
	}
	
	public void stopIntake() {
		agitatorTalon.set(0);
	}
}
