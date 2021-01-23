// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Turret Class for the turret mechanism on the robot.
 * <p> Constructor: {@link #Turret()}
 */
public class Turret extends SubsystemBase {

  //#region Variable Declaration

  // Variables for Turret subsystem \\
  private WPI_TalonSRX turretMotor = Robot.turretMotor;
  private AnalogInput turretLimit = Robot.turretLimit;

  private boolean leftLimit = false;
  private boolean rightLimit = false;
  private boolean direction; // true = right, flase = left
  private boolean aligned = false;

  //#endregion
  
  /**
   * Creates a new Turret object.
   */
  public Turret() {
    turretMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    GetLimitValue();

    SmartDashboard.putBoolean("Left Limit", leftLimit);
    SmartDashboard.putBoolean("Right Limit", rightLimit);

    if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxRightJoystickHorizontal) < -0.2 && !leftLimit) {
      TurnLeft(0.5);
      UpdateDirection(false);
    } else if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxRightJoystickHorizontal) > 0.2 && !rightLimit) {
      TurnRight(0.5);
      UpdateDirection(true);
    } else {
      StopTurretMotor();
    }
  }

  /**
   * Turns the turret to the left at a specified speed
   * and updates the direction to false (false = left)
   * @param speed
   */
  public void TurnLeft(double speed) {
    if (leftLimit) {
      turretMotor.set(0.0);
    } else {
      turretMotor.set(speed);
      UpdateDirection(false);
    }
  }

  /**
   * Turns the turret to the right at a specified speed
   * and updates the direction to true (true = right)
   * @param speed
   */
  public void TurnRight(double speed) {
    if (rightLimit) {
      turretMotor.set(0.0);
    } else {
      turretMotor.set(-speed);
      UpdateDirection(true);
    }
  }

  /**
   * Stops the turret motors
   */
  public void StopTurretMotor() {
    turretMotor.set(0);
  }

  /**
   * Updates the direction to be either true or false
   * depending on input (true = right, false = left)
   * @param direct
   */
  public void UpdateDirection (boolean direct) {
    if (!leftLimit && !rightLimit) {
      direction = direct;
    }
  }

  /**
   * Changes the values of leftlimit and rightlimit to equal the
   * direction needed
   */
  public void GetLimitValue() {
    if (turretLimit.getVoltage() > 2 && !direction) {
      leftLimit = true;
    } else if (turretLimit.getVoltage() > 2 && direction) {
      rightLimit = true;
    } else {
      leftLimit = false;
      rightLimit = false;
    }
  }

  /**
   * Overrides the align command by setting the value of aligned to true
   */
  public void StopAlignCommand() {
    aligned = true;
  }
}
