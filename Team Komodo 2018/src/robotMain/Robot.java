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

//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import vision.TestPipeline;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import subsystems.*;
import commands.auto.AutoTestCommandGroup;
import commands.auto.AutonomousCommand;
import commands.teleop.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    private Command autonomousCommand;
    private Command teleopDriveCommand;
    private Command teleopLiftCommand;
    private Command teleopManipulatorCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static DriveSystem driveSystem;
    public static ManipulatorSystem manipulatorSystem;
    public static LifterSystem lifterSystem;

	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 480;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	
	private final Object imgLock = new Object();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        driveSystem = new DriveSystem();
        manipulatorSystem = new ManipulatorSystem();
        lifterSystem = new LifterSystem();
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
//        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
//        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
//        
        /*
        visionThread = new VisionThread(camera, new TestPipeline(), pipeline -> {
            if (!pipeline.filterContoursOutput().isEmpty()) {
                Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                synchronized (imgLock) {
                    centerX = r.x + (r.width / 2);
                }
            }
        });
        visionThread.start();
        */
        
        autonomousCommand = new AutoTestCommandGroup();
        
        teleopDriveCommand = new TeleopDriveCommand();
        teleopLiftCommand = new TeleopLiftCommand();
        teleopManipulatorCommand = new TeleopManipulatorCommand();

        chooser.addDefault("Autonomous Command", autonomousCommand);
        chooser.addObject("Drive Command", teleopDriveCommand);
        chooser.addObject("Lift Command", teleopLiftCommand);
        chooser.addObject("Manipulator Command", teleopManipulatorCommand);
        SmartDashboard.putData("Auto mode", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
    	//WPI lib provided code to get the field data.  
    	//Three character string for switch, scale, switch.
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
    	//if (teleopDriveCommand != null) teleopDriveCommand.cancel();
        if (teleopLiftCommand != null) teleopLiftCommand.cancel();
        if (teleopManipulatorCommand != null) teleopManipulatorCommand.cancel();
        
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        //if (teleopDriveCommand != null) teleopDriveCommand.start();
        if (teleopLiftCommand != null) teleopLiftCommand.start();
        if (teleopManipulatorCommand != null) teleopManipulatorCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
