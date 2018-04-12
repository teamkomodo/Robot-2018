package subsystems;

import commands.teleop.TeleopManipulatorCommand;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;

public class ManipulatorSystem extends Subsystem {
	private PWMSpeedController leftIntakeController;
	private PWMSpeedController rightIntakeController;
	private SpeedControllerGroup intakeControllers;
	private PWMSpeedController rotatorController;
	
	public ManipulatorSystem() {
		leftIntakeController = new Victor(RobotMap.intakeLeftControllerPort);
		rightIntakeController = new Victor(RobotMap.intakeRightControllerPort);
		intakeControllers = new SpeedControllerGroup(leftIntakeController, rightIntakeController);
		if (RobotMap.isCompetitionBot) {
			rotatorController = new Talon(RobotMap.rotatorControllerPort);
		}
		else {
			rotatorController = new Victor(RobotMap.rotatorControllerPort);
		}
	}

	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new TeleopManipulatorCommand());
	}
  
    @Override
    public void periodic() {
    }
    
    public PWMSpeedController getLeftIntakeController() {
    	return leftIntakeController;
    }
    public PWMSpeedController getRightIntakeController() {
    	return rightIntakeController;
    }
    public SpeedControllerGroup getIntakeControllers() {
    	return intakeControllers;
    }
    
    public PWMSpeedController getRotatorController() {
    	return rotatorController;
    }
}
