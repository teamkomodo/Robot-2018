package commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftLeftSwitchCommandGroup extends CommandGroup {
	private double startToSwitch = 3;
	private double turnToSwitch = 90;
	private double endDistnace = 0.5;
	private double liftTime = 3;
	private double manipulateTime = 3;
	
	public AutoLeftLeftSwitchCommandGroup() {
		addSequential(new AutoForwardDistanceCommand(startToSwitch));
		addSequential(new AutoDriveWaitTimeCommand(10));
		addSequential(new AutoRotateCommand(turnToSwitch));
		addSequential(new AutoDriveWaitTimeCommand(10));
		//addSequential(new AutoLiftTimeCommand(liftTime));		//need to define lift time command
		addSequential(new AutoForwardDistanceCommand(endDistnace));
		//addSequential(new AutoManipulateTimeCommand(manipulateTime));	//need to define command
		
	}
}
