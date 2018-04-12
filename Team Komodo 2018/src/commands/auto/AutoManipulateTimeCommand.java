package commands.auto;

import robotMain.Robot;

public class AutoManipulateTimeCommand extends AutoCommand{

	private double speed = 0.7;

	
    public AutoManipulateTimeCommand(double tCT) {
    	requires(Robot.manipulatorSystem);
    	
        setTimeout(tCT);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.manipulatorSystem.getLeftIntakeController().set(-speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (isTimedOut()) 
        	Robot.manipulatorSystem.getLeftIntakeController().set(0);
    	return isTimedOut();
    }
}
