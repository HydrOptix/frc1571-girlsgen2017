package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleFeeder extends InstantCommand {

    public ToggleFeeder() {
        requires(Robot.feeder);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.feeder.toggleFeeder();
    }

}
