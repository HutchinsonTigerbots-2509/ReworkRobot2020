// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Intake Class for the intake mechanism on the robot.
 * <p> Constructor: {@link #Intake()}
 */
public class Intake extends SubsystemBase {

  //#region Variable Declaration

  // Variables for Intake Subsystem \\
  private WPI_VictorSPX intakeMotor = Robot.intakeMotor;
  private WPI_TalonSRX intakeDropMotor = Robot.intakeDropMotor;

  private int dropCounter = 0;
  private int liftCounter = 0;

  //#endregion
  
  /**
   * Creates a new Intake object
   */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxLeftTrigger) > 0.1) {
      if (dropCounter < 50) {
        DropIntake();
        dropCounter++;
        liftCounter = 0;
      }
      IntakeOut();
    } else if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxRightTrigger) > 0.1) {
      if (liftCounter < 50) {
        DropIntake();
        dropCounter++;
        liftCounter = 0;
      }
      IntakeIn();
    } else {
      if (liftCounter < 150) {
        LiftIntake();
        liftCounter++;
        dropCounter = 0;
      }
      StopIntakeMotor();
    }

    if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxLeftJoystickVertical) > 0.2) {
      DropIntake();
    } else if (Robot.robotContainer.CoOpStick.getRawAxis(Constants.xboxLeftJoystickVertical) < -0.2) {
      LiftIntake();
    } else if (liftCounter >= 150 || dropCounter >= 50) {
      StopDropIntake();
    }
  }

  /**
   * Drops the intake mechanism to lie horizontally
   */
  public void DropIntake() {
    intakeDropMotor.set(1);
  }

  /**
   * Lifts the intake mechanism to verticall
   */
  public void LiftIntake() {
    intakeDropMotor.set(-1);
  }

  /**
   * Stops the motor that controllers the dropping of the intake
   */
  public void StopDropIntake() {
    intakeMotor.set(0.8);
  }

  /**
   * Starts spinning the intake wheels to accept power cells
   */
  public void IntakeIn() {
    intakeMotor.set(0.8);
  }

  /**
   * Starts spinning the intake wheels to spit out power cells
   */
  public void IntakeOut() {
    intakeMotor.set(-0.8);
  }

  /**
   * Stops the intake motors
   */
  public void StopIntakeMotor() {
    intakeMotor.set(0);
  }
}
