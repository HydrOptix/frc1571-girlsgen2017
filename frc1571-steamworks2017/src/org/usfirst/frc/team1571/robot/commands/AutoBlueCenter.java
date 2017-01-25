package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueCenter extends CommandGroup {

    public AutoBlueCenter() {
        addSequential(new DriveDistance(2.5,1,0));
    }
}
