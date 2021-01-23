// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Shooter Class for the shooter mechanism on the robot.
 * <p> Constructor: {@link #Climb()}
 */
public class Shooter extends SubsystemBase {

  //#region Variable Declaration

  // Variables for shooter subsystem \\
  private WPI_TalonSRX topShooterMotor = Robot.topShooterMotor;
  private WPI_TalonSRX bottomShooterMotor = Robot.bottomShooterMotor;

  //#endregion
  
  /**
   * Creates a new Shooter object.
   */
  public Shooter() {
    topShooterMotor.setNeutralMode(NeutralMode.Coast);
    bottomShooterMotor.setNeutralMode(NeutralMode.Coast);

    topShooterMotor.config_kP(0, Constants.kShooterPGain);
    topShooterMotor.config_kI(0, Constants.kShooterIGain);
    topShooterMotor.config_kD(0, Constants.kShooterDGain);

    bottomShooterMotor.config_kP(0, Constants.kShooterPGain);
    bottomShooterMotor.config_kI(0, Constants.kShooterIGain);
    bottomShooterMotor.config_kD(0, Constants.kShooterDGain);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("RMP", GetRPM());
  }

  /**
   * Returns the velocity of the bottom shooter in RMP's
   * @return
   */
  public double GetRPM() {
    return Math.abs((bottomShooterMotor.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
  }

  /**
   * Moves the shooter forward (0, 1]
   * @param topSpeed
   * @param bottomSpeed
   */
  public void ShooterForward(double topSpeed, double bottomSpeed) {
    topShooterMotor.set(topSpeed);
    bottomShooterMotor.set(bottomSpeed);
  }

  /**
   * Reverses the shooter motor
   */
  public void ShooterReverse() {
    ShooterForward(-1, 1);
  }

  /**
   * Stops the shooter motors
   */
  public void StopShooter() {
    ShooterForward(0, 0);
  }

  /**
   * Sets the shooter speed based on a given RPM
   * @param RPM
   */
  public void SetShooterVelocity(int RPM) {
    topShooterMotor.set(ControlMode.Velocity, -(RPM * Constants.kShooterTicksPerRotation) / 600);
    bottomShooterMotor.set(ControlMode.Velocity, -(RPM * Constants.kShooterTicksPerRotation) / 600);
  }
}
