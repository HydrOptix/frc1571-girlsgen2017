package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearClose extends Command {

    public GearClose() {
        requires(Robot.gearSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.gearSystem.close();
    }

    protected boolean isFinished() {
        return Robot.gearSystem.isClosed();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
