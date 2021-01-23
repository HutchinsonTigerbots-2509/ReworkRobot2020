// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Drivetrain Class for the drivetrain mechanism on the robot.
 * Utilizes the DifferentialDrive.TankDrive to move the the robot around.
 * <p> Constructor: {@link #Drivetrain()}
 */
public class Drivetrain extends SubsystemBase {

  //#region Variable Declaration

  // Variables for drivetrain \\
  private WPI_TalonFX leftFront = Robot.leftFront;
  private WPI_TalonFX leftRear = Robot.leftRear;
  private WPI_TalonFX rightFront = Robot.rightFront;
  private WPI_TalonFX rightRear = Robot.rightRear;
  
  private TalonFXSensorCollection leftFrontCollection = Robot.leftFrontCollection;
  private TalonFXSensorCollection leftRearCollection = Robot.leftRearCollection;
  private TalonFXSensorCollection rightFrontCollection = Robot.rightFrontCollection;
  private TalonFXSensorCollection rightRearCollection = Robot.rightRearCollection;

  private SpeedControllerGroup leftMotorGroup = Robot.leftMotorGroup;
  private SpeedControllerGroup rightMotorGroup = Robot.rightMotorGroup;

  private AHRS drivetrainGyro = Robot.drivetrainGyro;

  private DifferentialDrive drive = Robot.drive;

  private boolean encoderNotReset = true;

  //#endregion
  
  /**
   * Creates a new Drivetrain object.
   */
  public Drivetrain() {
    // Sets the PID values for the motors
    leftFront.config_kP(0, Constants.kDrivetrainPGain);
    leftFront.config_kI(0, Constants.kDrivetrianIGain);
    leftFront.config_kD(0, Constants.kDrivetrainDGain);
    leftFront.setNeutralMode(NeutralMode.Coast);

    leftRear.config_kP(0, Constants.kDrivetrainPGain);
    leftRear.config_kI(0, Constants.kDrivetrianIGain);
    leftRear.config_kD(0, Constants.kDrivetrainDGain);
    leftRear.setNeutralMode(NeutralMode.Coast);

    rightFront.config_kP(0, Constants.kDrivetrainPGain);
    rightFront.config_kI(0, Constants.kDrivetrianIGain);
    rightFront.config_kD(0, Constants.kDrivetrainDGain);
    rightFront.setNeutralMode(NeutralMode.Coast);

    rightRear.config_kP(0, Constants.kDrivetrainPGain);
    rightRear.config_kI(0, Constants.kDrivetrianIGain);
    rightRear.config_kD(0, Constants.kDrivetrainDGain);
    rightRear.setNeutralMode(NeutralMode.Coast);

    ResetEncoders();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Moves the drivetrain either forward or backward
   * @param speed
   */
  public void MoveDrivetrain(double speed) {
    drive.tankDrive(speed, speed);
  }

  /**
   * Moves the robots drivetrain to turn either left or right
   * at the desired speed based on the boolean value
   * @param speed
   * @param left
   */
  public void TurnRobot(double speed, boolean left) {
    if (left) {
      drive.tankDrive(-speed, speed);
    } else {
      drive.tankDrive(speed, -speed);
    }
  }

  /**
   * Stops the drivetrain motors
   */
  public void StopMotors() {
    drive.tankDrive(0, 0);
  }

  /**
   * Drives the robot like in mario kart.
   * LETS A GOOOOOOOOO!
   * @param stick
   */
  public void MarioDrive(Joystick stick) {
    double speedMult = 0.5;
    double turnSpeedMult = 0.4;
    double speedX = 0.0;
    double speedY = 0.0;

    if (stick.getRawAxis(Constants.xboxLeftJoystickVertical) != 0) {
      speedX = stick.getRawAxis(Constants.xboxLeftJoystickVertical) * speedMult;
    }

    speedY = stick.getRawAxis(Constants.xboxRightJoystickHorizontal) * turnSpeedMult;
    
    if (speedX > 0.05) {
      drive.arcadeDrive(speedX, speedY);
    } else if (speedX < -0.05) {
      drive.arcadeDrive(speedX, speedY);
    } else {
      drive.arcadeDrive(0,speedY);
    }
  }

  /**
   * Same concept as {@link #MarioDrive(Joystick)}, but with a ramp
   * up and down function.
   * @param stick
   */
  public void MarioDriveRamp(Joystick stick) {
    //TODO
  }

  /**
   * Resets TalonFX's encoder values to 0
   */
  public void ResetEncoders() {
    leftFront.setSelectedSensorPosition(0);
    leftRear.setSelectedSensorPosition(0);
    rightFront.setSelectedSensorPosition(0);
    rightRear.setSelectedSensorPosition(0);

    leftFrontCollection.setIntegratedSensorPosition(0, 0);
    leftRearCollection.setIntegratedSensorPosition(0, 0);
    rightFrontCollection.setIntegratedSensorPosition(0, 0);
    rightRearCollection.setIntegratedSensorPosition(0, 0);
  }
}
