package subsystems;


public class AutoController {
	private final double DEFAULT_SPEED = 0.1;
    private final double ROBOT_DIAMETER_IN = 24.8;
    private final double FEET_TO_ENCODER_K = -5100;
    //private final double FEET_TO_ENCODER_LK = -5100; // when going forwards
    //private final double FEET_TO_ENCODER_RK = 4200; // when going backwards

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
    	driveSystem.getDrive().tankDrive(-leftSpeed, -rightSpeed);
    }
    
    // Positive degreeRotation <=> counter-clockwise
 	// Negative degreeRotation <=> clockwise
    public int degreesToEncoder(double degreeRotation) {
    	return inchesToEncoder((degreeRotation/360.0)*ROBOT_DIAMETER_IN);
    }
    public int inchesToEncoder(double feetValue) {
    	return (int)(feetValue*FEET_TO_ENCODER_K);
    }
    
    /*
    public int inchesToLeftEncoder(double feetValue) {
    	return (int)(feetValue*FEET_TO_ENCODER_LK);
    }
    
    public int inchesToRightEncoder(double feetValue) {
    	return (int)(feetValue*FEET_TO_ENCODER_RK);
    }
    */
    
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
