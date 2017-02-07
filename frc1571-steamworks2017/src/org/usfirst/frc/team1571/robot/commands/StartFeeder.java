package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StartFeeder extends InstantCommand {

    public StartFeeder() {
    }

    protected void initialize() {
    	Robot.feeder.startFeeder();
    }

}
