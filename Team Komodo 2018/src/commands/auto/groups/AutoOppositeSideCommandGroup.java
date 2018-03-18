package commands.auto.groups;

import commands.auto.AutoDriveWaitTimeCommand;
import commands.auto.AutoForwardDistanceCommand;
import commands.auto.AutoLiftTimeCommand;
import commands.auto.AutoManipulateTimeCommand;
import commands.auto.AutoEncoderRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
	
	public AutoOppositeSideCommandGroup(String side, String goal) {//add parameter to determine side
		if (side.equals("right")) {
			turnToGoalDegrees *= -1;
		}
		if(goal.equals("scale")) {
			START_FT = 0;
			finalApproachFT = 0;
			finalTurnDegrees *= -1;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
		addSequential(new AutoDriveWaitTimeCommand());
		addSequential(new AutoForwardDistanceCommand(START_FT));
		addSequential(new AutoRotateWaitCommandGroup(turnToGoalDegrees));
		addSequential(new AutoForwardDistanceCommand(NEXT_FT));
		addSequential(new AutoRotateWaitCommandGroup(finalTurnDegrees));
		addSequential(new AutoForwardDistanceCommand(finalApproachFT));
		addSequential(new AutoRotateWaitCommandGroup(finalTurnDegrees));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoForwardDistanceCommand(END_DISTANCE_FT));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
