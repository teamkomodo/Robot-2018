package commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightRightScaleCommandGroup extends CommandGroup{
	private final double startToSwitch = 0;
	private final double startToSwitch2 = 0;
	private final double turnToSwitch = 0;
	private final double turn1 = 0;
	private final double endDistnace = 0;
	private final int liftTime = 0;
	private final int manipulateTime = 0;
	private final int waitTime = 10;
	
	public AutoRightRightScaleCommandGroup() {
		addSequential(new AutoForwardDistanceCommand(startToSwitch));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		addSequential(new AutoRotateCommand(turn1));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		addParallel(new AutoLiftTimeCommand(liftTime));
		addSequential(new AutoForwardDistanceCommand(startToSwitch2));
		//addSequential(new AutoLiftTimeCommand(liftTime));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		addSequential(new AutoRotateCommand(turnToSwitch));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		addSequential(new AutoForwardDistanceCommand(endDistnace));
		addSequential(new AutoManipulateTimeCommand(manipulateTime));
		
	}
}
