package commands.auto;

import robotMain.Robot;
import subsystems.AutoController;

public class AutoGyroRotateCommand extends AutoCommand {
	AutoController controller;
	private double degreeRotation;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoGyroRotateCommand(double degRot) {
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        
        degreeRotation = degRot;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	controller.resetGyro();
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	// Counter-Clockwise
    	if (degreeRotation > 0)
    		speed = controller.getAutoSpeed();
    	// Clockwise
    	else
    		speed = -controller.getAutoSpeed();
    	
    	//controller.tank(speed, -speed);
    	controller.tank(speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(controller.getAngle()) > Math.abs(degreeRotation))
    		return true;
    	return false;
    }
}
