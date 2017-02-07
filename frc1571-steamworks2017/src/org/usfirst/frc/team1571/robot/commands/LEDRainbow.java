package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDRainbow extends InstantCommand {
	
	int wait;

    public LEDRainbow(int wait) {
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.rainbow(wait);
    }

}
