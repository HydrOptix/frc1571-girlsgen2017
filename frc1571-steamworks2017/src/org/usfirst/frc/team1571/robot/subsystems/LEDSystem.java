package org.usfirst.frc.team1571.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1571.robot.RobotMap;

import org.usfirst.frc.team1571.robot.commands.*;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class LEDSystem extends Subsystem {
	
	private SerialPort usbSerial = RobotMap.arduino;
	private Timer serialTimer;

    public void initDefaultCommand() {
    }
    
    public void solid(int r, int g, int b, int wait) {
	    sendCommand("M0"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void wipe(int r, int g, int b, int wait) {
	    sendCommand("M1"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void wipeContinuous(int r, int g, int b, int wait) {
	    sendCommand("M2"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void wipeOscillate(int r, int g, int b, int wait) {
	    sendCommand("M3"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void fade(int r, int g, int b, int wait) {
    	sendCommand("M4"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void fadeOscillate(int r1, int g1, int b1, int r2, int g2, int b2, int wait) {
    	sendCommand("M5"+"R"+r1+"G"+g1+"B"+b1+"W"+wait);
    	sendCommand("R"+r2+"G"+g2+"B"+b2);
    }
    
    public void theaterChase(int r, int g, int b, int wait) {
	    sendCommand("M6"+"R"+r+"G"+g+"B"+b+"W"+wait);
    }
    
    public void rainbow(int wait) {
	    sendCommand("M7"+"W"+wait);
    }
    
    public void rainbowCycle(int wait) {
	    sendCommand("M8"+"W"+wait);
    }
    
    public void theaterChaseRainbow(int wait) {
	    sendCommand("M9"+"W"+wait);
    }
    
    public boolean serialReady() {
    	if(serialTimer == null) {
    		serialTimer = new Timer();
    		serialTimer.start();
    		return true;
    	}
    	
    	if(serialTimer.hasPeriodPassed(RobotMap.serialDelay)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void sendCommand(String out) {
    	if(serialReady()) {
	    	try {
	    		usbSerial.writeString("S" + out + "F");
	    	} catch (RuntimeException e) {
				System.err.println("Caught SerialPort RuntimeException: " + e.getMessage());
	    	}
    	}
    }
}

