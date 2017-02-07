package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDFade extends InstantCommand {
	
	int r,g,b,wait;

    public LEDFade(int r, int g, int b, int wait) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.fade(r, g, b, wait);
    }

}
