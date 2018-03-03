package commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Command;
import robotMain.Robot;

public class TeleopManipulatorCommand extends Command {
	private PWMSpeedController manipulatorController;
	private Joystick gamepadR;
	
    public TeleopManipulatorCommand() {
        requires(Robot.manipulatorSystem);
 
        manipulatorController = Robot.manipulatorSystem.getManipulatorController();
        gamepadR = Robot.oi.getGamepadR();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	manipulatorController.set(gamepadR.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
