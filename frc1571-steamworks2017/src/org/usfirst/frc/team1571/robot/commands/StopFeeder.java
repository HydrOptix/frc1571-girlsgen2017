package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StopFeeder extends InstantCommand {

    public StopFeeder() {
    	requires(Robot.feeder);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.feeder.stopFeeder();
    }

}
