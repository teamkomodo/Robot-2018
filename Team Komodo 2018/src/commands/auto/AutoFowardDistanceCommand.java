package commands.auto;

import robot.Robot;

public class AutoFowardDistanceCommand extends AutoCommand {
	private int encoderValue;
	private int stopValue;
	
    public AutoFowardDistanceCommand(double distanceIN) {
        requires(Robot.driveSystem);
        
        encoderValue = 0;
        stopValue = controller.inchesToEncoder(distanceIN);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	if (stopValue-encoderValue > 0)
    		speed = -controller.getAutoSpeed();
    	else
    		speed = controller.getAutoSpeed();
    	
    	controller.tank(speed, speed);
    	encoderValue += 1; // Will use encoder diff in future
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(encoderValue) >= Math.abs(stopValue))
    		return true;
    	return false;
    }
}
