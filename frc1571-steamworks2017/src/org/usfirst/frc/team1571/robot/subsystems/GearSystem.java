package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSystem extends Subsystem {

    private final DigitalInput openSwitch = RobotMap.gearOpenSwitch;
    private final DigitalInput closedSwitch = RobotMap.gearClosedSwitch;
    private final CANTalon gearArm = RobotMap.gearArm;

    public void initDefaultCommand() {
    }
    
    public void close() {
    	if(!closedSwitch.get()) {
    		gearArm.set(.1);
    	} else {
    		gearArm.set(0);
    	}
    }
    
    public void open() {
    	if(!openSwitch.get()) {
    		gearArm.set(-.1);
    	} else {
    		gearArm.set(0);
    	}
    }
    
    public boolean isOpen() {
    	return openSwitch.get();
    }
    
    public boolean isClosed() {
    	return closedSwitch.get();
    }
}

