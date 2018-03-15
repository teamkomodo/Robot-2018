package subsystems;

import robotMain.RobotMap;

public class AutoController {
	private final double DEFAULT_SPEED = 0.5;
    private final double ROBOT_DIAMETER_FT = 24.8/12;
    private final double FEET_TO_ENCODER_K = 5100;
    //private final double FEET_TO_ENCODER_LK = -5100; // when going forwards
    //private final double FEET_TO_ENCODER_RK = 4200; // when going backwards
    private final double FEET_TO_ENCODER_K_P = FEET_TO_ENCODER_K;

    private double autoSpeed = DEFAULT_SPEED;
    
    private DriveSystem driveSystem;
    
    public AutoController(DriveSystem drive) {
    	driveSystem = drive;
    }
    
    // Called every DriveSystem's periodic
    public void update() {
    }
    
    public void tank(double leftSpeed, double rightSpeed) {
    	// The motors are reversed, so negative values will send the robot forward
    	driveSystem.getDrive().tankDrive(leftSpeed, rightSpeed);
    }
    
    // Positive degreeRotation <=> counter-clockwise
 	// Negative degreeRotation <=> clockwise
    public int degreesToEncoder(double degreeRotation) {
    	return feetToEncoder((degreeRotation/360.0)*ROBOT_DIAMETER_FT*Math.PI);
    }
    public int feetToEncoder(double feetValue) {
    	double feetToEncoder;
    	if(RobotMap.isCompetitionBot) {
    		feetToEncoder = FEET_TO_ENCODER_K;
    	}else {
    		feetToEncoder = FEET_TO_ENCODER_K_P;
    	}
    	return (int)(feetValue*feetToEncoder);
    }
    
    public double getAutoSpeed() {
    	return autoSpeed;
    }
    
    public void setAutoSpeed(double newSpeed) {
    	autoSpeed = newSpeed;
    }
    
    public void resetAutoSpeed() {
    	autoSpeed = DEFAULT_SPEED;
    }
}
