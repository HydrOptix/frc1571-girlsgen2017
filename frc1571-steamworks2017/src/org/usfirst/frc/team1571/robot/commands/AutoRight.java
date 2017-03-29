package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRight extends CommandGroup {

    public AutoRight() {
    	addSequential(new DriveDistance(2,1,0));
        addSequential(new TurnAngle(30,.5));
        addSequential(new Gearbot());
    }
}
