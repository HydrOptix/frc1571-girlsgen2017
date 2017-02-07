package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StopIntake extends InstantCommand {

    public StopIntake() {
    	requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.stopIntake();
    }

}
