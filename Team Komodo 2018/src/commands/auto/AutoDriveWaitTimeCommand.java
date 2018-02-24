package commands.auto;

import robotMain.Robot;

public class AutoDriveWaitTimeCommand extends AutoCommand {
	private int timeCT;
	private int time;
	
    public AutoDriveWaitTimeCommand(int tCT) {
    	requires(Robot.driveSystem);
    	
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
    	Robot.driveSystem.getDrive().tankDrive(0, 0);
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
