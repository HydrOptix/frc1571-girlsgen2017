package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBlueCenter extends CommandGroup {

    public AutoBlueCenter() {
        addSequential(new DriveDistance(4,1,0));
        addSequential(new TurnAngle(-90,1));
        addSequential(new DriveDistance(11,1,0));
        addSequential(new TurnAngle(90,1));
        addSequential(new DriveDistance(3,1,0));
        addSequential(new TurnAngle(-145,1));
    }
}
