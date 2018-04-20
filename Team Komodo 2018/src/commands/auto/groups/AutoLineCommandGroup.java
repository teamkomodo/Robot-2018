package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoGyroForwardCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineCommandGroup extends CommandGroup{

	private double forward = 10;
	private double goBack = 0;
	
	public AutoLineCommandGroup() {
		addSequential (new AutoDriveWaitTimeCommand(10));
		addSequential (new AutoGyroForwardCommand(forward));
		addSequential (new AutoGyroForwardCommand(goBack));
	}
}
