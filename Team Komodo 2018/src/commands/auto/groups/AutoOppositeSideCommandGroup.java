package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoGyroForwardCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import robotMain.Robot.POSITION;

public class AutoOppositeSideCommandGroup extends CommandGroup{
	private double LIFT_TIME_S = 0;
	private double NEXT_FT = 0;
	private double END_DISTANCE_FT = 0;
	private double MANIPULATE_TIME_S = 0;
	private double START_FT = 0;
	
	//default values set to switch
	private double turnToGoalDegrees = 0;
	private double finalApproachFT = 0;
	private double finalTurnDegrees = 0;
	
	public AutoOppositeSideCommandGroup(POSITION side, Boolean scale) {//add parameter to determine side
		if (side.equals(POSITION.RIGHT)) {
			turnToGoalDegrees *= -1;
		}
		if(scale) {
			START_FT = 0;
			finalApproachFT = 0;
			finalTurnDegrees *= -1;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoDriveWaitTimeCommand());
		addSequential(new AutoGyroForwardCommand(START_FT));
		addSequential(new AutoEncoderRotateWaitCommandGroup(turnToGoalDegrees));
		addSequential(new AutoGyroForwardCommand(NEXT_FT));
		addSequential(new AutoEncoderRotateWaitCommandGroup(finalTurnDegrees));
		addSequential(new AutoGyroForwardCommand(finalApproachFT));
		addSequential(new AutoEncoderRotateWaitCommandGroup(finalTurnDegrees));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoGyroForwardCommand(END_DISTANCE_FT));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
