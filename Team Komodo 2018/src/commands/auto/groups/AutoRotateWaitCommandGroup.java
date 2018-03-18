package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRotateWaitCommandGroup extends CommandGroup{

	public AutoRotateWaitCommandGroup(double rotate) {
		addSequential(new AutoDriveWaitTimeCommand(2.5));
		addSequential(new AutoEncoderRotateCommand(rotate));
		addSequential(new AutoDriveWaitTimeCommand(2.5));
	}
}
