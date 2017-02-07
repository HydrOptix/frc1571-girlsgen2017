package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class TurnSpeed extends InstantCommand {
	
	double speed;

    public TurnSpeed(double speed) {
        requires(Robot.driveSystem);
        
        this.speed = speed;
    }

    protected void initialize() {
    	Robot.driveSystem.stationaryTurn(speed);
    }
}
