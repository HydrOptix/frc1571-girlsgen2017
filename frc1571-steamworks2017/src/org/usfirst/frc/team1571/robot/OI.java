package org.usfirst.frc.team1571.robot;

import org.usfirst.frc.team1571.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public XboxController driverController;
		public double driverController_deadzoneRadiusLStick = .2;
		public double driverController_deadzoneRadiusRStick = .2;
		public double driverController_deadzoneRadiusTriggers = .15;
		
	public JoystickButton driverButtonA;
	public JoystickButton driverButtonX;
	public JoystickButton driverButtonRightBumper;
		
	public Joystick auxJoystick;
		public double auxJoystick_deadzoneRadiusTwist = .28;
		
	public JoystickButton auxButtonTrigger;
	public JoystickButton auxButtonSecondary;
	public JoystickButton auxButton7;
	
	public OI() {
		
		driverController = new XboxController(0);
		auxJoystick = new Joystick(1);
		
		driverButtonA = new JoystickButton(driverController, 1);
			driverButtonA.whenPressed(new GearOpen());
			
		driverButtonX = new JoystickButton(driverController, 3);
			driverButtonX.whenPressed(new GearClose());
			
		driverButtonRightBumper = new JoystickButton(driverController, 5);
			
		auxButtonTrigger = new JoystickButton(auxJoystick, 1);
		auxButtonSecondary = new JoystickButton(auxJoystick, 2);
		auxButton7 = new JoystickButton(auxJoystick, 7);
		
		//SmartDashboard buttons
		SmartDashboard.putData("Joystick Manager Command", new JoystickManager());
		SmartDashboard.putData("Turn to Angle", new TurnAngle(-30, .4));
		SmartDashboard.putData("Drive to Distance", new DriveDistance(2.5, .5, 0));
		
	}
}
