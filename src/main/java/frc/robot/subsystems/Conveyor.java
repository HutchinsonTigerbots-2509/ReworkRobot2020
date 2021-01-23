// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * Conveyor Class for the conveyor mechanism on the robot.
 * <p> Constructor: {@link #Conveyor()}
 */
public class Conveyor extends SubsystemBase {

  //#region Variable Declaration

  // Variables for colorwheel subsystem \\
  private WPI_VictorSPX topConveyorMotor = Robot.topConveyorMotor;
  private WPI_VictorSPX bottomConveyorMotor = Robot.bottomConveyorMotor;

  private AnalogInput topLightSensor = Robot.topLightSensor;
  private AnalogInput bottomLightSensor = Robot.bottomLightSensor;

  private boolean canSensorMove = true;

  //#endregion
  
  /**
   * Creates a new conveyor object.
   */
  public Conveyor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Sensor Move", canSensorMove);
    GetTopSensorValue();
    GetBottomSensorValue();

    if (canSensorMove) {
      if (!GetTopSensorValue() && GetBottomSensorValue()) {
        BottomConveyorForward(0.8);
      } else if (GetBottomSensorValue()) {
        BottomConveyorForward(0.8);
      } else {
        StopMotors();
      }
    }
  }

  /**
   * Gets the bottom sensor value and places it on the smart
   * dashboard and returns that value.
   * @return true/false
   */
  public boolean GetBottomSensorValue() {
    if (bottomLightSensor.getVoltage() < 0.5) {
      SmartDashboard.putBoolean("Bottom Sensor", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Bottom Sensor", false);
      return false;
    }
  }

  /**
   * Gets the top sensor value and places it on the smart
   * dashboard and returns that value.
   * @return true/false
   */
  public boolean GetTopSensorValue() {
    if (topLightSensor.getVoltage() < 0.15) {
      SmartDashboard.putBoolean("Top Sensor", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Top Sensor", false);
      return false;
    }
  }

  /**
   * Moves only the bottom conveyor portion at a designated speed.
   * @param speed
   */
  public void BottomConveyorForward (double speed) {
    bottomConveyorMotor.set(speed);
  }

  /**
   * Moves both the top and bottom conveyors at set speeds
   * @param topSpeed
   * @param bottomSpeed
   */
  public void FullConveyorMove(double topSpeed, double bottomSpeed) {
    // Robot.robotContainer.turret.StopAlignCommand(); //TODO
    topConveyorMotor.set(topSpeed);
    bottomConveyorMotor.set(bottomSpeed);
  }

  /**
   * Reverses the conveyor motors at full speed
   */
  public void ConveyorReverse() {
    FullConveyorMove(-1, -1);
  }

  /**
   * Stops the conveyor motors
   */
  public void StopMotors() {
    FullConveyorMove(0, 0);
  }

  /**
   * Toggles the value of canSensorMove
   */
  public void ToggleSensor() {
    canSensorMove = !canSensorMove;
  }
}
