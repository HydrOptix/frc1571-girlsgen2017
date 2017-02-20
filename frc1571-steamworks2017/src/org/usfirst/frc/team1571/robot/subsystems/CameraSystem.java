package org.usfirst.frc.team1571.robot.subsystems;

import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSystem extends Subsystem {

	private final Relay ringLight = RobotMap.ringLight;
	private final Servo tiltServo = RobotMap.cameraTiltServo;

    public void initDefaultCommand() {
    }
    
    public double getTargetDistance() {
    	double targetPos = tiltServo.get();
		return RobotMap.cameraAngleFunctionA * Math.pow(targetPos, 2) + RobotMap.cameraAngleFunctionB * targetPos + RobotMap.cameraAngleFunctionC;
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

