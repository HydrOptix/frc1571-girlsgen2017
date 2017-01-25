package org.usfirst.frc.team1571.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Declare all components on the robot.
	
	//Agitator components and variables
	public static CANTalon agitatorTalon;
		public static double agitatorSpeed = 1.00;
	
	//CameraSystem components and variables
	public static AxisCamera axisCamera;
	public static Servo cameraTiltServo;
		public static double cameraTiltDefaultPos = 0.00;	//When the camera isn't doing anything, reset it to this position
		public static double cameraTiltMinPos = 0.00;		//The minimum position the camera will tilt to
		public static double cameraTiltMaxPos = 1.00;		//The maximum position the camera will tilt to. Sometimes you don't want the camera to rotate upside down.
		public static double cameraTiltMinAngle = 0.00;		//The next two variables are relative to the ground. They are used to calculate angles and distance to the boiler.
		public static double cameraTiltMaxAngle = 201.00;	//The maximum angle the servo can rotate
	
	//Climber components and variables
	public static CANTalon climberTalon;
		public static double climbSpeed = 1.00;
	
	//DriveSystem components and variables
	public static CANTalon driveTalonRightFront;
	public static CANTalon driveTalonRightBack;
	
	public static CANTalon driveTalonLeftFront;
	public static CANTalon driveTalonLeftBack;
	
	public static ADXRS450_Gyro steeringGyro;
	
	public static Encoder driveLeftEncoder;
	public static Encoder driveRightEncoder;
		public static double wheelDiameter = .5; //Wheel diameter in feet. Used to calculate distance the robot is traveling
		public static double gearRatio = 10/1; //Gear ratio from encoder to wheel. Used to calculate distance the robot is traveling
		public static int countsPerRevolution = 20;
		public static double distancePerCount;
	
	/* The max steering radius of the robot on a scale of 0 to 2.
	0 means the robot doesn't turn (E.G. Sean Jim mode). Don't set it to 0.
	1 means at max turning (joystick all the way to one side) one set of wheels will be stationary and the other will be turning full speed.
	2 means the wheels will be turning opposite directions.
	So, if you set the max turning radius to .75, the most the robot will turn while moving is with 100% one side speed and 25% on the other side.
	This value does not affect stationary turning.*/
	public static double maxSteering = 1.00;
	
	/*The max speed the robot turns while stationary on a scale of 0 to 1.
	 * 0 means the robot doesn't turn. Again, don't set it to 0
	 * 1 means the wheels drive opposing each other at full speed
	 * If you made the value negative you could really mess with the driver by making the robot turn the wrong way.*/
	public static double turnSpeed = 1.00;
	
	/* The max speed the robot will drive forward on a scale of 0 to 1.
	 * 0 means the robot doesn't drive forward. Don't set this to 0.
	 * 1 means the robot operates at 100% speed while driving.*/
	public static double driveSpeed = 1.00;
	
	/* The allowable gyro speed without correcting for straight driving. TODO - test gyro values to see what is reasonable
	 * Only active when the robot is supposed to be driving straight*/
	public static double allowableGyroError = 0;
	
	/*The rate at which to add or subtract speed to each side when the gyro senses it is not driving straight
	 * Only active when the robot is supposed to be driving sraight*/
	public static double straightSteeringAdjustRate = .01;
	
	//Feeder components and variables
	public static CANTalon feederTalon;
		public static double feederSpeed = 1.00;
	
	//Intake components and variables
	public static CANTalon intakeTalon;
		public static double intakeSpeed = 1.00;
	
	//LEDSystem components and variables
	public static SerialPort usbSerial;
		
	//PowerDistributionSystem components and variables
	public static PowerDistributionPanel powerDistributionPanel;
	
	//Shooter components and variables
	public static CANTalon shooterTalon;
		public static double shooterSpeed = 1.00;
		public static int shooterEncCodesPerRev = 20;
	
	//Other various global variables
	public static boolean driving; //variable used to determine whether the shooter joystick should be used for aiming
	
	public static void init() {
		//Instantiate all components to pass to Robot.java and add them to LiveWindow
		
		//Climber components
		climberTalon = new CANTalon(5);
			LiveWindow.addActuator("Climber", "Climber Talon", climberTalon);
		
		//Collector components
		intakeTalon = new CANTalon(6);
			LiveWindow.addActuator("Collector", "Collector Talon", intakeTalon);
		
		//DriveSystem components
		driveTalonRightFront = new CANTalon(0);
			LiveWindow.addActuator("Drive System", "Right Front Wheel", driveTalonRightFront);
		driveTalonRightBack = new CANTalon(1);
			LiveWindow.addActuator("Drive System", "Right Back Wheel", driveTalonRightBack);
		driveTalonLeftFront = new CANTalon(2);
			LiveWindow.addActuator("Drive System", "Left Front Wheel", driveTalonLeftFront);
		driveTalonLeftBack = new CANTalon(3);
			LiveWindow.addActuator("Drive System", "Left Back Wheel", driveTalonLeftBack);
			
		steeringGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			LiveWindow.addSensor("Drive System", "Steering Gyro", steeringGyro);
			
		driveLeftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
			LiveWindow.addSensor("Drive System", "Steering Encoder", driveLeftEncoder);
				
		//PowerDistributionSystem Componenents
		powerDistributionPanel = new PowerDistributionPanel();
		LiveWindow.addSensor("Power Distribution System", "Power Distribution Panel", powerDistributionPanel);
		
		//Shooter components
		shooterTalon = new CANTalon(4);
			LiveWindow.addActuator("Shooter", "Flywheel", shooterTalon);
			shooterTalon.changeControlMode(CANTalon.TalonControlMode.Speed); //We definitely need an encoder for this
			//TODO - Do we want to control the flywheel through closed loop or in the program?
			
	}
	
	public static NetworkTable initPreferences() {
		NetworkTable receivedPreferences = NetworkTable.getTable("preferencesTable");
		
		//Climber variables
		putValueIfEmpty(receivedPreferences, "Climb Speed", climbSpeed);
		
		//Collector variables
		putValueIfEmpty(receivedPreferences, "Collect Speed", intakeSpeed);
		
		//DriveSystem variables
		putValueIfEmpty(receivedPreferences, "Wheel Diameter", wheelDiameter);
		putValueIfEmpty(receivedPreferences, "Gear Ratio", gearRatio);
		putValueIfEmpty(receivedPreferences, "Drive Counts Per Revolution", countsPerRevolution);
		putValueIfEmpty(receivedPreferences, "Max Steering", maxSteering);
		putValueIfEmpty(receivedPreferences, "Turn Speed", turnSpeed);
		putValueIfEmpty(receivedPreferences, "Drive Speed", driveSpeed);
		putValueIfEmpty(receivedPreferences, "Allowable Gyro Error", allowableGyroError);
		putValueIfEmpty(receivedPreferences, "Straight Steering Adjust Rate", straightSteeringAdjustRate);
		
		distancePerCount = 360/countsPerRevolution*wheelDiameter;
		
		//LEDSystem variables
		usbSerial = new SerialPort(9600, SerialPort.Port.kUSB);
		
		//PowerDistributionSystem variables
		powerDistributionPanel = new PowerDistributionPanel();
		
		//Shooter variables
		putValueIfEmpty(receivedPreferences, "Flywheel Speed", shooterSpeed);
		
		return receivedPreferences;
	}
	
	public static void updatePreferences(NetworkTable preferencesTable) {
		
		//Climber variables
		climbSpeed = preferencesTable.getNumber("Climb Speed", climbSpeed);
		
		//Intake variables
		intakeSpeed = preferencesTable.getNumber("Collect Speed", intakeSpeed);
		
		//DriveSystem variables
		wheelDiameter = preferencesTable.getNumber("Wheel Diameter", wheelDiameter);
		gearRatio = preferencesTable.getNumber("Gear Ratio", gearRatio);
		countsPerRevolution = (int)preferencesTable.getNumber("Drive Counts Per Revolution", countsPerRevolution);
		maxSteering = preferencesTable.getNumber("Max Steering", maxSteering);
		turnSpeed = preferencesTable.getNumber("Turn Speed", turnSpeed);
		driveSpeed = preferencesTable.getNumber("Drive Speed", driveSpeed);
		allowableGyroError = preferencesTable.getNumber("Allowable Gyro Error", allowableGyroError);
		straightSteeringAdjustRate = preferencesTable.getNumber("Straight Steering Adjust Rate", straightSteeringAdjustRate);
		
		distancePerCount = 360/countsPerRevolution*wheelDiameter;

		
		//PowerDistributionPanel variables
		
		//Shooter variables
		shooterSpeed = preferencesTable.getNumber("Flywheel Speed", shooterSpeed);
		
	}
	
	private static void putValueIfEmpty(NetworkTable table, String key, Object value) {
		table.putValue(key, table.getValue(key, value)); //Get the preference's value in the table, and if it is null set it to the value of the default variable (at the top of RobotMap)
	}
}
