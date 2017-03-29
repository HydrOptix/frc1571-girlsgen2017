package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class JoystickManager extends Command {

    public JoystickManager() {
    	requires(Robot.agitator);
    	requires(Robot.climber);
    	requires(Robot.driveSystem);
    	requires(Robot.feeder);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Starting Joystick Manager");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double driverXLeft = Robot.oi.driverController.getX(Hand.kLeft);
    	double driverTriggerLeft = Robot.oi.driverController.getTriggerAxis(Hand.kLeft);
    	double driverTriggerRight = Robot.oi.driverController.getTriggerAxis(Hand.kRight);
    	double auxTwistAxis = Robot.oi.auxJoystick.getRawAxis(2);
    	double auxThrottle = (Robot.oi.auxJoystick.getRawAxis(3)-1)/-2;
    	boolean driverButtonX = Robot.oi.driverButtonX.get();
    	boolean auxButtonTrigger = Robot.oi.auxButtonTrigger.get();
    	boolean auxButtonSecondary = Robot.oi.auxButtonSecondary.get();
    	boolean auxButton7 = Robot.oi.auxButton7.get();

    	
    	if(Math.abs(driverXLeft) < Robot.oi.driverController_deadzoneRadiusLStick) {
    		driverXLeft = 0;
    	}
    	if(driverTriggerLeft < Robot.oi.driverController_deadzoneRadiusTriggers) {
    		driverTriggerLeft = 0;
    	}
    	if(driverTriggerRight < Robot.oi.driverController_deadzoneRadiusTriggers) {
    		driverTriggerRight = 0;
    	}
    	if(Math.abs(auxTwistAxis) < Robot.oi.auxJoystick_deadzoneRadiusTwist) {
    		auxTwistAxis = 0;
    	}
    	    	
    	double driveThrottle = driverTriggerRight - driverTriggerLeft;
    	
    	if(driveThrottle != 0) {
    		System.out.println("Driving at " + driveThrottle + "," + driverXLeft);
    		Robot.driveSystem.tankDrive(driveThrottle, driverXLeft);
    		
    		if(driveThrottle > 1) {
    			//Robot.ledSystem.theaterChase(0, 255, 0, (int)((driveThrottle - 1)/-1 * 40));
    		} else {
    			//Robot.ledSystem.theaterChase(255, 0, 0, (int)((driveThrottle + 1) * 40));
    		}
    	} else if(driverXLeft != 0) {
    		double turnThrottle = driverXLeft;
    		System.out.println("Turning at " + driverXLeft);
    		Robot.driveSystem.stationaryTurn(turnThrottle);
    		
    		//Robot.ledSystem.rainbowCycle((int)((Math.abs(turnThrottle) -1)/-1 * 40));
    	} else if(auxTwistAxis != 0){
    		Robot.driveSystem.stationaryTurn(auxTwistAxis * .25);
    	} else {
    		if(auxThrottle > 0) {
    			Robot.driveSystem.tankDrive(auxThrottle * .25, 0);
    		} else {
    			Robot.driveSystem.allStop();
    		}
    	}
    	    	
    	if(auxButtonSecondary) {
    		Robot.shooter.setVelocity(330);
    		if(auxButtonTrigger) {
    			Robot.feeder.startFeeder();
    		} else {
    			Robot.feeder.stopFeeder();
    		}
    		
    	} else {
    		Robot.shooter.setSpeed(0);
    		Robot.feeder.stopFeeder();
    	}
    	
    	if(auxButton7) {
    		Robot.agitator.startAgitator();
    	} else {
    		Robot.agitator.stopAgitator();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
