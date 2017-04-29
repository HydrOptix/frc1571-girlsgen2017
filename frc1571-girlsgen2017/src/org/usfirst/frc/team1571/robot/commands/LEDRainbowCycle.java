package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LEDRainbowCycle extends InstantCommand {
	
	int wait;

    public LEDRainbowCycle(int wait) {
    	this.wait = wait;
    }

    protected void initialize() {
    	Robot.ledSystem.rainbowCycle(wait);
    }

}
