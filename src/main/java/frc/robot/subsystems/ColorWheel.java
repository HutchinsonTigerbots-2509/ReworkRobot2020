// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * Colorwheel Class for the colorwheel mechanism on the robot.
 * <p> Constructor: {@link #ColorWheel()}
 */
public class ColorWheel extends SubsystemBase {

  //#region Variable Declaraion

  // Variables for Colorwheel Subsystem \\
  private WPI_VictorSPX colorWheelMotor = Robot.colorWheelMotor;

  private double speed = 0.5;
  private String gameData;

  //#endregion

  /**
   * Creates a new Colorwheel object.
   */
  public ColorWheel() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  /**
   * Moves the colorwheel motor forward
   */
  public void ColorWheelForward() {
    colorWheelMotor.set(speed);
  }

  /**
   * Moves the colorwheel motor backwards
   */
  public void ColorWheelReverse() {
    colorWheelMotor.set(-speed);
  }

  /**
   * Stops the colorwheel motor
   */
  public void StopColorWheel() {
    colorWheelMotor.set(0);
  }

  /**
   * Retrieves the game data from the driverstation.
   * Used to find the color we need to set the wheel to
   */
  public void GetGameData() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B' :
          SmartDashboard.putString("Color", "BLUE");
          break;
        case 'G' :
          SmartDashboard.putString("Color", "GREEN");
          break;
        case 'R' :
          SmartDashboard.putString("Color", "RED");
          break;
        case 'Y' :
          SmartDashboard.putString("Color", "YELLOW");
          break;
        default :
          SmartDashboard.putString("Color", "ERROR");
          break;
      }
    }
  }
}
