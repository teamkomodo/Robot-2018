package commands.teleop;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotMain.Robot;

public class TeleopManipulatorRotateCommand extends Command {
	private PWMSpeedController manipulatorController;
	private JoystickButton gamepadRB;//down
	private JoystickButton gamepadLB;//up
	
    public TeleopManipulatorRotateCommand() {
        requires(Robot.manipulatorSystem);
 
        manipulatorController = Robot.manipulatorRotatorSystem.getManipulatorController();
        gamepadRB = Robot.oi.getGamepadRB();
        gamepadLB = Robot.oi.getGamepadLB();
        
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	SmartDashboard.putBoolean("Button LB", gamepadLB.get());
    	SmartDashboard.putBoolean("Button RB", gamepadRB.get());
    	
    	double speed = 0.5;
    	if (gamepadRB.get()&& !gamepadLB.get()) {
    		manipulatorController.set(speed);
    	}else if (gamepadLB.get()&& !gamepadRB.get()) {
    		manipulatorController.set(-speed);
    	}else {
    	}
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
