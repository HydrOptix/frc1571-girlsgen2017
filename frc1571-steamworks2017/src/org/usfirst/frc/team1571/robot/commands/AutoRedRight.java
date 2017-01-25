package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRight extends CommandGroup {

    public AutoRedRight() {
        addSequential(new DriveDistance(2.5,1,0));
    }
}
