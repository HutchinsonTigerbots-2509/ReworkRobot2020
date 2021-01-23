// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * Climbing Class for the climber mechanism on the robot.
 * <p> Constructor: {@link #Climb()}
 */
public class Climb extends SubsystemBase {

  //#region Variable Declarations

  // Variables for Climb Subsystem \\
  private WPI_TalonSRX climbMotor = Robot.climbMotor;
  private WPI_TalonSRX climbLock = Robot.climbLock;

  private AnalogInput climbLimit = Robot.climbLimit;

  //#endregion
  
  /** 
   * Creates a new Climb object.
   */
  public Climb() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Extends the climbing system
   */
  public void ClimbExtend() {
    climbMotor.set(-1);
  }

  /**
   * Retracts the climbing system
   */
  public void ClimbRetract() {
    if (!getClimbLimit()) {
      climbMotor.set(1);
    } else {
      climbMotor.set(0);
    }
  }

  /**
   * Stops the climber from moving
   */
  public void StopClimb() {
    climbMotor.set(0);
  }

  /**
   * Locks the climber motor
   */
  public void ClimbLock() {
    climbLock.set(1); 
  }

  /**
   * Stops the locking mechanism for climbing
   */
  public void StopLock() {
    climbLock.set(0);
  }

  /**
   * Returns whether or not the climb limit has been activated
   * @return true/false
   */
  public boolean getClimbLimit() {
    if (climbLimit.getVoltage() > 0.17) {
      return true;
    } else {
      return false;
    }
  }
}
