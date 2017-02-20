package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class Aimbot extends Command {
	
	double[] defaultValue = new double[0];
	
	double xSpeed;
	double ySpeed;
	int scanningDirection;
	
	boolean xCentered, yCentered;
	
	Timer yWait, xWait;

    public Aimbot() {
        requires(Robot.cameraSystem);
        requires(Robot.driveSystem);
        requires(Robot.feeder);
        requires(Robot.shooter);
    }

    protected void initialize() {
    	yCentered = false;
    	xCentered = false;
    	scanningDirection = 1;
    	
    	yWait = new Timer();
    	xWait = new Timer();
    }

    protected void execute() {
    	double[] centerX = Robot.visionTable.getNumberArray("centerX", defaultValue);
    	double[] centerY = Robot.visionTable.getNumberArray("centerY", defaultValue);
    	
    	if(centerY.length == 2) {
    		Robot.cameraSystem.activateLightRing();
    		
    		if(!yCentered) {	//If we have already centered the servo, we don't really want to move it. Slight movements could uncenter the camera, but mechanical parts should smooth out the inconsistencies
	    		double averageY = (centerY[0] + centerY[1])/2;
	    		
	    		if(RobotMap.cameraPixelHeight/2 + RobotMap.cameraAllowablePixelError > averageY && averageY > RobotMap.cameraPixelHeight/2 - RobotMap.cameraAllowablePixelError) {	//If the camera is centered
	    			if(ySpeed == 0) { 	//If the last time we checked the camera was no longer moving
	    				if(yWait.get() >= .5) {	//If the camera has been stationary for a half second (the camera could continue moving even after the command to stop moving has been issued)
	    					yWait.stop();		//Stop the timer
	    					yCentered = true;	//Mark the Y axis as centered;
	    					System.out.println("Camera: Y is centered");
	    				}
	    			} else {			//If the camera was moving last loop
	    				ySpeed = 0;		//Mark the camera as not moving
	    				yWait.reset();
	    				yWait.start();	//Start the countdown to the stationary target
	    			}
	    			
	    			
	    		} else if(averageY > RobotMap.cameraPixelHeight/2 + RobotMap.cameraAllowablePixelError) { //If the camera is lower than the target (Remember Y=0 starts at the top of the image)
	    			if(averageY < RobotMap.cameraPixelHeight/2 + RobotMap.cameraSlowZonePixels) { //If the camera is inside than slow-moving zone
	    				ySpeed = RobotMap.cameraTiltSlowIncrementRate; //Positive = camera moves up
	    				System.out.print("Camera: Tilting up " + RobotMap.cameraTiltSlowIncrementRate);
	    			} else {
	    				ySpeed = RobotMap.cameraTiltFastIncrementRate;
	    				System.out.print("Camera: Tilting up " + RobotMap.cameraTiltFastIncrementRate);
	    			}
	    			
	    			
	    		} else if(averageY < RobotMap.cameraPixelHeight/2 - RobotMap.cameraAllowablePixelError) {	//If the camera is higher than the target
	    			if(averageY > RobotMap.cameraPixelHeight/2 - RobotMap.cameraSlowZonePixels) { //If the camera is inside than slow-moving zone
	    				ySpeed = RobotMap.cameraTiltSlowIncrementRate * -1; //Negative = camera moves down
	    				System.out.print("Camera: Tilting down " + RobotMap.cameraTiltSlowIncrementRate);
	    			} else {
	    				ySpeed = RobotMap.cameraTiltFastIncrementRate * -1;
	    				System.out.print("Camera: Tilting down " + RobotMap.cameraTiltFastIncrementRate);
	    			}
	    		}
	    		
	    		if(ySpeed != 0) {
	    			double servoPosition = Robot.cameraSystem.getCameraServo();
	    			double newPosition = servoPosition + ySpeed;
	    			Robot.cameraSystem.setCameraServo(newPosition);
	    			System.out.println(" to " + newPosition);
	    		}
	    		
	    		
    		}
    		
    		if(!xCentered) {
	    		double averageX = (centerX[0] + centerX[1])/2;
	    		
	    		if(RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError > averageX && averageX > RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {
	    			if(xSpeed == 0) {
	    				if(xWait.get() >= .5) {
	    					xWait.stop();
	    					xCentered = true;
	    					System.out.println("Camera: X is centered");
	    				}
	    			} else {
	    				xSpeed = 0; //Mark the driveSystem as not moving
	    				xWait.reset();
	    				xWait.start();	//Start the countdown to the stationary target
	    			}
	    		} else if(averageX > RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError) {	//If the camera is too far to the right of the target
	    			if(averageX < RobotMap.visionTargetXCenter + RobotMap.cameraSlowZonePixels) {	//If the camera is inside than slow-moving zone
	    				xSpeed = RobotMap.driveAimbotSlowSpeed * -1;	//Negative = robot turns left
	    				System.out.println("Camera: Turning left at " + (RobotMap.driveAimbotSlowSpeed * 100) + "% speed");
	    			} else {
	    				xSpeed = RobotMap.driveAimbotFastSpeed * -1;
	    				System.out.println("Camera: Turning left at " + (RobotMap.driveAimbotFastSpeed * 100) + "% speed");
	    			}
	    			
	    			
	    		} else if(averageX < RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {	//If the camera is too far to the left of the target
	    			if(averageX > RobotMap.visionTargetXCenter - RobotMap.cameraSlowZonePixels) {	//If the camera is inside than slow-moving zone
	    				xSpeed = RobotMap.driveAimbotSlowSpeed; //Positive = robot turns right
	    				System.out.println("Camera: Turning right at " + (RobotMap.driveAimbotSlowSpeed * 100) + "% speed");
	    			} else {
	    				xSpeed = RobotMap.driveAimbotFastSpeed;
	    				System.out.println("Camera: Turning right at " + (RobotMap.driveAimbotFastSpeed * 100) + "% speed");
	    			}
	    		}
	    		
	    		Robot.driveSystem.stationaryTurn(xSpeed);
	    		
	    		double xDifference = RobotMap.visionTargetXCenter - averageX;
	    		
	    		Robot.oi.driverController.setRumble(RumbleType.kRightRumble, (xDifference-RobotMap.cameraPixelWidth/-2)/RobotMap.cameraPixelWidth);
    		}
    		
    		
    	} else { //If we lose tracking on the target, we want to make sure it is actually gone before completely re-calibrating
    		if(!(xCentered && yCentered)) {	//If we were already centered, let's just keep on shooting. The driver can restart targeting again if we got misaligned. Otherwise, let's start scanning up and down for the target
    			double servoPosition = Robot.cameraSystem.getCameraServo();
    			
    			if(servoPosition <= RobotMap.cameraTiltMinPos) {
    				scanningDirection = 1;
    				Robot.cameraSystem.setCameraServo(RobotMap.cameraTiltMinPos);
    			} else if(servoPosition >= RobotMap.cameraTiltMaxPos) {
    				scanningDirection = -1;
    			} else {
    				double newPosition = servoPosition + (RobotMap.cameraTiltScanIncrementRate * scanningDirection);
    				Robot.cameraSystem.setCameraServo(newPosition);
    				System.out.println("Camera: Scanning from " + servoPosition + " to " + newPosition);
    			}
    		}
    	}
    	
    	if(xCentered && yCentered) {
    		double shootSpeed = Robot.shooter.getSpeedFromDistance(Robot.cameraSystem.getTargetDistance());
    		Robot.shooter.setVelocity(shootSpeed);
    		
    		double shooterVelocity = Robot.shooter.getVelocity();
    		if(shootSpeed + RobotMap.shooterSpeedError > shooterVelocity && shooterVelocity > shootSpeed - RobotMap.shooterSpeedError) {
    			Robot.feeder.startFeeder();
    			Robot.agitator.startAgitator();
    		} else {
    			Robot.feeder.stopFeeder();
    			Robot.agitator.stopAgitator();
    		}
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveSystem.allStop();
    	Robot.shooter.setSpeed(0);
    	Robot.feeder.stopFeeder();
    	Robot.agitator.stopAgitator();
    	Robot.cameraSystem.DeactivateLightRing();
    	Robot.cameraSystem.setCameraServo(RobotMap.cameraTiltDefaultPos);
    	
    	if(RobotState.isOperatorControl()) {
    		Robot.joystickCommand.start();
    	}
    }

    protected void interrupted() {
    }
}
