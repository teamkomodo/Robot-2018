package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoGyroForwardCommand;
import commands.auto.AutoGyroRotateCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import robotMain.Robot.POSITION;

public class AutoMiddleStartCommandGroup extends CommandGroup{
	private double FORWARD_ONE_FT = 2.5;
	private double FORWARD_TWO_FT = 5.5;
	private double LIFT_TIME_S = 2;
	private double END_DISTANCE_FT = 1;
	private double MANIPULATE_TIME_S = 1;
	private double BACKWARD_TO_CUBE = -3;
	private double INTAKE_FOR_CUBE = -4;
	private double FORWARD_TO_CUBE = 4;
	
	private double turnToSwitchDegreesOne;// = -45;
	private double turnToSwitchDegreesTwo;// = 45;
	

	public AutoMiddleStartCommandGroup(POSITION side) {//add parameter to determine side
		turnToSwitchDegreesOne = 45;
		turnToSwitchDegreesTwo = -45;
		if (side.equals(POSITION.RIGHT)) {
			turnToSwitchDegreesOne *= -1;
			turnToSwitchDegreesTwo *= -1;

		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoDriveWaitTimeCommand());
		addSequential(new AutoGyroForwardCommand(FORWARD_ONE_FT));
		addSequential(new AutoGyroRotateCommand(turnToSwitchDegreesOne));
		addParallel(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoGyroForwardCommand(FORWARD_TWO_FT));
		//addSequential(new AutoGyroForwardCommand(FORWARD_TWO_FT/2.0, 0.6));
		addSequential(new AutoGyroRotateCommand(turnToSwitchDegreesTwo));
		//addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoGyroForwardCommand(END_DISTANCE_FT));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
		//get a cube
		addSequential(new AutoGyroForwardCommand(BACKWARD_TO_CUBE));
		addParallel(new AutoLiftTimeCommand(-LIFT_TIME_S*0.9));
		addSequential(new AutoGyroRotateCommand(turnToSwitchDegreesTwo));
		addParallel(new AutoManipulateTimeCommand(INTAKE_FOR_CUBE));
		addSequential(new AutoGyroForwardCommand(FORWARD_TO_CUBE, 0.6));
		//score cube
		addSequential (new AutoLiftTimeCommand(0.25));
		addSequential (new AutoGyroForwardCommand(-FORWARD_TO_CUBE));
		addSequential (new AutoGyroRotateCommand(turnToSwitchDegreesOne));
		addSequential (new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential (new AutoGyroForwardCommand(-BACKWARD_TO_CUBE));
	}
}
