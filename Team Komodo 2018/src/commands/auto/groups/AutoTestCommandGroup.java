package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoGyroForwardCommand;
import commands.auto.AutoGyroRotateCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTestCommandGroup extends CommandGroup {
	public AutoTestCommandGroup() {
//		addSequential(new AutoGyroForwardCommand(3));
//		addSequential(new AutoEncoderRotateWaitCommandGroup(90));
//		addSequential(new AutoGyroForwardCommand(3));
//		addSequential(new AutoGyroForwardCommand(-3));
//		addSequential(new AutoEncoderRotateWaitCommandGroup(-90));
//		addSequential(new AutoGyroForwardCommand(-3));
		addSequential (new AutoGyroForwardCommand(1.0));
		addSequential (new AutoGyroRotateCommand(90));
		addSequential (new AutoDriveWaitTimeCommand(1));
		addSequential (new AutoGyroRotateCommand(-90));
		addSequential(new AutoManipulateTimeCommand(1));
		addSequential(new AutoLiftTimeCommand(1));
	}
}
