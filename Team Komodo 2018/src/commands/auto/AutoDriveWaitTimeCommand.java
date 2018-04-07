package commands.auto;

import robotMain.Robot;

public class AutoDriveWaitTimeCommand extends AutoCommand {

	private double waitBeginning = 0;
	
    public AutoDriveWaitTimeCommand(double tCT) {
    	requires(Robot.driveSystem);
    	
        setTimeout(tCT);
    }
    public AutoDriveWaitTimeCommand() {
    	requires(Robot.driveSystem);
    	
        setTimeout(waitBeginning);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.driveSystem.getDrive().tankDrive(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return isTimedOut();
    }
}
