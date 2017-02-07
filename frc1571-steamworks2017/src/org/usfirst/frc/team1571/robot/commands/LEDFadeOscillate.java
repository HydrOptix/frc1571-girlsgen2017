package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDFadeOscillate extends InstantCommand {
	
	int r1,g1,b1,r2,g2,b2,wait;

    public LEDFadeOscillate(int r1, int g1, int b1, int r2, int g2, int b2, int wait) {
    	this.r1 = r1;
    	this.g1 = g1;
    	this.b1 = b1;
    	this.r2 = r2;
    	this.g2 = g2;
    	this.b2 = b2;
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.fadeOscillate(r1, g1, b1, r2, g2, b2, wait);
    }

}
