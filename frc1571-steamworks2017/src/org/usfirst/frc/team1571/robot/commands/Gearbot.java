package org.usfirst.frc.team1571.robot.commands;

import org.usfirst.frc.team1571.robot.Robot;
import org.usfirst.frc.team1571.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Gearbot extends Command {

	double[] defaultValue = new double[0];

	String phase;

	Timer xWait, driveTimer;

	boolean xTimerStarted, driveTimerStarted, isFinished;

	int finalCounts;

	public Gearbot() {
		requires(Robot.cameraSystem);
		requires(Robot.driveSystem);
		requires(Robot.gearSystem);
	}

	protected void initialize() {
		isFinished = false;
		phase = "turning";

	}

	protected void execute() {

		if (phase == "turning") {

			double[] centerX = Robot.visionTable.getNumberArray("centerX", defaultValue);

			if (centerX.length >= 2) {

				double differenceX = (Math.abs(centerX[0] - centerX[1]));

				if (differenceX >= 600) {
					Robot.driveSystem.allStop();
					phase = "startFinal";
				}

				double averageX = (centerX[0] + centerX[1]) / 2;

				if (RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError > averageX
						&& averageX > RobotMap.visionTargetXCenter - RobotMap.visionTargetXCenter) {

					if (xWait.get() >= .5) {
						xWait.stop();
						phase = "driving";
						xTimerStarted = false;
					} else if (xTimerStarted == false) {
						xWait.reset();
						xWait.start();
						xTimerStarted = true;
					}

				} else if (averageX > RobotMap.visionTargetXCenter + RobotMap.cameraAllowablePixelError) {
					if (xTimerStarted) {
						xWait.stop();
						xTimerStarted = false;
					}

					Robot.driveSystem.stationaryTurn(.2);

				} else if (averageX < RobotMap.visionTargetXCenter - RobotMap.cameraAllowablePixelError) {
					if (xTimerStarted) {
						xWait.stop();
						xTimerStarted = false;
					}

					Robot.driveSystem.stationaryTurn(-.2);

				}

			}

		} else if (phase == "driving") {

			Robot.driveSystem.tankDrive(.2, 0);

			if (driveTimerStarted && driveTimer.get() >= 2) {

				Robot.driveSystem.allStop();
				driveTimer.stop();
				driveTimerStarted = false;

			} else if (driveTimerStarted == false) {

				driveTimer.reset();
				driveTimer.start();
				driveTimerStarted = true;

			}

		} else if (phase == "startFinal") {

			finalCounts = Robot.driveSystem.getEncoderCounts("LEFT") + 500;

		} else if (phase == "final") {

			int currentCounts = Robot.driveSystem.getEncoderCounts("LEFT");
			if (currentCounts >= finalCounts) {
				Robot.driveSystem.allStop();
				Robot.gearSystem.open();

				if (Robot.gearSystem.isOpen()) {
					driveTimerStarted = false;
					phase = "reverse";
				}

			} else {
				Robot.driveSystem.tankDrive(.2, 0);
			}

		} else if (phase == "reverse") {
			if (driveTimerStarted = false) {
				driveTimer.reset();
				driveTimer.start();
				driveTimerStarted = true;
			} else if (driveTimer.get() >= 1) {
				Robot.driveSystem.allStop();

				Robot.gearSystem.close();

				if (Robot.gearSystem.isClosed()) {
					isFinished = true;
				}
			}
		}

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.driveSystem.allStop();
	}

	protected void interrupted() {
	}
}
