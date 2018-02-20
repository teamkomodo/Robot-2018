package commands.auto;

import robotMain.Robot;

public class AutoRotateCommand extends AutoCommand {
	private int lEncoderValue;
	private int stopValue;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoRotateCommand(double degreeRotation) {
        requires(Robot.driveSystem);
        
        lEncoderValue = Robot.driveSystem.getLeftEncoderRaw();
        double rotationIN = controller.degreesToEncoder(degreeRotation);
        stopValue = lEncoderValue+controller.inchesToEncoder(rotationIN);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("Hiya");
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
    	lEncoderValue = Robot.driveSystem.getLeftEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(stopValue)-Math.abs(lEncoderValue) <= 25)
    		return true;
    	return false;
    }
}
