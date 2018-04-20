package commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import robotMain.Robot;

public class PrintEncodersCommand extends Command {
	private boolean done = false;
	
	@Override
	protected void initialize() {
		//System.out.println("initializing PrintEncoders");
		//Robot.driveSystem.resetLeftEncoder();
		//Robot.driveSystem.resetRightEncoder();
		done = false;
	}
	@Override
	protected void execute() {
		System.out.println("Right encoder:	" + Robot.driveSystem.getRightEncoderRaw());
		System.out.println("Left encoder:	" + Robot.driveSystem.getLeftEncoderRaw());
		Robot.driveSystem.resetRightEncoder();
		Robot.driveSystem.resetLeftEncoder();
		done = true;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return done;
	}

}
