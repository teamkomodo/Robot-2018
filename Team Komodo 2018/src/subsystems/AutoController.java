package subsystems;

import comAnalogAdis16448.ADIS16448_IMU;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import robotMain.RobotMap;

public class AutoController {
	private final double DEFAULT_SPEED = 0.6;
    private final double ROBOT_DIAMETER_FT = 24.8/12;
    private final double FEET_TO_ENCODER_K = 5100;
    //private final double FEET_TO_ENCODER_LK = -5100; // when going forwards
    //private final double FEET_TO_ENCODER_RK = 4200; // when going backwards
    private final double FEET_TO_ENCODER_K_P = -5100;

    private double autoSpeed = DEFAULT_SPEED;
    
    private DriveSystem driveSystem;
    private ADIS16448_IMU autoGyro;
    
    public AutoController(DriveSystem drive) {
    	driveSystem = drive;
    	autoGyro = new ADIS16448_IMU();
    }
    
    // Called every DriveSystem's periodic
    public void update() {
    }
    
    public void tank(double leftSpeed, double rightSpeed) {
    	// The motors are reversed, so negative values will send the robot forward
    	driveSystem.getDrive().tankDrive(leftSpeed, rightSpeed);
    }
    
    public void arcade(double speed, double angle) {
    	driveSystem.getDrive().arcadeDrive(speed, angle);
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
    
    public void resetGyro() {
    	autoGyro.reset();
    }
    
    public double getAngle() {
    	return autoGyro.getAngleX();
    }
}
