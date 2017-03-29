package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenter extends CommandGroup {

    public AutoCenter() {
        addSequential(new Gearbot());
    }
}
