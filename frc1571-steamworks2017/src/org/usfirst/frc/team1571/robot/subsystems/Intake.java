package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	private final CANTalon intakeTalon = RobotMap.intakeTalon;
	private boolean intakeRunning;

	public void initDefaultCommand() {
	}
	
	public void startIntake() {
		intakeTalon.set(RobotMap.intakeSpeed);
		intakeRunning = true;
	}
	
	public void stopIntake() {
		intakeTalon.set(0);
		intakeRunning = false;
	}
	
	public void toggleIntake() {
    	if(intakeRunning) {
    		stopIntake();
    	} else {
    		startIntake();
    	}
    }
}
