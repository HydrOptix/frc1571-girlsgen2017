package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveSpeed extends InstantCommand {
	
	double speed, steering;

    public DriveSpeed(double speed, double steering) {
    	requires(Robot.driveSystem);
        this.speed = speed;
        this.steering = steering;
    }

    protected void initialize() {
    	System.out.println("Driving Speed");
    	Robot.driveSystem.tankDrive(speed, steering);
    }

}
