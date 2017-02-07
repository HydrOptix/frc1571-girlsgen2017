package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDSolid extends InstantCommand {
	
	int r,g,b,wait;

    public LEDSolid(int r, int g, int b, int wait) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    	this.wait = wait;
    }

    protected void initialize() {
    	System.out.println("LED Solid");
    	Robot.ledSystem.solid(r, g, b, wait);
    }

}
