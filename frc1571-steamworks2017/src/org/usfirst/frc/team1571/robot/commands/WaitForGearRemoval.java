package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForGearRemoval extends Command {

	Timer delayTimer;
	boolean timerStarted;
	boolean isFinished;

    public WaitForGearRemoval() {
    	requires(Robot.gearSwitch);
    }

    protected void initialize() {
    	delayTimer = new Timer();
    	timerStarted=false;
    }

    protected void execute() {
    	if(!Robot.gearSwitch.getPressed() && !timerStarted) {
    		delayTimer.start();
    		timerStarted=true;
    	}
    	
    	if(timerStarted && delayTimer.hasPeriodPassed(RobotMap.gearRemoveDelay)) {
    		delayTimer.stop();
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
