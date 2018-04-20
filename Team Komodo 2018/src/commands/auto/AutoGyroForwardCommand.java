package commands.auto;

import robotMain.Robot;
import subsystems.AutoController;

public class AutoGyroForwardCommand extends AutoCommand {
	AutoController controller;
	double Kp = 0.04;
	private double distanceFT;
	private int startValue;
	private int encoderValue;
	private int stopValue;
	private double timerAdjustment = 1;
	private double slower = 0;
	
	private double dSpeed;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoGyroForwardCommand(double distance) {
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        distanceFT = distance;
        
        setTimeout(Math.abs(distance/timerAdjustment));
        
        if (distance < 0) {
        	dSpeed = -controller.getAutoSpeed();
        }else {
        	dSpeed = controller.getAutoSpeed();
        }
    }
    
    public AutoGyroForwardCommand(double distance, double speed) {
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        distanceFT = distance;
        
        setTimeout(Math.abs(distance/timerAdjustment));
        
        dSpeed = speed;
    }
    
    private int getEncoderRaw() {
		return -Robot.driveSystem.getLeftEncoderRaw();
		//return Robot.driveSystem.getLeftEncoderRaw();
	}

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	controller.resetGyro();
    	Robot.driveSystem.resetLeftEncoder();
     	startValue = getEncoderRaw();
    	encoderValue = startValue;
        stopValue = encoderValue+controller.feetToEncoder(distanceFT);
        System.out.println("Start Auto forward " + distanceFT + " feet, " + (stopValue-startValue) + " encoder counts");
    }
    
	// Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//    	double speed = 0.75;
    	double angle = controller.getAngle(); // get current heading
        controller.arcade(dSpeed, angle*Kp); // drive towards heading 0
    	encoderValue = getEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println(startValue + " " + encoderValue + " " + stopValue);
    	if (stopValue>startValue) {
    		if (encoderValue>stopValue) {
    			controller.arcade(0, 0);
    	    	System.out.println("Auto Forward expected to go from " +
    	    			startValue + " to " + stopValue + " and went to " + encoderValue);
    			return true;
    		}else if((Math.abs(stopValue)-Math.abs(encoderValue)) < controller.feetToEncoder(slower)) {
    			dSpeed = 0.5;
    		}
    	} else {
    		if (encoderValue<stopValue) {
    			controller.arcade(0, 0);
    	    	System.out.println("Auto Forward expected to go from " +
    	    			startValue + " to " + stopValue + " and went to " + encoderValue);
    			return true;
    		}else if(Math.abs((Math.abs(stopValue)-Math.abs(encoderValue))) < Math.abs(controller.feetToEncoder(slower))) {
    			dSpeed = 0.5;
    		}
    	}
    	if(isTimedOut()) {
    		System.out.println("AutoGyroForwardCommand TIMED OUT after " + Math.abs(distanceFT/timerAdjustment) + " seconds!!!");
	    	System.out.println("Auto Forward expected to go from " +
	    			startValue + " to " + stopValue + " and went to " + encoderValue);
    	}
    	return isTimedOut();
    }
}