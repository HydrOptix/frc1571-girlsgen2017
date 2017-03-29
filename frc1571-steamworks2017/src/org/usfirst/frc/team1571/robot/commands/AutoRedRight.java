package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRight extends CommandGroup {

    public AutoRedRight() {
    	addSequential(new DriveDistance(7,1,0));
        addSequential(new TurnAngle(145,1));
    }
}
