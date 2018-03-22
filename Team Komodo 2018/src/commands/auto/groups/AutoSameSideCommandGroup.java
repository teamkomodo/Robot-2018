package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoGyroForwardCommand;
import commands.auto.AutoGyroRotateCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSameSideCommandGroup extends CommandGroup{
	private double LIFT_TIME_S = 11;
	private double END_DISTANCE_FT = 55/12.0;
	private double MANIPULATE_TIME_S = 1;
	
	private double startFT = 20;
	private double turnToGoalDegrees = 90;
	private boolean useL = false;
	private boolean useR = true;
	
	public AutoSameSideCommandGroup(String side, String goal) {//add parameter to determine side
		if (side.equals("right")) {
			turnToGoalDegrees *= -1;
			useL = true;
			useR = false;
		}
		if(goal.equals("switch")) {
			startFT = 168/12.0;
			LIFT_TIME_S = 4;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoGyroForwardCommand(startFT));
		addSequential(new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoGyroRotateCommand(turnToGoalDegrees, useL, useR));
		addSequential(new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoGyroForwardCommand(1));
		addSequential (new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
