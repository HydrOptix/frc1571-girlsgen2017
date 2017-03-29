package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeft extends CommandGroup {

    public AutoBlueLeft() {
        addSequential(new DriveDistance(7,1,0));
        addSequential(new TurnAngle(-145,1));
    }
}
