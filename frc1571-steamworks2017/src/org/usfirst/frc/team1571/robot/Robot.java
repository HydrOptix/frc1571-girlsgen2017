
package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1571.robot.commands.*;
import org.usfirst.frc.team1571.robot.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
	
	Command autonomousCommand;
	public static Command joystickCommand;

	public static Agitator agitator;
	public static CameraSystem cameraSystem;
	public static Climber climber;
	public static DriveSystem driveSystem;
	public static Feeder feeder;
	public static GearSwitch gearSwitch;
	public static Intake intake;
	public static LEDSystem ledSystem;
	public static PowerDistributionSystem powerDistributionSystem;
	public static Shooter shooter;
	NetworkTable preferencesTable;
	public static NetworkTable visionTable;

	SendableChooser<Command> autoChooser = new SendableChooser<>();
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		preferencesTable = RobotMap.initPreferences();
		visionTable = NetworkTable.getTable("GRIP/GRIPContours");
		RobotMap.init();
		
		agitator = new Agitator();
		cameraSystem = new CameraSystem();
		climber = new Climber();
		driveSystem = new DriveSystem();
		feeder = new Feeder();
		gearSwitch = new GearSwitch();
		intake = new Intake();
		ledSystem = new LEDSystem();
		powerDistributionSystem = new PowerDistributionSystem();
		shooter = new Shooter();
		
		oi = new OI();
				
		autoChooser.addDefault("Autodetect Station Auto", new AutoBlueCenter());
		autoChooser.addObject("Blue Center Auto", new AutoBlueCenter());
		autoChooser.addObject("Blue Left Auto", new AutoBlueLeft());
		autoChooser.addObject("Blue Right Auto", new AutoBlueRight());
		autoChooser.addObject("Red Center Auto", new AutoRedCenter());
		autoChooser.addObject("Red Left Auto", new AutoRedLeft());
		autoChooser.addObject("Red Right Auto", new AutoRedRight());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		SmartDashboard.putData(agitator);
		SmartDashboard.putData(cameraSystem);
		SmartDashboard.putData(climber);
		SmartDashboard.putData(climber);
		SmartDashboard.putData(driveSystem);
		SmartDashboard.putData(feeder);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(ledSystem);
		SmartDashboard.putData(powerDistributionSystem);
		SmartDashboard.putData(shooter);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

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
		
		joystickCommand.start();
	}

	@Override
	public void teleopPeriodic() {
		RobotMap.updatePreferences(preferencesTable);
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
