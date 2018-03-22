package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoGyroForwardCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineCommandGroup extends CommandGroup{

	private double forward = 9;
	
	public AutoLineCommandGroup() {
		addSequential (new AutoDriveWaitTimeCommand());
		addSequential (new AutoGyroForwardCommand(forward));
	}
}
