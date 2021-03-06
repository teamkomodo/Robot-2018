package commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotMain.Robot;
import subsystems.AutoController;

public class AutoGyroRotateCommand extends AutoCommand {
	AutoController controller;
	private double degreeRotation;
	private double degreeSlower = 10;
	
	private boolean useLeft = true;
	private boolean useRight =true;
	private double speed = 0.9;
	
	// Positive degreeRotation <=> counter-clockwise
	// Negative degreeRotation <=> clockwise
	// Left motor going forward will send left encoder going negative
    public AutoGyroRotateCommand(double degRot) {
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        
        degreeRotation = degRot;
    }
    
    public AutoGyroRotateCommand(double degRot, boolean useL, boolean useR) {
    	useLeft = useL;
    	useRight = useR;
    	
        requires(Robot.driveSystem);
        controller = Robot.driveSystem.getAutoController();
        
        setTimeout(4.0);
        degreeRotation = degRot;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	controller.resetGyro();
        controller.resetGyro();
        System.out.println("Start Auto turn " + degreeRotation + " degrees");
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	// Counter-Clockwise
    	if (degreeRotation > 0)
    		speed = -1 * Math.abs(speed); //controller.getAutoSpeed();
    	// Clockwise
    	else
    		speed = Math.abs(speed); //controller.getAutoSpeed();
    	
    	//controller.tank(speed, -speed);
    	double lSpeed = useLeft ? speed : 0;
    	double rSpeed = useRight ? -speed : 0;
    	controller.tank(lSpeed, rSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println("Gyro Log: "+ controller.getAngle() + " " + degreeRotation);

    	//if (Math.abs(controller.getAngle()) > Math.abs(degreeRotation))
    	double currAngle = controller.getAngle();
    	if (Math.abs(controller.getAngle()) > Math.abs(degreeRotation)
    		&& currAngle/Math.abs(currAngle)==degreeRotation/Math.abs(degreeRotation)) {
    		controller.tank(0, 0);
        	System.out.println("Gyro completed turn of " + degreeRotation + " degrees, actual turn was " + controller.getAngle() + " degrees");
    		return true;    		
    	}else if ((Math.abs(degreeRotation) - Math.abs(controller.getAngle()) < degreeSlower)
        		&& currAngle/Math.abs(currAngle)==degreeRotation/Math.abs(degreeRotation)) {
        	speed = 0.7;
    	}
    	if(isTimedOut()) {
    		System.out.println("AUTO TURN TIMED OUT!!!");
        	System.out.println("Auto completed turn of " + degreeRotation + " degrees, actual turn was " + controller.getAngle() + " degrees");
    	}
    	return isTimedOut();
    }
}
