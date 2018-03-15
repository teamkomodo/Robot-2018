package commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightRightSwitchCommandGroup extends CommandGroup{
	private final double startToSwitch = 0;
	private final double startToSwitch2 = 0;
	private final double turnToSwitch = 0;
	private final double endDistnace = 0;
	private final int liftTime = 0;
	private final int manipulateTime = 0;
	private final int waitTime = 10;
	
	public AutoRightRightSwitchCommandGroup() {
		addSequential(new AutoForwardDistanceCommand(startToSwitch));
		addParallel(new AutoLiftTimeCommand(liftTime));
		addSequential(new AutoForwardDistanceCommand(startToSwitch2));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		addSequential(new AutoRotateCommand(turnToSwitch));
		addSequential(new AutoDriveWaitTimeCommand(waitTime));
		//addSequential(new AutoLiftTimeCommand(liftTime));
		addSequential(new AutoForwardDistanceCommand(endDistnace));
		addSequential(new AutoManipulateTimeCommand(manipulateTime));
		
	}
}
