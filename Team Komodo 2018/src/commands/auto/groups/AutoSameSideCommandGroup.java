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
	
	private double startFT;
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
		}else {
			startFT = 20;
		}
		constructCommandGroup();
	}
	private void constructCommandGroup() {
//		System.out.println("startFT : "+startFT);
//		System.out.println("turnToGoalDegrees : "+turnToGoalDegrees);
//		System.out.println("useL : "+useL);
//		System.out.println("useR : "+useR);
//		System.out.println("LIFT_TIME_S : "+LIFT_TIME_S);
//		System.out.println("MANIPULATE_TIME_S : "+MANIPULATE_TIME_S);
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
