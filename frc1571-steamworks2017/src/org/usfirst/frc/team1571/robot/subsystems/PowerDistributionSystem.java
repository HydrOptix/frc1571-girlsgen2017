package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;
import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class PowerDistributionSystem extends Subsystem {

	private final PowerDistributionPanel pdp = RobotMap.powerDistributionPanel;

    public void initDefaultCommand() {
    	setDefaultCommand(new ReportPDP());
    }
    
    public void reportValues() {
    	SmartDashboard.putNumber("Current Voltage",pdp.getVoltage());
    	SmartDashboard.putNumber("Current Temperature", pdp.getTemperature());
    	
    	for(int i = 0; i < 16; i++) {
    		SmartDashboard.putNumber("Channel "+i+" Current", pdp.getCurrent(i));
    	}
    	
    }
}

