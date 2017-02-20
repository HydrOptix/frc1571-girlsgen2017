package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.StopFeeder;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {
	
	private final CANTalon feederTalon = RobotMap.feederTalon;
	private boolean feederRunning;

    public void initDefaultCommand() {
    	setDefaultCommand(new StopFeeder());
    }
    
    public void startFeeder() {
    	feederTalon.set(RobotMap.feederSpeed);
    	feederRunning = true;
    }
    
    public void stopFeeder() {
    	feederTalon.set(0);
    	feederRunning = false;
    }
    
    public void toggleFeeder() {
    	if(feederRunning) {
    		stopFeeder();
    	} else {
    		startFeeder();
    	}
    }
}

