// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //#region Robot Variables

  // General Variables \\
  public static RobotContainer robotContainer; // Initialized in robotInit
  

  // Climbing Variables \\
  public static WPI_TalonSRX climbMotor = new WPI_TalonSRX(Constants.kClimbMotorID);
  public static WPI_TalonSRX climbLock = new WPI_TalonSRX(Constants.kClimbLockID);

  public static AnalogInput climbLimit = new AnalogInput(Constants.kClimbLimitID);
  

  // Color Wheel Variables \\
  public static WPI_VictorSPX colorWheelMotor = new WPI_VictorSPX(Constants.kColorMotorID);
  

  // Conveyor Variables \\
  public static WPI_VictorSPX topConveyorMotor = new WPI_VictorSPX(Constants.kTopConveyorMotorID);
  public static WPI_VictorSPX bottomConveyorMotor = new WPI_VictorSPX(Constants.kBottomConveyorMotorID);

  public static AnalogInput topLightSensor = new AnalogInput(Constants.kTopLightSensorID);
  public static AnalogInput bottomLightSensor = new AnalogInput(Constants.kBottomLightSensorID);
  

  // Drivetrain Variables \\
  public static WPI_TalonFX leftFront = new WPI_TalonFX(Constants.kLeftFrontID);
  public static WPI_TalonFX leftRear = new WPI_TalonFX(Constants.kLeftRearID);
  public static WPI_TalonFX rightFront = new WPI_TalonFX(Constants.kRightFrontID);
  public static WPI_TalonFX rightRear = new WPI_TalonFX(Constants.kRightRearID);
  
  public static TalonFXSensorCollection leftFrontCollection = leftFront.getSensorCollection();
  public static TalonFXSensorCollection leftRearCollection = leftRear.getSensorCollection();
  public static TalonFXSensorCollection rightFrontCollection = rightFront.getSensorCollection();
  public static TalonFXSensorCollection rightRearCollection = rightRear.getSensorCollection();

  public static SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  public static SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  public static AHRS drivetrainGyro = new AHRS(SPI.Port.kMXP);

  public static DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  

  // Intake Variables \\
  public static WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Constants.kIntakeMotorID);
  public static WPI_TalonSRX intakeDropMotor = new WPI_TalonSRX(Constants.kIntakeDropMotorID);
  

  // Shooter Variables \\
  public static WPI_TalonSRX topShooterMotor = new WPI_TalonSRX(Constants.kTopShooterMotorID);
  public static WPI_TalonSRX bottomShooterMotor = new WPI_TalonSRX(Constants.kBottomShooterMotorID);
  

  // Turret Variables \\
  public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(Constants.kTurretMotorID);
  public static AnalogInput turretLimit = new AnalogInput(Constants.kTurretLimitID);

  //#endregion

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
