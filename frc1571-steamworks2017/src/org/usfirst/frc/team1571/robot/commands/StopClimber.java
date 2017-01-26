package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StopClimber extends InstantCommand {

    public StopClimber() {
    }

    protected void initialize() {
    	Robot.climber.stopClimber();
    }

}
