package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.HAL;

/**
 *
 */
public class Aimbot extends Command {
	
	double[] defaultValue = new double[0];
	boolean yCentered;
	boolean xCentered;
	boolean hadTarget;
	boolean scanningUp;

    public Aimbot() {
        requires(Robot.cameraSystem);
        requires(Robot.driveSystem);
        requires(Robot.feeder);
        requires(Robot.ledSystem);
        requires(Robot.shooter);
    }

    protected void initialize() {
    	yCentered = false;
    	xCentered = false;
    	hadTarget = false;
    }

    protected void execute() {
    	double[] centerX = Robot.visionTable.getNumberArray("centerX", defaultValue);
    	double[] centerY = Robot.visionTable.getNumberArray("centerY", defaultValue);
    	
    	if(centerY != defaultValue) {
    		
    		if(centerY.length == 2) {
    			double averageY = (centerY[0] + centerY[1])/2;
    			
				double servoIncrementValue;
    			if(averageY > RobotMap.cameraPixelHeight/2 + RobotMap.cameraAllowablePixelError) {
    				
    				if(averageY > RobotMap.cameraPixelHeight/2 + RobotMap.cameraSlowZonePixels) {
    					servoIncrementValue = RobotMap.cameraTiltFastIncrementRate;
    					System.out.println("Moving down quickly");
    				} else {
    					servoIncrementValue = RobotMap.cameraTiltSlowIncrementRate;
    					System.out.println("Moving down slowly");
    				}
    				
    			} else if(averageY < RobotMap.cameraPixelHeight/2 - RobotMap.cameraAllowablePixelError) {
    				
    				if(averageY < RobotMap.cameraPixelHeight/2 - RobotMap.cameraSlowZonePixels) {
    					servoIncrementValue = RobotMap.cameraTiltFastIncrementRate * -1;
    					System.out.println("Moving up quickly");
    				} else {
    					servoIncrementValue = RobotMap.cameraTiltSlowIncrementRate * -1;
    					System.out.println("Moving up slowly");
    				}
    				
    			} else {
    				System.out.println("Centered");
    				servoIncrementValue = 0;
    			}
    			
    			if(servoIncrementValue != 0) {
    				Robot.cameraSystem.setCameraServo(Robot.cameraSystem.getCameraServo() + (servoIncrementValue * Math.abs(480/2-averageY)));
    				yCentered = false;
    			} else {
    				yCentered = true;
    			}
    			
    			hadTarget = true;
    		} else if(centerY.length > 2) {
    			System.out.println("Too many countours found");
    			yCentered = false;
    		} else if(centerY.length < 2) {
    			System.out.println("Too few contours found");
    			yCentered = false;
    			
    			if(hadTarget) {
    				Robot.cameraSystem.setCameraServo(RobotMap.cameraTiltDefaultPos);
    				scanningUp = true;
    			} else {
    				double cameraPos = Robot.cameraSystem.getCameraServo();
    				if(RobotMap.cameraTiltMinPos < RobotMap.cameraTiltMaxPos) {
    					if(scanningUp) {
    						if(cameraPos >= RobotMap.cameraTiltMaxPos) {
    							scanningUp = false;
    						} else {
    							Robot.cameraSystem.setCameraServo(cameraPos + RobotMap.cameraTiltScanIncrementRate);
    						}
    					} else {
    						if(cameraPos <= RobotMap.cameraTiltMinPos) {
    							scanningUp = true;
    						} else {
    							Robot.cameraSystem.setCameraServo(cameraPos - RobotMap.cameraTiltScanIncrementRate);
    						}
    					}
    					
    					
    				} else if(RobotMap.cameraTiltMinPos > RobotMap.cameraTiltMaxPos){
    					if(!scanningUp) {
    						if(cameraPos >= RobotMap.cameraTiltMaxPos) {
    							scanningUp = true;
    						} else {
    							Robot.cameraSystem.setCameraServo(cameraPos + RobotMap.cameraTiltScanIncrementRate);
    						}
    					} else {
    						if(cameraPos <= RobotMap.cameraTiltMinPos) {
    							scanningUp = false;
    						} else {
    							Robot.cameraSystem.setCameraServo(cameraPos - RobotMap.cameraTiltScanIncrementRate);
    						}
    					}
    				}
    			}
    			
    			hadTarget = false;
    		}
    		
    	} else {
    		System.out.println("No GRIP data received");
    	}
    	
    	if(centerX != defaultValue) {
    		if(centerX.length == 2) {
    			double averageX = (centerX[0] + centerY[1])/2;
    			
    			double turnSpeed;
    			if(averageX > RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError) {
    				
    				if(averageX > RobotMap.visionTargetXCenter + RobotMap.cameraSlowZonePixels) {
    					turnSpeed = RobotMap.driveAutoaimFastSpeed * -1;
    					System.out.println("Turning left quickly");
    				} else {
    					turnSpeed = RobotMap.driveAutoaimSlowSpeed * -1;
    					System.out.println("Turning left slowly");
    				}
    				
    			} else if(averageX < RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {
    				
    				if(averageX < RobotMap.visionTargetXCenter - RobotMap.cameraSlowZonePixels) {
    					turnSpeed = RobotMap.driveAutoaimFastSpeed;
    					System.out.println("Turning right quickly");
    				} else {
    					turnSpeed = RobotMap.driveAutoaimSlowSpeed;
    					System.out.println("Turning right slowly");
    				}
    				
    			} else {
    				System.out.println("Centered");
    				turnSpeed = 0;
    			}
    			
    			if(turnSpeed != 0) {
    				Robot.driveSystem.stationaryTurn(turnSpeed);
    				xCentered = false;
    			} else {
    				xCentered = true;
    			}
    			
    			
    		} else if(centerX.length > 2) {
    			xCentered = false;
    		} else if(centerX.length < 2) {
    			xCentered = false;
    		}
    	}
    	
    	if(xCentered && yCentered) {
    		
    	} else {
    		Robot.agitator.stopAgitator();
    		Robot.feeder.stopFeeder();
    		Robot.shooter.setSpeed(0);
    	}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.agitator.stopAgitator();
		Robot.feeder.stopFeeder();
		Robot.shooter.setSpeed(0);
		Robot.driveSystem.stationaryTurn(0);
		Robot.cameraSystem.setCameraServo(RobotMap.cameraTiltDefaultPos);
		
		if(RobotState.isOperatorControl()) {
			Robot.joystickCommand.start();
		}
    }

    protected void interrupted() {
    }
}
