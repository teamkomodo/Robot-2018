// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package robotMain;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Competition Robot                Practice Robot
	// ==========================       ==========================
	// 4 Spark motor controllers        4 Spark motor controllers
	// 4 Victor SP                      3 Victor SP
	// 2 Talon SRX                      3 Victor Pro SPX
	
	// Drive train uses identical controllers.
	// Competition manipulator uses 2 Victor SP motor controllers.
	// Practice manipulator uses 2 Victor Pro SPX motor controllers.
	// Competition manipulator rotator uses 1 Talon SRX motor controller.
	// Practice manipulator rotator uses 1 Victor Pro SPX motor controller.
	// 1 Victor SP is unused on both robots.

	// Boolean to indicate which robot we are using.
	public static boolean isCompetitionBot = false;
	public static boolean isReverseDrive = false;
	
	public static int leftJoystickPort = 1;
	public static int rightJoystickPort = 0;
	public static int switchDriveDirrection = 3;
	
	public static int gamepadPort = 2;
	public static int gamepadLX = 0;
	public static int gamepadLY = 1;
	public static int gamepadLT = 2;
	public static int gamepadRT = 3;
	public static int gamepadRX = 4;
	public static int gamepadRY = 5;
	public static int gamepadLB = 5; // buttons
	public static int gamepadRB = 6;
	
	public static int leftSpark1Port = 0;
	public static int leftEncoderChannelA = 0;
	public static int leftEncoderChannelB = 1;
	
	public static int rightSpark1Port = 1;
	public static int rightEncoderChannelA = 2;
	public static int rightEncoderChannelB = 3;
	
	public static int lifterControllerPort = 2;
	public static int manipulatorControllerPort = 3;
	public static int rotatorControllerPort = 4;
	//public static int wenchServoPort = -1; // Not installed right now

	public static int gyroPort = 1;
}
