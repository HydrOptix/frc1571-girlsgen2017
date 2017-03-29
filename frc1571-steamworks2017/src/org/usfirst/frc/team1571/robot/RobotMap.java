package org.usfirst.frc.team1571.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RobotMap {
	//Declare all components on the robot.
	
	//Agitator components and variables
	public static CANTalon agitatorTalon;
		public static double agitatorSpeed = 1.00;	//Speed the agitator runs (-1 to 1)
	
	//CameraSystem components and variables
	public static Relay ringLight;
	public static Servo cameraTiltServo;
		public static double cameraTiltDefaultPos = .795;	//When the camera isn't doing anything, reset it to this position
		public static double cameraTiltMinPos = .795;		//The minimum position the camera will tilt to
		public static double cameraTiltMaxPos = 1;		//The maximum position the camera will tilt to. Sometimes you don't want the camera to rotate upside down.
		
		
		public static double cameraAngleFunctionA = 0;			//Ended up going unused due to lack of time for testing
		public static double cameraAngleFunctionB = -70.544;	//Ended up going unused due to lack of time for testing
		public static double cameraAngleFunctionC = 67.067;		//Ended up going unused due to lack of time for testing
		
		public static double cameraPixelHeight = 480;			//Height of the camera input
		public static double cameraPixelWidth = 640;			//Width of the camera input
		
		public static int cameraAllowablePixelError = 10;		//Number of pixels the vision target is allowed to be off by
		public static int cameraSlowZonePixels = 200;			//Number of pixels in the slow-moving more accurate zone
		public static double cameraTiltFastIncrementRate = .001;//Amount by which the servo increments when moving quickly (servo ranges from 0 to 1)
		public static double cameraTiltSlowIncrementRate = .001;//Amount by which the servo increments when moving slowly
		public static double cameraTiltScanIncrementRate = .01;	//Amount by which the servo increments when scanning for the target (not sure if scanning actually works right now)
		
		public static int visionTargetXCenter = 350; //If the shooter is slightly off-center, set the center for the vision target to this pixel
		
	
	//Climber components and variables
	public static CANTalon climberTalon;
		public static double climbSpeed = -1.00;				//Speed the climber runs (-1 to 1)
	
	//DriveSystem components and variables
	public static CANTalon driveTalonRightMaster;
	public static CANTalon driveTalonRightSlave;
	
	public static CANTalon driveTalonLeftMaster;
	public static CANTalon driveTalonLeftSlave;
	
	public static ADXRS450_Gyro steeringGyro;
	
	public static Encoder driveLeftEncoder;
	public static Encoder driveRightEncoder;
		public static double wheelDiameter = .5; //Wheel diameter in feet. Used to calculate distance the robot is traveling
		public static double gearRatio = 10/1; //Gear ratio from encoder to wheel. Used to calculate distance the robot is traveling
		public static int countsPerRevolution = 80; //The number of counts the encoder outputs per revolution (don't forget to multiply by the encoding factor)
		public static double distancePerCount = (gearRatio * countsPerRevolution)/(Math.PI * wheelDiameter);
	
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
	public static double turnSpeed = 1.0;
	
	/* The max speed the robot will drive forward on a scale of 0 to 1.
	 * 0 means the robot doesn't drive forward. Don't set this to 0.
	 * 1 means the robot operates at 100% speed while driving.*/
	public static double driveSpeed = -1.00;
	
	public static double driveAimbotFastSpeed = .15;	//Speed the robot turns when moving quickly to center the vision target
	public static double driveAimbotSlowSpeed = .05;	//Speed the robot turns when moving more accurately to center the vision target
	
	//Feeder components and variables
	public static CANTalon feederTalon;
		public static double feederSpeed = 0.50;		//Speed the robot runs the feeder (-1 to 1)
		
	//GearSwitch components and variables
	public static DigitalInput gearOpenSwitch;
	public static DigitalInput gearClosedSwitch;
		public static boolean reverseGearOpenSwitch = false;
		public static boolean reverseGearClosedSwitch = false;
		
	public static CANTalon gearArm;
	
	//Intake components and variables
	public static CANTalon intakeTalon;
		public static double intakeSpeed = -0.75;		//Speed the robot runs the ball intake (-1 to 1)
	
	//LEDSystem components and variables
	public static SerialPort arduino;
	public static double serialDelay = .5;				//Delay before sending another serial command (ended up mostly unused because the robot kept freezing. Serial overflow?)
		
	//PowerDistributionSystem components and variables
	public static PowerDistributionPanel powerDistributionPanel;
	
	//Shooter components and variables
	public static CANTalon shooterTalon;
		public static double shooterSpeed = 1.00;		//Speed multiplier for the shooter. You probably don't want to change this.
		public static double shooterSpeedFunctionA = .048;	//These are the coefficients for a parabolic velocity function (y=Ax^2+Bx+C) used to calculate the speed needed to shoot the ball far enough.
		public static double shooterSpeedFunctionB = 10.262;	//Example functions that we used in 2017 can be found at https://docs.google.com/spreadsheets/d/1XohjcKDPCyi3CSsj_TRhFFEKfISIe4YYJaIspVYB7Hk/
		public static double shooterSpeedFunctionC = 215.143;//If you look at the results chart, Google Sheets has calculated a parabolic function for each line. Us the numbers before each x for these values.
		
		public static double shooterP = 10;			//P value for the shooter PID control
		public static double shooterI = 0;			//I value for the shooter PID control
		public static double shooterD = 0;			//D value for the shooter PID control
		
		public static double shooterSpeedError = 0;		//Error the shooter is allowed when setting the speed
				
	public static void init() {
		//Instantiate all components to pass to Robot.java and add them to LiveWindow
		
		//Agitator components
		//agitatorTalon = new CANTalon(4);
		//	LiveWindow.addActuator("Agitator", "Agitator Talon", agitatorTalon);
		
		//CameraSystem components
		cameraTiltServo = new Servo(0);
			LiveWindow.addActuator("Camera System", "Camera Tilt Servo", cameraTiltServo);
		ringLight = new Relay(0);
			LiveWindow.addActuator("Camera System", "Camera Ring Light", ringLight);
		
		//Climber components
		climberTalon = new CANTalon(5);
			LiveWindow.addActuator("Climber", "Climber Talon", climberTalon);
		
		
		//DriveSystem components
		driveTalonRightMaster = new CANTalon(2);
			LiveWindow.addActuator("Drive System", "Right Front Talon", driveTalonRightMaster);
		driveTalonRightSlave = new CANTalon(3);
			LiveWindow.addActuator("Drive System", "Right Back Talon", driveTalonRightSlave);
		driveTalonLeftMaster = new CANTalon(0);
			LiveWindow.addActuator("Drive System", "Left Front Talon", driveTalonLeftMaster);
		driveTalonLeftSlave = new CANTalon(1);
			LiveWindow.addActuator("Drive System", "Left Back Talon", driveTalonLeftSlave);
			
		steeringGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			LiveWindow.addSensor("Drive System", "Steering Gyro", steeringGyro);
			
		driveLeftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
			LiveWindow.addSensor("Drive System", "Right Steering Encoder", driveLeftEncoder);
			
		driveRightEncoder = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
			LiveWindow.addSensor("Drive System", "Right Steering Encoder", driveRightEncoder);
			
			
		//Feeder components
		feederTalon = new CANTalon(6);
			LiveWindow.addActuator("Feeder", "Feeder Talon", feederTalon);
			feederTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			
		//GearSwitch components
		gearOpenSwitch = new DigitalInput(4);
		gearClosedSwitch = new DigitalInput(5);
		
		gearArm = new CANTalon(4);
			
		//Intake components
		intakeTalon = new CANTalon(7);
			LiveWindow.addActuator("Intake", "Intake Talon", intakeTalon);
			
		//LEDSystem components
		initSerial();
			
		//PowerDistributionSystem Componenents
		powerDistributionPanel = new PowerDistributionPanel();
			LiveWindow.addSensor("Power Distribution System", "Power Distribution Panel", powerDistributionPanel);
		
		//Shooter components
		shooterTalon = new CANTalon(8);
			LiveWindow.addActuator("Shooter", "Flywheel", shooterTalon);
			shooterTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
			shooterTalon.setVoltageRampRate(1);
			shooterTalon.reverseOutput(false);
			shooterTalon.setAllowableClosedLoopErr(0);
			shooterTalon.enableBrakeMode(false);
		
			
	}
	
	public static NetworkTable initPreferences() { //Create a NetworkTable for the values that can be changed on the fly and set their default values
		NetworkTable receivedPreferences = NetworkTable.getTable("preferencesTable");
		
		//Agitator variables
		putValueIfEmpty(receivedPreferences, "Agitator Speed", agitatorSpeed);

		//CameraSystem variables
		putValueIfEmpty(receivedPreferences, "Camera Default Tilt", cameraTiltDefaultPos);
		putValueIfEmpty(receivedPreferences, "Camera Minimum Tilt", cameraTiltMinPos);
		putValueIfEmpty(receivedPreferences, "Camera Maximum Tilt", cameraTiltMaxPos);
		
		putValueIfEmpty(receivedPreferences, "Camera Angle Function A", cameraAngleFunctionA);
		putValueIfEmpty(receivedPreferences, "Camera Angle Function B", cameraAngleFunctionB);
		putValueIfEmpty(receivedPreferences, "Camera Angle Function C", cameraAngleFunctionC);
		
		putValueIfEmpty(receivedPreferences, "Camera Pixel Height", cameraPixelHeight);
		
		putValueIfEmpty(receivedPreferences, "Camera Allowable Pixel Error", cameraAllowablePixelError);
		putValueIfEmpty(receivedPreferences, "Camera Slow Zone Pixels", cameraSlowZonePixels);
		putValueIfEmpty(receivedPreferences, "Camera Fast Tilt Rate", cameraTiltFastIncrementRate);
		putValueIfEmpty(receivedPreferences, "Camera Slow Tilt Rate", cameraTiltFastIncrementRate);
		putValueIfEmpty(receivedPreferences, "Camera Scanning Tilt Rate", cameraTiltScanIncrementRate);
		
		putValueIfEmpty(receivedPreferences, "Camera Centered X Pixel", visionTargetXCenter);
		
		//Climber variables
		putValueIfEmpty(receivedPreferences, "Climb Speed", climbSpeed);
				
		//DriveSystem variables
		putValueIfEmpty(receivedPreferences, "Wheel Diameter", wheelDiameter);
		putValueIfEmpty(receivedPreferences, "Gear Ratio", gearRatio);
		putValueIfEmpty(receivedPreferences, "Drive Counts Per Revolution", countsPerRevolution);
		distancePerCount = 360/countsPerRevolution*wheelDiameter;
		
		putValueIfEmpty(receivedPreferences, "Steering Rate", maxSteering);
		putValueIfEmpty(receivedPreferences, "Turning Speed", turnSpeed);
		putValueIfEmpty(receivedPreferences, "Aimbot Fast Turning Speed", driveAimbotFastSpeed);
		putValueIfEmpty(receivedPreferences, "Aimbot Slow Turning Speed", driveAimbotSlowSpeed);
		
		//Feeder variables
		putValueIfEmpty(receivedPreferences, "Feeder Speed", feederSpeed);		
		
		//GearSystem variables

		//Intake variables
		putValueIfEmpty(receivedPreferences, "Intake Speed", intakeSpeed);
		
		//LEDSystem
		putValueIfEmpty(receivedPreferences, "LED Update Delay", serialDelay);
		
		//PowerDistributionSystem variables
		
		//Shooter variables
		putValueIfEmpty(receivedPreferences, "Manual Shooter Speed", shooterSpeed);
		putValueIfEmpty(receivedPreferences, "Shooter Speed Function A", shooterSpeedFunctionA);
		putValueIfEmpty(receivedPreferences, "Shooter Speed Function B", shooterSpeedFunctionB);
		putValueIfEmpty(receivedPreferences, "Shooter Speed Function C", shooterSpeedFunctionC);
		
		return receivedPreferences;
	}
	
	public static void updatePreferences(NetworkTable preferencesTable) { //See if any of the preference values have changed
		
		//Agitator variables
			agitatorSpeed = preferencesTable.getNumber("Agitator Speed", agitatorSpeed);

		//CameraSystem variables
			cameraTiltDefaultPos = preferencesTable.getNumber("Camera Default Tilt", cameraTiltDefaultPos);
			cameraTiltMinPos = preferencesTable.getNumber("Camera Minimum Tilt", cameraTiltMinPos);
			cameraTiltMaxPos = preferencesTable.getNumber("Camera Maximum Tilt", cameraTiltMaxPos);
				
			cameraAngleFunctionA = preferencesTable.getNumber("Camera Angle Function A", cameraAngleFunctionA);
			cameraAngleFunctionB = preferencesTable.getNumber("Camera Angle Function B", cameraAngleFunctionB);
			cameraAngleFunctionC = preferencesTable.getNumber("Camera Angle Function C", cameraAngleFunctionC);
				
			cameraPixelHeight = preferencesTable.getNumber("Camera Pixel Height", cameraPixelHeight);
				
			cameraAllowablePixelError = (int)preferencesTable.getNumber("Camera Allowable Pixel Error", cameraAllowablePixelError);
			cameraSlowZonePixels = (int)preferencesTable.getNumber("Camera Slow Zone Pixels", cameraSlowZonePixels);
			cameraTiltFastIncrementRate = preferencesTable.getNumber("Camera Fast Tilt Rate", cameraTiltFastIncrementRate);
			cameraTiltFastIncrementRate = preferencesTable.getNumber("Camera Slow Tilt Rate", cameraTiltFastIncrementRate);
			cameraTiltScanIncrementRate = preferencesTable.getNumber("Camera Scanning Tilt Rate", cameraTiltScanIncrementRate);
				
			visionTargetXCenter = (int)preferencesTable.getNumber("Camera Centered X Pixel", visionTargetXCenter);
				
		//Climber variables
			climbSpeed = preferencesTable.getNumber("Climb Speed", climbSpeed);
						
		//DriveSystem variables
			wheelDiameter = preferencesTable.getNumber("Wheel Diameter", wheelDiameter);
			gearRatio = preferencesTable.getNumber("Gear Ratio", gearRatio);
			countsPerRevolution = (int)preferencesTable.getNumber("Drive Counts Per Revolution", countsPerRevolution);
			distancePerCount = 360/countsPerRevolution*wheelDiameter;
				
			maxSteering = preferencesTable.getNumber("Steering Rate", maxSteering);
			turnSpeed = preferencesTable.getNumber("Turning Speed", turnSpeed);
			driveAimbotFastSpeed = preferencesTable.getNumber("Aimbot Fast Turning Speed", driveAimbotFastSpeed);
			driveAimbotSlowSpeed = preferencesTable.getNumber("Aimbot Slow Turning Speed", driveAimbotSlowSpeed);
				
		//Feeder variables
			feederSpeed = preferencesTable.getNumber("Feeder Speed", feederSpeed);		
				
		//GearSwitch variables

		//Intake variables
			intakeSpeed = preferencesTable.getNumber("Intake Speed", intakeSpeed);
				
		//LEDSystem
			serialDelay = preferencesTable.getNumber("LED Update Delay", serialDelay);
				
		//PowerDistributionSystem variables
				
		//Shooter variables
			shooterSpeed = preferencesTable.getNumber("Manual Shooter Speed", shooterSpeed);
			shooterSpeedFunctionA = preferencesTable.getNumber("Shooter Speed Function A", shooterSpeedFunctionA);
			shooterSpeedFunctionB = preferencesTable.getNumber("Shooter Speed Function B", shooterSpeedFunctionB);
			shooterSpeedFunctionC = preferencesTable.getNumber("Shooter Speed Function C", shooterSpeedFunctionC);
		
	}
	
	private static void putValueIfEmpty(NetworkTable table, String key, Object value) {
		table.putValue(key, table.getValue(key, value)); //Get the preference's value in the table, and if it is null set it to the value of the default variable (at the top of RobotMap)
	}
	
	private static void initSerial() {
		try {
			arduino = new SerialPort(9600, SerialPort.Port.kUSB);
		} catch(RuntimeException e) {
			System.err.println("Caught SerialPort RuntimeException: " + e.getMessage());
		}
	}
}
