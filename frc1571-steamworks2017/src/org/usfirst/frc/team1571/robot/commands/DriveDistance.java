package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	private boolean isFinished;
	private double distance, speed, steering;

    public DriveDistance(double distance, double speed, double steering) {
    	this.distance = distance;
    	this.speed = speed;
    	this.steering = steering;
    	requires(Robot.driveSystem);
    }

    protected void initialize() {
    	isFinished = false;
    }

    protected void execute() {
    	Robot.driveSystem.tankDrive(speed, steering);
    	
    	double leftCounts = Robot.driveSystem.getEncoderDistance("LEFT");
    	double rightCounts = Robot.driveSystem.getEncoderDistance("RIGHT");
    	
    	if((leftCounts + rightCounts)/2 > distance) {
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Robot.driveSystem.tankDrive(0, 0);
    }

    protected void interrupted() {
    	System.out.println("Notice - Drive to distance interrupted. Robot may be off course");
    }
}
