package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetShootSpeed extends InstantCommand {
	
	double speed;

    public SetShootSpeed(double speed) {
        this.speed = speed;
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.shooter.setSpeed(speed);
    }

}
