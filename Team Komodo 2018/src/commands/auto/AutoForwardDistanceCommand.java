package commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotMain.Robot;

public class AutoForwardDistanceCommand extends AutoCommand {
	private double distanceFT;
	private int startValue;
	private int encoderValue;
	private int stopValue;
	double speed;
	double slower = 1;
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
        speed = controller.getAutoSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (stopValue-startValue > 0)
    		speed = Math.abs(speed);
    	else
    		speed = -1 * Math.abs(speed);
    	
    	controller.tank(speed, speed);
    	encoderValue = Robot.driveSystem.getRightEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println(startValue+" "+encoderValue+" "+stopValue);
    	SmartDashboard.putString("DB/String 7", "Encoder: " + encoderValue + "/" + stopValue);

    	if (stopValue>startValue) {
    		if (encoderValue>stopValue) {
    			return true;
    		}else if((Math.abs(stopValue)-Math.abs(encoderValue)) > controller.feetToEncoder(slower)) {
    			speed = 0.5;
    			return false;
    		}
    	} else {
    		if (encoderValue<stopValue) {
    			return true;
    		}else if((Math.abs(stopValue)-Math.abs(encoderValue)) > controller.feetToEncoder(slower)) {
    			speed = 0.5;
    			return false;
    		}
    	}
    	if(isTimedOut()) {
    		System.out.println("TIMED OUT!!!");
    	}
    	return isTimedOut();
    }
}
