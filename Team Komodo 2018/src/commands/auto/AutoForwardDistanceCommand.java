package commands.auto;

import robotMain.Robot;

public class AutoForwardDistanceCommand extends AutoCommand {
	private double distanceFT;
	private int startValue;
	private int encoderValue;
	private int stopValue;
	
    public AutoForwardDistanceCommand(double dFT) {
        requires(Robot.driveSystem);
        distanceFT = dFT;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	startValue = Robot.driveSystem.getLeftEncoderRaw();
    	encoderValue = startValue;
        stopValue = encoderValue+controller.feetToEncoder(distanceFT);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	if (stopValue-startValue > 0)
    		speed = controller.getAutoSpeed();
    	else
    		speed = -controller.getAutoSpeed();
    	
    	controller.tank(speed, speed);
    	encoderValue = Robot.driveSystem.getLeftEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
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
