package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

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
    	distance = distance * RobotMap.countsPerRevolution;
    	isFinished = false;
    	Robot.driveSystem.resetEncoders();
    }

    protected void execute() {
    	
    	
    	double leftDistance = Robot.driveSystem.getEncoderDistance("LEFT");
    	double rightDistance = Robot.driveSystem.getEncoderDistance("RIGHT");
    	
    	System.out.println("Left: " + leftDistance);
    	System.out.println("Right: " + rightDistance);
    	
    	System.out.println("At distance" + (leftDistance + rightDistance)/2);
    	System.out.println("To distance" + distance);
    	
    	if((leftDistance + rightDistance)/2 >= distance) {
    		Robot.driveSystem.allStop();
    		isFinished = true;
    	} else {
        	Robot.driveSystem.tankDrive(speed, steering);
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Robot.driveSystem.allStop();
    }

    protected void interrupted() {
    	System.out.println("Notice - Drive to distance interrupted. Robot may be off course");
    }
}
