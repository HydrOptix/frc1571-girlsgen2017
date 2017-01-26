package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private CANTalon climberTalon = RobotMap.climberTalon;
	
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
	public void startClimber() {
		climberTalon.set(RobotMap.climbSpeed);
	}
	
	public void stopClimber() {
		climberTalon.set(0);
	}
}
