package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDWipe extends InstantCommand {
	
	int r,g,b,wait;

    public LEDWipe(int r, int g, int b, int wait) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.wipe(r, g, b, wait);
    }

}
