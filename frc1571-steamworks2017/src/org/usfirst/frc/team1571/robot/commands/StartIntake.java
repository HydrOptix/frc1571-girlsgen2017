package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StartIntake extends InstantCommand {

    public StartIntake() {
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.startIntake();
    }

}
