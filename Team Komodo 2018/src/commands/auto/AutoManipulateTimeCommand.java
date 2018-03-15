package commands.auto;

import robotMain.Robot;

public class AutoManipulateTimeCommand extends AutoCommand{

	private double speed = 0.7;
	private int timeCT;
	private int time;
	
    public AutoManipulateTimeCommand(int tCT) {
    	requires(Robot.manipulatorSystem);
    	
        timeCT = tCT;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	time = 0;
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.manipulatorSystem.getManipulatorController().set(speed);
    	time++;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (time >= timeCT)
    		return true;
    	return false;
    }
}
