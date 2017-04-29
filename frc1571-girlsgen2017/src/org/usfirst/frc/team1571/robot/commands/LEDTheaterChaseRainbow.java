package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDTheaterChaseRainbow extends InstantCommand {
	
	int wait;

    public LEDTheaterChaseRainbow(int wait) {
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.theaterChaseRainbow(wait);
    }

}
