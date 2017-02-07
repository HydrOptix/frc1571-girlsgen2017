package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearSwitch extends Subsystem {

    private final DigitalInput gearSwitch = RobotMap.gearSwitch;

    public void initDefaultCommand() {
    }
    
    public boolean getPressed() {
    	if(gearSwitch.get()) {
    		return !RobotMap.reverseGearSwitch;
    	} else {
    		return RobotMap.reverseGearSwitch;
    	}
    }
}

