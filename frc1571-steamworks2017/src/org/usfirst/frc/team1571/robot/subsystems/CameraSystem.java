package org.usfirst.frc.team1571.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
		setDefaultCommand(null);
    }
    
    public double getVisionDistance() {
    	return 0;
    }
}

