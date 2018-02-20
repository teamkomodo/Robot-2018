package commands.auto;

import robotMain.Robot;

public class AutoForwardDistanceCommand extends AutoCommand {
	private int encoderValue;
	private int stopValue;
	
    public AutoForwardDistanceCommand(double distanceIN) {
        requires(Robot.driveSystem);
        
        encoderValue = Robot.driveSystem.getLeftEncoderRaw();
        stopValue = encoderValue+controller.inchesToEncoder(distanceIN);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("Hi");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed;
    	if (stopValue-encoderValue < 0)
    		speed = -controller.getAutoSpeed();
    	else
    		speed = controller.getAutoSpeed();
    	
    	Robot.driveSystem.getDrive().tankDrive(-0.1, -0.1);
    	encoderValue = Robot.driveSystem.getLeftEncoderRaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(stopValue)-Math.abs(encoderValue) <= 25)
    		return true;
    	return false;
    }
}
