package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    	boolean driverButtonX = Robot.oi.driverButtonX.get();
    	boolean auxButtonTrigger = Robot.oi.auxButtonTrigger.get();
    	boolean auxButtonSecondary = Robot.oi.auxButtonSecondary.get();
    	SmartDashboard.putNumber("Left Joystick X", driverXLeft);
    	SmartDashboard.putNumber("Left Trigger", driverTriggerLeft);
    	SmartDashboard.putNumber("Right Trigger", driverTriggerRight);
    	SmartDashboard.putNumber("Joystick Twist Axis", auxTwistAxis);
    	SmartDashboard.putBoolean("X Button", driverButtonX);
    	SmartDashboard.putBoolean("Joystick Trigger", auxButtonTrigger);
    	SmartDashboard.putBoolean("Joystick Secondary Button", auxButtonSecondary);
    	SmartDashboard.putBoolean("Driving", RobotMap.driving);

    	
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
    	    	    	
    	if(driverXLeft == 0 && driverTriggerLeft == 0 && driverTriggerRight == 0 && !driverButtonX) {	//If the the driver is doing anything to drive the robot,
    		RobotMap.driving = false;																	//set the driving variable to true. Otherwise, set the driving variable to false.
    	} else {
    		RobotMap.driving = true;
    	}
    	    	
    	if(!driverButtonX) {
    	
	    	if(RobotMap.driving) {	//If the driver is trying to drive the robot, run the commands needed 
	    		double driveThrottle = driverTriggerRight - driverTriggerLeft; //Find the driving speed by combining the two gamepad trigger inputs
	    		SmartDashboard.putNumber("Virtual Throttle", driveThrottle);
	    		
	    		if(driveThrottle == 0) { //If the robot isn't supposed to be moving forward (throttle is 0) then run the stationary turning command
	    			if(driverXLeft != 0) {
	    				System.out.println("Turning");
	    				Robot.driveSystem.stationaryTurn(driveThrottle);
	    			}
	    		} else {				//If the robot is supposed to be moving forward (throttle is not 0) then run the driving/reduced turning code
	    			System.out.println("Driving");
	    			Robot.driveSystem.tankDrive(driveThrottle, driverXLeft);
	    		}
	    	} else {				//If the driver is not interacting with the robot, run the secondary joystick shooting code
	    		double targetSpeed;
	    		if(auxButtonSecondary) { //If the secondary button is pressed, auto-detect the speed the flywheels need to be running
	    			double distance = Robot.cameraSystem.getTargetDistance();
	    			targetSpeed = distance; //TODO - distance equation
	    		} else { //If the secondary button is not pressed, run the flywheels at the speed specified by the throttle
	    			targetSpeed = (Robot.oi.auxJoystick.getRawAxis(3)-1)/-2;
	    		}
    			Robot.shooter.setSpeed(targetSpeed);
	    		
	    		if(auxButtonTrigger) {
	    			Robot.feeder.startFeeder();
	    			Robot.agitator.startAgitator();
	    		} else {
	    			Robot.feeder.stopFeeder();
	    			Robot.agitator.stopAgitator();
	    		}
	    		
	    	}
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
