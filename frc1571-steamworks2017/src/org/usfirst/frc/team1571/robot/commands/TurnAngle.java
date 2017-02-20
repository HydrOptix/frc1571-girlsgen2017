package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnAngle extends Command {
	
	double speed, angle, lastAngle;
	boolean isFinished = false;
	TurnSpeed turnCommand;

    public TurnAngle(double speed, double angle) {
    	this.speed = speed;
    	this.angle = angle;
    }

    protected void initialize() {
    	isFinished = false;
    	
    	Robot.driveSystem.allStop();
    	Robot.driveSystem.resetGyro();
    	Robot.driveSystem.calibrateGyro();
    	
    	turnCommand = new TurnSpeed(speed);
    	turnCommand.start();
    	
    }

    protected void execute() {
    	double currentAngle = Robot.driveSystem.getGyroAngle();
    	if(speed > 0) {
    		if(currentAngle > angle) {
    			isFinished = true;
    		}
    	} else if(speed < 0) {
    		if(currentAngle < angle) {
    			isFinished = true;
    		}
    	} else {
    		isFinished = true;
    		System.out.print("Warning - Tried to turn to an angle with no speed. Why are you doing this?");
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Robot.driveSystem.allStop();;
    }

    protected void interrupted() {
    	System.out.println("Warning - Turn to angle interrupted, robot may be misaligned.");
    }
}
