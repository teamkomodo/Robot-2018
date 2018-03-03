// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;
import commands.teleop.*;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;


/**
 *
 */
public class LifterSystem extends Subsystem {
	private PWMSpeedController lifterController;
    private Servo wenchServo;
    
    public LifterSystem() {
    	lifterController = new Victor(RobotMap.lifterControllerPort);
    	//wenchServo = new Servo(RobotMap.wenchServoPort);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new TeleopLiftCommand());
    }
  
    @Override
    public void periodic() {
    }
    
    public PWMSpeedController getLifterController() {
    	return lifterController;
    }
    
}

