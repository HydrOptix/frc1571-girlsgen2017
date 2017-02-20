package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	
	private boolean isFinished;
	private double distance, speed, steering;
	DriveSpeed driveSpeedCommand;

    public DriveDistance(double distance, double speed, double steering) {
    	this.distance = distance;
    	this.speed = speed;
    	this.steering = steering;
    }

    protected void initialize() {
    	isFinished = false;
    	driveSpeedCommand = new DriveSpeed(speed, steering);
    	driveSpeedCommand.start();
    	
    	Robot.driveSystem.resetEncoders();
    }

    protected void execute() {
    	
    	double leftDistance = Robot.driveSystem.getEncoderDistance("LEFT");
    	double rightDistance = Robot.driveSystem.getEncoderDistance("RIGHT");
    	
    	if((leftDistance + rightDistance)/2 >= distance) {
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	driveSpeedCommand.end();
    }

    protected void interrupted() {
    	System.out.println("Notice - Drive to distance interrupted. Robot may be off course");
    }
}
