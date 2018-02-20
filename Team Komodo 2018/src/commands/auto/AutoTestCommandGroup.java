package commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTestCommandGroup extends CommandGroup {
	public AutoTestCommandGroup() {
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoRotateCommand(90));
		addSequential(new AutoForwardDistanceCommand(1));
		addSequential(new AutoForwardDistanceCommand(-1));
		addSequential(new AutoRotateCommand(-90));
		addSequential(new AutoForwardDistanceCommand(-1));
	}
}
