// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.sql.DriverAction;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //#region Variable Declarations

  // Joystick Variables \\
  public Joystick OpStick = new Joystick(Constants.OpJoystickPortID);
  public Joystick CoOpStick = new Joystick(Constants.CoOpJoystickPortID);

  private JoystickButton alignButton;
  private JoystickButton runConveyorMaxButton;
  private JoystickButton conveyorReverseButton;
  private JoystickButton shootAllButton;
  private JoystickButton runShooterMaxButton;
  private JoystickButton colorWheelForward;
  private JoystickButton colorWheelReverse;
  private JoystickButton switchPipelineButton;
  private JoystickButton extendClimberButton;
  private JoystickButton retractClimberButton;
  private JoystickButton creepLeftButton;
  private JoystickButton creepRightButton;
  private JoystickButton disableSensorButton;
  private JoystickButton climbLock;


  // Subsystems \\
  public Climb climb = new Climb();
  public ColorWheel colorWheel = new ColorWheel();
  public Conveyor conveyor = new Conveyor();
  public Drivetrain drivetrain = new Drivetrain();
  public Intake intake = new Intake();
  public Shooter shooter = new Shooter();
  public Turret turret = new Turret();
  public Vision vision = new Vision();

  //#endregion

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
