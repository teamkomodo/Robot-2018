// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package robotMain;

import subsystems.DriveType;
import commands.auto.AutoEncoderRotateCommand;
import commands.teleop.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick leftJoystick;
    private Joystick rightJoystick;

    private Button setTankButton;
    private Button setArcade1Button;
    private Button setArcade2Button;
    
    private Button printEncoders;
    
    private JoystickButton rotateManipulatorUp;
    private JoystickButton rotateManipulatorDown;
    private JoystickButton switchDriveDirection;    
    private JoystickButton halfSpeedOut;

    private Joystick gamepadL;
    private Joystick gamepadR;

    public OI() {
    	System.out.println("Hello OI");
        leftJoystick = new Joystick(RobotMap.leftJoystickPort);
        rightJoystick = new Joystick(RobotMap.rightJoystickPort);
        
        setTankButton = new JoystickButton (rightJoystick, 12);
        setTankButton.whenPressed(new SetDriveTypeCommand(DriveType.TANK));
        //setTankButton.whenPressed(new AutoRotateCommand(90));
        
        setArcade1Button = new JoystickButton (rightJoystick, 11);
        setArcade1Button.whenPressed(new SetDriveTypeCommand(DriveType.ARCADE_1));
        //setArcade1Button.whenPressed(new AutoRotateCommand(-90));
        
        setArcade2Button = new JoystickButton (rightJoystick, 10);
        setArcade2Button.whenPressed(new SetDriveTypeCommand(DriveType.ARCADE_2));
        
        printEncoders = new JoystickButton(rightJoystick, 9);
        printEncoders.whenPressed(new PrintEncodersCommand());
        
        switchDriveDirection = new JoystickButton(rightJoystick, 3);
        switchDriveDirection.whenPressed(new SwitchDriveDirrectionCommand());
        
        gamepadL = new Joystick(RobotMap.gamepadPort);
        gamepadL.setXChannel(RobotMap.gamepadLX);
        gamepadL.setYChannel(RobotMap.gamepadLY);
        //gamepadL.setZChannel(RobotMap.gamepadLT);
        
        gamepadR = new Joystick(RobotMap.gamepadPort);
        gamepadR.setXChannel(RobotMap.gamepadRX);
        gamepadR.setYChannel(RobotMap.gamepadRY);
        //gamepadR.setZChannel(RobotMap.gamepadRT);
        
        rotateManipulatorUp = new JoystickButton (gamepadL, RobotMap.gamepadLB);
        rotateManipulatorDown = new JoystickButton (gamepadL, RobotMap.gamepadRB);
        
        halfSpeedOut = new JoystickButton(gamepadL, RobotMap.gamepadX);
        
        RobotMap.isReverseDrive = false;

    }

    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }
    
    public Joystick getGamepadL() {
    	return gamepadL;
    }
    
    public Joystick getGamepadR() {
    	return gamepadR;
    }
    
    public JoystickButton getGamepadLB() {
    	return rotateManipulatorUp;
    }
    
    public JoystickButton getGamepadRB() {
    	return rotateManipulatorDown;
    }
    
    public JoystickButton getHalfSpeedOut() {
    	return halfSpeedOut;
    }
}

