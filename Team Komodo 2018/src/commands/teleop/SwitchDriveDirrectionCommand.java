package commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import robotMain.RobotMap;

public class SwitchDriveDirrectionCommand extends Command {

	@Override
	protected void execute() {
		RobotMap.isReverseDrive = !RobotMap.isReverseDrive;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
