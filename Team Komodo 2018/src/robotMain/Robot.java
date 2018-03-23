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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
import commands.auto.AutonomousCommand;
import commands.auto.groups.AutoLineCommandGroup;
import commands.auto.groups.AutoMiddleStartCommandGroup;
import commands.auto.groups.AutoSameSideCommandGroup;
import commands.auto.groups.AutoTestCommandGroup;
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
    private SendableChooser<POSITION> positionChooser = new SendableChooser<>();
    private SendableChooser<Boolean> scaleChooser = new SendableChooser<>();

    public static OI oi;
    public static DriveSystem driveSystem;
    public static ManipulatorSystem manipulatorSystem;
    public static LifterSystem lifterSystem;

	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 480;
	
	public enum POSITION {LEFT, CENTER, RIGHT};
	
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
        
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        camera.setFPS(30);
        
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
        
        //autonomousCommand = new AutoTestCommandGroup();
        autonomousCommand = new AutoSameSideCommandGroup("left", "scale");
        
        teleopDriveCommand = new TeleopDriveCommand();
        teleopLiftCommand = new TeleopLiftCommand();
        teleopManipulatorCommand = new TeleopManipulatorCommand();

//        chooser.addDefault("Auto Line", new AutoLineCommandGroup());
//        chooser.addObject("Same Side Left Scale", new AutoSameSideCommandGroup("left",
//																				"scale"));
//		chooser.addObject("Same Side Left Switch", new AutoSameSideCommandGroup("left",
//																				 "switch"));
//		chooser.addObject("Same Side Right Scale", new AutoSameSideCommandGroup("right",
//																				"scale"));
//		chooser.addObject("Same Side Right Switch", new AutoSameSideCommandGroup("right",
//				 																 "switch"));
//        SmartDashboard.putData("Auto mode", chooser);
        
        positionChooser.addDefault("Left", POSITION.LEFT);
        positionChooser.addObject("Center", POSITION.CENTER);
        positionChooser.addObject("Right", POSITION.RIGHT);
        SmartDashboard.putData("Position", positionChooser);
        
        scaleChooser.addDefault("Switch", Boolean.TRUE);
        scaleChooser.addObject("Scale", Boolean.FALSE);
        SmartDashboard.putData("Scale Override", scaleChooser);

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

        //autonomousCommand = (Command) chooser.getSelected();
        autonomousCommand = chooseCommand(positionChooser.getSelected(), 
        		scaleChooser.getSelected(), 
        		getSide(gameData),
        		getSide(gameData.substring(1)));
        if (autonomousCommand != null) autonomousCommand.start();
    }

    private POSITION getSide(String gameData) {
    	POSITION position = POSITION.RIGHT;
		if (gameData.startsWith("L")) {
    		position = POSITION.LEFT;
    	}
		return position;
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
    
    public Command chooseCommand(POSITION startPosition, Boolean scaleOverride, 
    		POSITION switchSide, POSITION scaleSide) {
    	Command autoCommand = null;
    	if (scaleOverride && startPosition.equals(scaleSide)) {//go to same side scale
    		if (startPosition.equals(POSITION.LEFT)) {
    			autoCommand = new AutoSameSideCommandGroup("left","scale");
    		} else {
    			autoCommand = new AutoSameSideCommandGroup("right","scale");
    		}
    	} else {
    		if(startPosition.equals(switchSide)) {//deliver to same side of switch
    			if(startPosition.equals(POSITION.LEFT)) {
    				autoCommand = new AutoSameSideCommandGroup("left","switch");
    			}else {
    				autoCommand = new AutoSameSideCommandGroup("right","switch");
    			}
    		}else {
				autoCommand = new AutoLineCommandGroup();
    		}
    	}
    	return autoCommand;
    }
    }
