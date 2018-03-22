package commands.auto;

import robotMain.Robot;
import subsystems.AutoController;

public class AutoGyroForwardCommand extends AutoCommand {
	AutoController controller;
	double Kp = 0.035;
	private double distanceFT;
	private int startValue;
	private int encoderValue;
	private int stopValue;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoGyroForwardCommand(double distance) {
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        distanceFT = distance;
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	controller.resetGyro();
    	Robot.driveSystem.resetLeftEncoder();
     	startValue = Robot.driveSystem.getLeftEncoderRaw();
    	encoderValue = startValue;
        stopValue = encoderValue+controller.feetToEncoder(distanceFT);
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed = 0.75;
    	double angle = controller.getAngle(); // get current heading
        controller.arcade(speed, -angle*Kp); // drive towards heading 0
    	encoderValue = Robot.driveSystem.getLeftEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	System.out.println(startValue + " " + encoderValue + " " + stopValue);
    	if (stopValue>startValue) {
    		if (encoderValue>stopValue) {
    			return true;
    		}
    	} else {
    		if (encoderValue<stopValue) {
    			return true;
    		}
    	}
    	return false;
    }
}