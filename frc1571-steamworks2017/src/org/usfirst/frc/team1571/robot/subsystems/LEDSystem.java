package org.usfirst.frc.team1571.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.SerialPort;

public class LEDSystem extends Subsystem {
	
	private SerialPort usbSerial = RobotMap.usbSerial;

    public void initDefaultCommand() {
		setDefaultCommand(null);
    }
    
    public void solid(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M0"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void wipe(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M1"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void wipeContinuous(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M2"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void wipeOscillate(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M3"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void fade(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M4"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void fadeOscillating(int r1, int r2, int g1, int g2, int b1, int b2, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M5"+"R"+r1+"G"+g1+"B"+b1+"W"+wait);
    	this.endSerial();
    	this.startSerial();
    	usbSerial.writeString("R"+r2+"G"+g2+"B"+b2);
    	this.endSerial();
    }
    
    public void theaterChase(int r, int g, int b, int wait) {
    	this.startSerial();
    	usbSerial.writeString("M6"+"R"+r+"G"+g+"B"+b+"W"+wait);
    	this.endSerial();
    }
    
    public void rainbow(int wait) {
    	this.startSerial();
    	usbSerial.writeString("M7"+"W"+wait);
    	this.endSerial();
    }
    
    public void rainbowCycle(int wait) {
    	this.startSerial();
    	usbSerial.writeString("M8"+"W"+wait);
    	this.endSerial();
    }
    
    public void theaterChaseRainbow(int wait) {
    	this.startSerial();
    	usbSerial.writeString("M9"+"W"+wait);
    	this.endSerial();
    }
    
    public void startSerial() {
    	usbSerial.writeString("S");
    	
    }
    
    public void endSerial() {
    	usbSerial.writeString("F");
    }
    
}

