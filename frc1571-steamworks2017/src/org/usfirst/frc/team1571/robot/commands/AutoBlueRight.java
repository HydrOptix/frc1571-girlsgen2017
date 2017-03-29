package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueRight extends CommandGroup {

    public AutoBlueRight() {
        addSequential(new DriveDistance(17.25,1,0));
        addSequential(new TurnAngle(-90,1));
        addSequential(new DriveDistance(22,1,0));
        addSequential(new TurnAngle(-90,1));
        addSequential(new DriveDistance(10,1,0));
        addSequential(new TurnAngle(35,.5));
    }
}
