package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeft extends CommandGroup {

    public AutoLeft() {
        addSequential(new DriveDistance(2,1,0));
        addSequential(new TurnAngle(-30,.5));
        addSequential(new Gearbot());
    }
}
