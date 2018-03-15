package commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import robotMain.Robot;

public class PrintEncodersCommand extends Command {

	@Override
	protected void initialize() {
		System.out.println("initializing PrintEncoders");
		//Robot.driveSystem.resetLeftEncoder();
		//Robot.driveSystem.resetRightEncoder();
		
	}
	@Override
	protected void execute() {
		System.out.println("Right encoder:/t" + Robot.driveSystem.getRightEncoderRaw());
		System.out.println("Left encoder:/t" + Robot.driveSystem.getLeftEncoderRaw());
		Robot.driveSystem.resetRightEncoder();
		Robot.driveSystem.resetLeftEncoder();

	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
