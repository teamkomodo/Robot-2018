package commands.auto;

import robot.Robot;

public class AutoRotateCommand extends AutoCommand {
	private int lEncoderValue;
	private int stopValue;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
    public AutoRotateCommand(double degreeRotation) {
        requires(Robot.driveSystem);
        
        lEncoderValue = 0;
        double rotationIN = controller.degreesToEncoder(degreeRotation);
        stopValue = controller.inchesToEncoder(rotationIN);
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	// Counter-Clockwise
    	if (stopValue-lEncoderValue > 0)
    		speed = -controller.getAutoSpeed();
    	// Clockwise
    	else
    		speed = controller.getAutoSpeed();
    	
    	controller.tank(speed, -speed);
    	lEncoderValue += 1; // Will use left encoder diff in future
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(lEncoderValue) >= Math.abs(stopValue))
    		return true;
    	return false;
    }
}
