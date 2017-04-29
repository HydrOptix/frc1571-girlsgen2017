package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearOpen extends Command {

    public GearOpen() {
        requires(Robot.gearSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.gearSystem.open();
    }

    protected boolean isFinished() {
        return Robot.gearSystem.isOpen();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
