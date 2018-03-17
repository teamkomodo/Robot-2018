package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import commands.auto.AutoRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSameSideCommandGroup extends CommandGroup{
	private double LIFT_TIME_S = 0;
	private double END_DISTANCE_FT = 0;
	private double MANIPULATE_TIME_S = 0;
	
	private double startFT = 0;
	private double turnToScaleDegrees = 0;
	
	public AutoSameSideCommandGroup(String side, String goal) {//add parameter to determine side
		if (side.equals("right")) {
			turnToScaleDegrees *= -1;
		}
		if(goal.equals("switch")) {
			startFT = 0;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoDriveWaitTimeCommand());
		addSequential(new AutoForwardDistanceCommand(startFT));
		addSequential(new AutoRotateWaitCommandGroup(turnToScaleDegrees));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoForwardDistanceCommand(END_DISTANCE_FT));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
