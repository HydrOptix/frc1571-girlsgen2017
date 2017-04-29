package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDWipeContinuous extends InstantCommand {
	
	int r,g,b,wait;

    public LEDWipeContinuous(int r, int g, int b, int wait) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.wipeContinuous(r, g, b, wait);
    }

}
