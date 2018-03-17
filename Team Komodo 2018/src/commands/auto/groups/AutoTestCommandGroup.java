package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTestCommandGroup extends CommandGroup {
	public AutoTestCommandGroup() {
		/*
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoForwardDistanceCommand(-1));
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoForwardDistanceCommand(-1));
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoForwardDistanceCommand(-1));
		*/
		/*
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoRotateCommand(90));
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoForwardDistanceCommand(-1));
		addSequential(new AutoRotateCommand(-90));
		addSequential(new AutoForwardDistanceCommand(-1));
		*/
		/*
		addSequential(new AutoForwardDistanceCommand(3));
		addSequential(new AutoDriveWaitTimeCommand(10));
		addSequential(new AutoRotateCommand(90));
		addSequential(new AutoForwardDistanceCommand(5.5));
		addSequential(new AutoDriveWaitTimeCommand(10));
		addSequential(new AutoRotateCommand(-90));
		addSequential(new AutoForwardDistanceCommand(3));
		*/
		addSequential(new AutoForwardDistanceCommand(3));
		addSequential(new AutoRotateWaitCommandGroup(90));
		addSequential(new AutoForwardDistanceCommand(3));
		addSequential(new AutoForwardDistanceCommand(-3));
		addSequential(new AutoRotateWaitCommandGroup(-90));
		addSequential(new AutoForwardDistanceCommand(-3));
	}
}