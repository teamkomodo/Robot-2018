package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoEncoderRotateWaitCommandGroup extends CommandGroup{

	public AutoEncoderRotateWaitCommandGroup(double rotate) {
		addSequential(new AutoDriveWaitTimeCommand(0.5));
		addSequential(new AutoEncoderRotateCommand(rotate));
		addSequential(new AutoDriveWaitTimeCommand(0.5));
	}
}
