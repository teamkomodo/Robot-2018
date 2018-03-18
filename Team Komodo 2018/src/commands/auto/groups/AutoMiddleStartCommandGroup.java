package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleStartCommandGroup extends CommandGroup{
	private double FORWARD_ONE_FT = 0;
	private double FORWARD_TWO_FT = 0;
	private double LIFT_TIME_S = 0;
	private double END_DISTANCE_FT = 0;
	private double MANIPULATE_TIME_S = 0;
	
	private double turnToScaleDegreesOne = 0;
	private double turnToScaleDegreesTwo = 0;
	
	public AutoMiddleStartCommandGroup(String side) {//add parameter to determine side
		if (side.equals("right")) {
			turnToScaleDegreesOne *= -1;
			turnToScaleDegreesTwo *= -1;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoDriveWaitTimeCommand());
		addSequential(new AutoForwardDistanceCommand(FORWARD_ONE_FT));
		addSequential(new AutoEncoderRotateWaitCommandGroup(turnToScaleDegreesOne));
		addSequential(new AutoForwardDistanceCommand(FORWARD_TWO_FT));
		addSequential(new AutoEncoderRotateWaitCommandGroup(turnToScaleDegreesTwo));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoForwardDistanceCommand(END_DISTANCE_FT));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
