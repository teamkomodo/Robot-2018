package subsystems;


public class AutoController {
	private final double DEFAULT_SPEED = 0.5;
    private double autoSpeed = DEFAULT_SPEED;
    private final double ROBOT_DIAMETER_IN = 24.8;
    private final double FEET_TO_ENCODER_K = 100; // TODO Find this via experimentation
    
    private DriveSystem driveSystem;
    
    public AutoController(DriveSystem drive) {
    	driveSystem = drive;
    }
    
    // Called every DriveSystem's periodic
    public void update() {
    }
    
    public void tank(double leftSpeed, double rightSpeed) {
    	driveSystem.getDrive().tankDrive(leftSpeed, rightSpeed);
    }
    
    public int degreesToEncoder(double degreeRotation) {
    	return inchesToEncoder((degreeRotation/360.0)*ROBOT_DIAMETER_IN);
    }
    
    public int inchesToEncoder(double feetValue) {
    	return (int)(feetValue*FEET_TO_ENCODER_K);
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
