package commands.auto;

import robotMain.Robot;

public class AutoEncoderRotateCommand extends AutoCommand {
	private int rotationEN;
	private int startValue;
	private int lEncoderValue;
	private int stopValue;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoEncoderRotateCommand(double degreeRotation) {
        requires(Robot.driveSystem);
        
        rotationEN = controller.degreesToEncoder(degreeRotation);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	startValue = Robot.driveSystem.getLeftEncoderRaw();
        lEncoderValue = startValue;
        stopValue = lEncoderValue+rotationEN;
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	// Counter-Clockwise
    	if (stopValue-startValue > 0)
    		speed = controller.getAutoSpeed();
    	// Clockwise
    	else
    		speed = -controller.getAutoSpeed();
    	
    	//controller.tank(speed, -speed);
    	controller.tank(speed, -speed);
    	lEncoderValue = Robot.driveSystem.getLeftEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (stopValue>startValue) {
    		if (lEncoderValue>stopValue) {
    			return true;
    		}
    	} else {
    		if (lEncoderValue<stopValue) {
    			return true;
    		}
    	}
    	return false;
    }
}
