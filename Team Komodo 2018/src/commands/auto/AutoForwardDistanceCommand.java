package commands.auto;

import robotMain.Robot;

public class AutoForwardDistanceCommand extends AutoCommand {
	private double distanceFT;
	private int startValue;
	private int encoderValue;
	private int stopValue;
	private double timerAdjustment = 7.0;
	
    public AutoForwardDistanceCommand(double dFT) {
        requires(Robot.driveSystem);
        distanceFT = dFT;
        
        setTimeout(dFT/timerAdjustment);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	startValue = Robot.driveSystem.getRightEncoderRaw();
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
    	encoderValue = Robot.driveSystem.getRightEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	System.out.println(startValue+" "+encoderValue+" "+stopValue);
    	if (stopValue>startValue) {
    		if (encoderValue>stopValue) {
    			return true;
    		}
    	} else {
    		if (encoderValue<stopValue) {
    			return true;
    		}
    	}
    	return isTimedOut();
    }
}
