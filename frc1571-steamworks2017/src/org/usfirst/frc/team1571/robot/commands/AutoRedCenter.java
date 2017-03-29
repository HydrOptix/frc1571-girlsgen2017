package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedCenter extends CommandGroup {

    public AutoRedCenter() {
    	addSequential(new DriveDistance(22,1,0));
    }
}
