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

public class AutoSameSideCommandGroup extends CommandGroup{
	private double LIFT_TIME_S = 5.5;
	private double MANIPULATE_TIME_S = 1;
	
	private double startFT = 24;
	private double turnToGoalDegrees = 80;
	private boolean useL = true; //false;
	private boolean useR = true;
	
	public AutoSameSideCommandGroup(POSITION side, Boolean scale) {
		if(!scale) {
			turnToGoalDegrees = 90;
			//startFT = 168/12.0;
			startFT = 10.5;
			LIFT_TIME_S = 0;
			
			useL = true;
			useR = true;
		}
		if (side.equals(POSITION.RIGHT)) {
			turnToGoalDegrees *= -1;
			if (scale) {
				useL = true;
				useR = true;//false;
			}
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
		
		addSequential(new AutoGyroForwardCommand(startFT, 0.75));
		//addSequential(new AutoForwardDistanceCommand(startFT));
		addSequential(new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoGyroRotateCommand(turnToGoalDegrees, useL, useR));
		//addSequential(new AutoGyroRotateCommand(turnToGoalDegrees));
		addSequential(new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoLiftTimeCommand(LIFT_TIME_S));
		addSequential(new AutoGyroForwardCommand(1, 0.6));
		addSequential (new AutoDriveWaitTimeCommand(1));
		addSequential(new AutoManipulateTimeCommand(MANIPULATE_TIME_S));
	}
}
