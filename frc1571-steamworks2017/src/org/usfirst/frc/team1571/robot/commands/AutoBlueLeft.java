package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeft extends CommandGroup {

    public AutoBlueLeft() {
        addSequential(new DriveDistance(2.5,1,0));
    }
}
