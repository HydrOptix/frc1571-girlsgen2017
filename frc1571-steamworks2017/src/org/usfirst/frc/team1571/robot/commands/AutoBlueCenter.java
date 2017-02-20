package org.usfirst.frc.team1571.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBlueCenter extends CommandGroup {

    public AutoBlueCenter() {
        addParallel(new LEDTheaterChase(0,255,0,10));
        addSequential(new DriveDistance(2.5,1,0));
        
        addParallel(new LEDRainbowCycle(5));
        addSequential(new WaitForGearRemoval());
        
        addParallel(new LEDTheaterChase(255,0,0,10));
        addSequential(new DriveDistance(-2.5,-1,0));
        addSequential(new TurnAngle(1,-90));
        addSequential(new DriveDistance(2.5,1,0));
        
        addParallel(new LEDRainbowCycle(5));
        addSequential(new Aimbot());
    }
}
