package commands.auto;

import robotMain.Robot;
import subsystems.AutoController;

public class AutoGyroRotateCommand extends AutoCommand {
	AutoController controller;
	private double degreeRotation;
	
	private boolean useLeft = true;
	private boolean useRight =true;
	
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
        
        degreeRotation = degRot;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	controller.resetGyro();
        controller.resetGyro();
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	// Counter-Clockwise
    	if (degreeRotation > 0)
    		speed = -0.75; //controller.getAutoSpeed();
    	// Clockwise
    	else
    		speed = 0.75;//-controller.getAutoSpeed();
    	
    	//controller.tank(speed, -speed);
    	double lSpeed = useLeft ? speed : 0;
    	double rSpeed = useRight ? -speed : 0;
    	controller.tank(lSpeed, rSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	System.out.println("Gyro Log: "+ controller.getAngle() + " " + degreeRotation);
    	//if (Math.abs(controller.getAngle()) > Math.abs(degreeRotation))
    	double currAngle = controller.getAngle();
    	if (Math.abs(controller.getAngle()) > Math.abs(degreeRotation)
    		&& currAngle/Math.abs(currAngle)==degreeRotation/Math.abs(degreeRotation)) {
    		controller.tank(0, 0);
    		return true;    		
    	}
    	return false;
    }
}
