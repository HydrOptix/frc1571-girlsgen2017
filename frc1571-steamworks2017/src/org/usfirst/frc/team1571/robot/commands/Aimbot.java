package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
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
    	Robot.cameraSystem.activateLightRing();
    }

    protected void execute() {
    	double[] centerX = Robot.visionTable.getNumberArray("centerX", defaultValue);
    	double[] centerY = Robot.visionTable.getNumberArray("centerY", defaultValue);
    	
    	if(centerY.length == 2) {
    		Robot.cameraSystem.activateLightRing();


    		
    		if(!yCentered && centerY.length == 2) {	//If we have already centered the servo, we don't really want to move it. Slight movements could uncenter the camera, but mechanical parts should smooth out the inconsistencies
        		double averageY = (centerY[0] + centerY[1])/2;

	    		if(RobotMap.cameraPixelHeight/2 + RobotMap.cameraAllowablePixelError > averageY && averageY > RobotMap.cameraPixelHeight/2 - RobotMap.cameraAllowablePixelError) {	//If the camera is centered
	    			double servoAngle = Robot.cameraSystem.getCameraServo();
	    			if(servoAngle > .855 && servoAngle < .86) {
	    				Robot.driveSystem.allStop();
	    				yCentered = true;
	    			} else if(servoAngle > .86) {
	    				Robot.driveSystem.tankDrive(-.2, 0);
	    			} else {
	    				Robot.driveSystem.tankDrive(.2, 0);
	    			}
	    			
	    			
	    		} else if(averageY < RobotMap.cameraPixelHeight/2 + RobotMap.cameraAllowablePixelError) { //If the camera is lower than the target (Remember Y=0 starts at the top of the image)
	    			Robot.driveSystem.allStop();
	    			
	    			if(averageY > RobotMap.cameraPixelHeight/2 + RobotMap.cameraSlowZonePixels) { //If the camera is inside than slow-moving zone
	    				ySpeed = RobotMap.cameraTiltSlowIncrementRate; //Positive = camera moves up
	    				System.out.print("Camera: Tilting up " + RobotMap.cameraTiltSlowIncrementRate);
	    			} else {
	    				ySpeed = RobotMap.cameraTiltFastIncrementRate;
	    				System.out.print("Camera: Tilting up " + RobotMap.cameraTiltFastIncrementRate);
	    			}
	    			
	    			
	    		} else if(averageY > RobotMap.cameraPixelHeight/2 - RobotMap.cameraAllowablePixelError) {	//If the camera is higher than the target
	    			Robot.driveSystem.allStop();
	    			
	    			if(averageY < RobotMap.cameraPixelHeight/2 - RobotMap.cameraSlowZonePixels) { //If the camera is inside than slow-moving zone
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
    		
    		if(!xCentered && centerX.length == 2) {
        		double averageX = (centerX[0] + centerX[1])/2;

	    		
	    		if(RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError > averageX && averageX > RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {
	    			if(xSpeed == 0) {
	    				if(xWait.get() >= .5 && yCentered) {
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
	    				xSpeed = RobotMap.driveAimbotSlowSpeed;	//Negative = robot turns left
	    				System.out.println("Camera: Turning left at " + (RobotMap.driveAimbotSlowSpeed * 100) + "% speed");
	    			} else {
	    				xSpeed = RobotMap.driveAimbotFastSpeed;
	    				System.out.println("Camera: Turning left at " + (RobotMap.driveAimbotFastSpeed * 100) + "% speed");
	    			}
	    			
	    			
	    		} else if(averageX < RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {	//If the camera is too far to the left of the target
	    			if(averageX > RobotMap.visionTargetXCenter - RobotMap.cameraSlowZonePixels) {	//If the camera is inside than slow-moving zone
	    				xSpeed = RobotMap.driveAimbotSlowSpeed * -1; //Positive = robot turns right
	    				System.out.println("Camera: Turning right at " + (RobotMap.driveAimbotSlowSpeed * 100) + "% speed");
	    			} else {
	    				xSpeed = RobotMap.driveAimbotFastSpeed * -1;
	    				System.out.println("Camera: Turning right at " + (RobotMap.driveAimbotFastSpeed * 100) + "% speed");
	    			}
	    		}
	    		
	    		Robot.driveSystem.stationaryTurn(xSpeed);	    		
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
    		double speedFromDistance = 300;
    		
    		Robot.shooter.setVelocity(speedFromDistance/.85);
    		
    		double shooterVelocity = Robot.shooter.getVelocity();
    		System.out.println("Current Speed at " + shooterVelocity);
    		if(speedFromDistance + RobotMap.shooterSpeedError > shooterVelocity && shooterVelocity > speedFromDistance - RobotMap.shooterSpeedError) {
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
