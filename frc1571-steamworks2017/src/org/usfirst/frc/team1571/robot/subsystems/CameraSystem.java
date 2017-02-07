package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSystem extends Subsystem {

	private final Relay ringLight = RobotMap.ringLight;
	private final Servo tiltServo = RobotMap.cameraTiltServo;

    public void initDefaultCommand() {
    }
    
    public double getTargetDistance() {
    	double targetAngle = (tiltServo.get()-RobotMap.cameraTiltMinPos)/(RobotMap.cameraTiltMaxPos-RobotMap.cameraTiltMinPos)*RobotMap.cameraTiltMaxAngle+RobotMap.cameraTiltMinAngle;
		double targetDistance = RobotMap.cameraAngleFunctionA * Math.pow(targetAngle, 2) + RobotMap.cameraAngleFunctionB * targetAngle + RobotMap.cameraAngleFunctionC;
		return targetDistance;
    }
    
    public void activateLightRing() {
    	ringLight.setDirection(Relay.Direction.kReverse);
    	ringLight.set(Relay.Value.kOn);
    }
    
    public void DeactivateLightRing() {
    	ringLight.set(Relay.Value.kOff);
    }
    
    public void setCameraServo(double pos) {
    	tiltServo.set(pos);
    }
    
    public double getCameraServo() {
    	return tiltServo.get();
    }
}

