package commands.auto;

import robotMain.Robot;

public class AutoLiftTimeCommand extends AutoCommand{

	private double speed = 0.7;
	
	
    public AutoLiftTimeCommand(double tCT) {
    	requires(Robot.lifterSystem);
    	if(tCT < 0) {//input negative numbers to move lift down
    		tCT*=-1;
    		speed *= -1;
    	}
        setTimeout(tCT);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.lifterSystem.getLifterController().set(-speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (isTimedOut())
    		Robot.lifterSystem.getLifterController().set(0);
    	return isTimedOut();
    }
}
