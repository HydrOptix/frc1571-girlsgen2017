
package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.PersistentException;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1571.robot.commands.*;
import org.usfirst.frc.team1571.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	Command autonomousCommand;
	Command joystickCommand;

	public static Agitator agitator;
	public static CameraSystem cameraSystem;
	public static Climber climber;
	public static DriveSystem driveSystem;
	public static Feeder feeder;
	public static Intake intake;
	public static LEDSystem ledSystem;
	public static PowerDistributionSystem powerDistributionSystem;
	public static Shooter shooter;
	NetworkTable preferencesTable;

	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		preferencesTable = RobotMap.initPreferences();
		RobotMap.init();
		
		oi = new OI();
		
		autoChooser.addDefault("Autodetect Station Auto", new AutoBlueCenter());
		autoChooser.addObject("Blue Center Auto", new AutoBlueCenter());
		autoChooser.addObject("Blue Left Auto", new AutoBlueLeft());
		autoChooser.addObject("Blue Right Auto", new AutoBlueRight());
		autoChooser.addObject("Red Center Auto", new AutoRedCenter());
		autoChooser.addObject("Red Left Auto", new AutoRedLeft());
		autoChooser.addObject("Red Right Auto", new AutoRedRight());
		SmartDashboard.putData("Auto mode", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		RobotMap.updatePreferences(preferencesTable);
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		joystickCommand = new JoystickManager();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		if(joystickCommand == null)
			joystickCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		RobotMap.updatePreferences(preferencesTable);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
