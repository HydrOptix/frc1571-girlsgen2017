
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

	//Declare all subsystems for easy access
	public static Agitator agitator;
	public static CameraSystem cameraSystem;
	public static Climber climber;
	public static DriveSystem driveSystem;
	public static Feeder feeder;
	public static GearSystem gearSystem;
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
		
		//Initialize new subsystems
		agitator = new Agitator();
		cameraSystem = new CameraSystem();
		climber = new Climber();
		driveSystem = new DriveSystem();
		feeder = new Feeder();
		gearSystem = new GearSystem();
		intake = new Intake();
		ledSystem = new LEDSystem();
		powerDistributionSystem = new PowerDistributionSystem();
		shooter = new Shooter();
		
		oi = new OI();
		
		//add all autonomous commands to the SmartDashboard Chooser object
		autoChooser.addDefault("Center Auto", new AutoCenter());
		autoChooser.addObject("Left Auto", new AutoLeft());
		autoChooser.addObject("Right Auto", new AutoRight());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		//Add all susbsystem information to the SmartDashboard
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
		//Get the autonomous command from the SmartDashboard
		autonomousCommand = autoChooser.getSelected();

		//Start the chosen autonomous command
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		//Check the preferences NetworkTable for updated values
		RobotMap.updatePreferences(preferencesTable);
		
		//Run the scheduler (runs scheduled commands)
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//Create a JoystickManager command
		joystickCommand = new JoystickManager();
		
		//Cancel the autonomous command if it's still running
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		//Start the joystick manager
		joystickCommand.start();
	}

	@Override
	public void teleopPeriodic() {
		//Check the preferences NetworkTable for updated values
		RobotMap.updatePreferences(preferencesTable);
		
		//Run the scheduler (runs scheduled commands)
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
