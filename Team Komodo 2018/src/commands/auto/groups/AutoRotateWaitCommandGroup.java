package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRotateWaitCommandGroup extends CommandGroup{

	public AutoRotateWaitCommandGroup(double rotate) {
		addSequential(new AutoDriveWaitTimeCommand(2.5));
		addSequential(new AutoRotateCommand(rotate));
		addSequential(new AutoDriveWaitTimeCommand(2.5));
	}
}
