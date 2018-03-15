package commands.auto;

import robotMain.Robot;

public class AutoLiftTimeCommand extends AutoCommand{

	private double speed = 0.7;
	private int timeCT;
	private int time;
	
    public AutoLiftTimeCommand(int tCT) {
    	requires(Robot.lifterSystem);
    	
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
    	Robot.lifterSystem.getLifterController().set(speed);
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
