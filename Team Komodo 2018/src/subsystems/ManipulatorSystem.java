package subsystems;

import commands.teleop.TeleopManipulatorCommand;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;

public class ManipulatorSystem extends Subsystem {
	private PWMSpeedController manipulatorController;
	private PWMSpeedController rotatorController;
	
	public ManipulatorSystem() {
		manipulatorController = new Victor(RobotMap.manipulatorControllerPort);
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
    
    public PWMSpeedController getManipulatorController() {
    	return manipulatorController;
    }
    
    public PWMSpeedController getRotatorController() {
    	return rotatorController;
    }
}
