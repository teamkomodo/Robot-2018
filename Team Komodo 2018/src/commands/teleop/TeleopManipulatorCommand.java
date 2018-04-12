package commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotMain.Robot;

public class TeleopManipulatorCommand extends Command {
	private final double SLOW_EJECT_SPEED = 0.35;
	private final double SINGLE_SIDE_INTAKE_SPEED = 0.7;
	
	private PWMSpeedController leftIntakeController;
	private PWMSpeedController rightIntakeController;
	private SpeedControllerGroup intakeControllers;
	private PWMSpeedController rotatorController;
	private Joystick gamepadR;
	private JoystickButton gamepadRB;//rotate down
	private JoystickButton gamepadLB;//rotate up
	private JoystickButton halfSpeedOut;
	private JoystickButton rightIntake;
	private JoystickButton leftIntake;
	
    public TeleopManipulatorCommand() {
        requires(Robot.manipulatorSystem);
 
        leftIntakeController = Robot.manipulatorSystem.getLeftIntakeController();
        rightIntakeController = Robot.manipulatorSystem.getRightIntakeController();
        intakeControllers = Robot.manipulatorSystem.getIntakeControllers();
        rotatorController = Robot.manipulatorSystem.getRotatorController();
        
        gamepadR = Robot.oi.getGamepadR();
        gamepadRB = Robot.oi.getGamepadRB();
        gamepadLB = Robot.oi.getGamepadLB();
        halfSpeedOut = Robot.oi.getHalfSpeedOut();
        rightIntake = Robot.oi.getRightIntake();
        leftIntake = Robot.oi.getLeftIntake();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed = SLOW_EJECT_SPEED * Robot.getAmpAdjust();//intake out speed
		if(halfSpeedOut.get()) {
    		intakeControllers.set(-speed);
    	}else if (rightIntake.get() || leftIntake.get()){
    		//single-side intake.  Inclusive: can run both wheels in by pressing both buttons.
    		speed = SINGLE_SIDE_INTAKE_SPEED;
    		if(rightIntake.get()) {
    			rightIntakeController.set(speed);
    		}if(leftIntake.get()) {
    			leftIntakeController.set(speed);
    		}
    	}else {
    		intakeControllers.set(gamepadR.getY());
    	}
    	
    	SmartDashboard.putBoolean("Button LB", gamepadLB.get());
    	SmartDashboard.putBoolean("Button RB", gamepadRB.get());
    	
    	double speed2 = 0.6 * Robot.getAmpAdjust();//rotator motor speed
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
