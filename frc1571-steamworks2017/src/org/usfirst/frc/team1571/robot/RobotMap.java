package org.usfirst.frc.team1571.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	//Declare all components on the robot.
	
	//Agitator components and variables
	public static CANTalon agitatorTalon;
		public static double agitatorSpeed = 1.00;
	
	//CameraSystem components and variables
	public static Relay ringLight;
	public static Servo cameraTiltServo;
		public static double cameraTiltDefaultPos = .9;	//When the camera isn't doing anything, reset it to this position
		public static double cameraTiltMinPos = .9;		//The minimum position the camera will tilt to
		public static double cameraTiltMaxPos = .5;		//The maximum position the camera will tilt to. Sometimes you don't want the camera to rotate upside down.
		public static double cameraTiltMinAngle = 0.00;	//The next two variables are relative to the ground. They are used to calculate angles and distance to the boiler.
		public static double cameraTiltMaxAngle = 90.00;//The maximum angle the servo can rotate to
		
		public static double cameraAngleFunctionA = 0;
		public static double cameraAngleFunctionB = -0.578;
		public static double cameraAngleFunctionC = 29.0;
		
		public static double cameraPixelWidth = 640;
		public static double cameraPixelHeight = 480;
		
		public static int cameraAllowablePixelError = 10;
		public static int cameraSlowZonePixels = 64;
		public static double cameraTiltFastIncrementRate = .00005;
		public static double cameraTiltSlowIncrementRate = .00002;
		public static double cameraTiltScanIncrementRate = .01;
		
		public static double cameraToShooterDistanceFeet = -4/12; //Positive: shooter exit point is behind camera. Negative: shooter exit point is in front of camera
		public static int visionTargetXCenter = 640/2; //If the shooter is slightly off-center, set the center for the vision target to this pixel
		
	
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
		public static int countsPerRevolution = 80; //The number of counts the encoder outputs per revolution (don't forget to multiply by the encoding factor)
		public static double distancePerCount = (Math.PI * wheelDiameter)/(gearRatio * countsPerRevolution);
	
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
	
	public static double driveAutoaimFastSpeed = .5;
	public static double driveAutoaimSlowSpeed = .1;
	
	//Feeder components and variables
	public static CANTalon feederTalon;
		public static double feederSpeed = 1.00;
		
	//GearSwitch components and variables
	public static DigitalInput gearSwitch;
		public static boolean reverseGearSwitch = false;
		public static double gearRemoveDelay = 1.0;
	
	//Intake components and variables
	public static CANTalon intakeTalon;
		public static double intakeSpeed = 1.00;
	
	//LEDSystem components and variables
	public static SerialPort usbSerial; //I was having issues with the USB COM port not responding all the time, so LEDs might go unused if I don't figure it out
		public static double serialDelay = .0010; //delay before another LED command will be sent
		
	//PowerDistributionSystem components and variables
	public static PowerDistributionPanel powerDistributionPanel;
	
	//Shooter components and variables
	public static CANTalon shooterTalon;
		public static double shooterSpeed = 1.00;
		public static int shooterEncCodesPerRev = 20;
		public static double shooterSpeedFunctionA = -.01;	//These are the coefficients for a parabolic velocity function (y=Ax^2+Bx+C) used to calculate the speed needed to shoot the ball far enough.
		public static double shooterSpeedFunctionB = 1.236;	//Example functions that we used in 2017 can be found at https://docs.google.com/spreadsheets/d/1XohjcKDPCyi3CSsj_TRhFFEKfISIe4YYJaIspVYB7Hk/
		public static double shooterSpeedFunctionC = 18.202;//If you look at the results chart, Google Sheets has calculated a parabolic function for each line. Us the numbers before each x for these values.
		
	//UltrasonicSensor components and variables
	public static Ultrasonic ultrasonicSensor;
	
	//Other various global variables
	public static boolean driving; //variable used to determine whether the shooter joystick should be used for aiming
	
	public static void init() {
		//Instantiate all components to pass to Robot.java and add them to LiveWindow
		
		//Agitator components
		agitatorTalon = new CANTalon(8);
			LiveWindow.addActuator("Agitator", "Agitator Talon", agitatorTalon);
		
		//CameraSystem components
		cameraTiltServo = new Servo(0);
			LiveWindow.addActuator("Camera System", "Camera Tilt Servo", cameraTiltServo);
		ringLight = new Relay(0);
			LiveWindow.addActuator("Camera System", "Camera Ring Light", ringLight);
		
		//Climber components
		climberTalon = new CANTalon(3);
			LiveWindow.addActuator("Climber", "Climber Talon", climberTalon);
		
		
		//DriveSystem components
		driveTalonRightFront = new CANTalon(0);
			LiveWindow.addActuator("Drive System", "Right Front Wheel", driveTalonRightFront);
		driveTalonRightBack = new CANTalon(1);
			LiveWindow.addActuator("Drive System", "Right Back Wheel", driveTalonRightBack);
		driveTalonLeftFront = new CANTalon(7);
			LiveWindow.addActuator("Drive System", "Left Front Wheel", driveTalonLeftFront);
		driveTalonLeftBack = new CANTalon(6);
			LiveWindow.addActuator("Drive System", "Left Back Wheel", driveTalonLeftBack);
			
		steeringGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			LiveWindow.addSensor("Drive System", "Steering Gyro", steeringGyro);
			
		driveLeftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
			LiveWindow.addSensor("Drive System", "Right Steering Encoder", driveLeftEncoder);
			driveLeftEncoder.setDistancePerPulse(distancePerCount);
			
		driveRightEncoder = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
			LiveWindow.addSensor("Drive System", "Right Steering Encoder", driveRightEncoder);
			driveRightEncoder.setDistancePerPulse(distancePerCount);
			
			
		//Feeder components
		feederTalon = new CANTalon(5);
			LiveWindow.addActuator("Feeder", "Feeder Talon", feederTalon);
			
		//GearSwitch components
		gearSwitch = new DigitalInput(4);
			
		//Intake components
		intakeTalon = new CANTalon(2);
			LiveWindow.addActuator("Intake", "Intake Talon", intakeTalon);
			
		//LEDSystem components
		usbSerial = new SerialPort(9600, SerialPort.Port.kUSB);
				
		//PowerDistributionSystem Componenents
		powerDistributionPanel = new PowerDistributionPanel();
		LiveWindow.addSensor("Power Distribution System", "Power Distribution Panel", powerDistributionPanel);
		
		//Shooter components
		shooterTalon = new CANTalon(4);
			LiveWindow.addActuator("Shooter", "Flywheel", shooterTalon);
			shooterTalon.changeControlMode(CANTalon.TalonControlMode.Speed); //We definitely need an encoder for this
			//TODO - Do we want to control the flywheel through closed loop or in the program?
			
		//UltrasonicSensor components;
		ultrasonicSensor = new Ultrasonic(5,6);
			
	}
	
	public static NetworkTable initPreferences() { //Create a NetworkTable for the values that can be changed on the fly and set their default values
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
	
	public static void updatePreferences(NetworkTable preferencesTable) { //See if any of the preference values have changed
		
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
		
		distancePerCount = (Math.PI * wheelDiameter)/(gearRatio * countsPerRevolution);

		
		//PowerDistributionPanel variables
		
		//Shooter variables
		shooterSpeed = preferencesTable.getNumber("Flywheel Speed", shooterSpeed);
		
	}
	
	private static void putValueIfEmpty(NetworkTable table, String key, Object value) {
		table.putValue(key, table.getValue(key, value)); //Get the preference's value in the table, and if it is null set it to the value of the default variable (at the top of RobotMap)
	}
}
