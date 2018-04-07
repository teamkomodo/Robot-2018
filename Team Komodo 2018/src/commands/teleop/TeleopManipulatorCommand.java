package commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotMain.Robot;

public class TeleopManipulatorCommand extends Command {
	private PWMSpeedController manipulatorController;
	private PWMSpeedController rotatorController;
	private Joystick gamepadR;
	private JoystickButton gamepadRB;//down
	private JoystickButton gamepadLB;//up
	private JoystickButton halfSpeedOut;
	
    public TeleopManipulatorCommand() {
        requires(Robot.manipulatorSystem);
 
        manipulatorController = Robot.manipulatorSystem.getManipulatorController();
        rotatorController = Robot.manipulatorSystem.getRotatorController();
        
        gamepadR = Robot.oi.getGamepadR();
        gamepadRB = Robot.oi.getGamepadRB();
        gamepadLB = Robot.oi.getGamepadLB();
        halfSpeedOut = Robot.oi.getHalfSpeedOut();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed = 0.35;//manipulator out speed
    	if(halfSpeedOut.get()) {
    		manipulatorController.set(-speed);
    	}else{
    		manipulatorController.set(gamepadR.getY());
    	}
    	
    	SmartDashboard.putBoolean("Button LB", gamepadLB.get());
    	SmartDashboard.putBoolean("Button RB", gamepadRB.get());
    	
    	double speed2 = 0.6;//rotator motor speed
    	if (gamepadRB.get() && !gamepadLB.get()) {
    		rotatorController.set(speed2);
    	} else if (gamepadLB.get() && !gamepadRB.get()) {
    		rotatorController.set(-speed2);
    	} else {
    		rotatorController.set(0);
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
