// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Climbing ID's \\
    public static final int kClimbMotorID = 0; //TODO
    public static final int kClimbLockID = 12;
    public static final int kClimbLimitID = 0; //TODO

    // Color Wheel ID's\\
    public static final int kColorMotorID = 0; //TODO
    
    // Conveyor ID's \\
    public static final int kTopConveyorMotorID = 0; //TODO
    public static final int kBottomConveyorMotorID = 0; //TODO
    public static final int kTopLightSensorID = 0; //TODO
    public static final int kBottomLightSensorID = 0; //TODO

    // Drivetrain ID's \\
    public static final int kLeftFrontID = 0; //TODO
    public static final int kLeftRearID = 0; //TODO
    public static final int kRightFrontID = 0; //TODO
    public static final int kRightRearID = 0; //TODO
    public static final int kMXP = 0; //TODO
    public static final int kDrivetrainPGain = 0; //TODO
    public static final int kDrivetrianIGain = 0; //TODO
    public static final int kDrivetrainDGain = 0; //TODO

    // Intake ID's \\
    public static final int kIntakeMotorID = 0; //TODO
    public static final int kIntakeDropMotorID = 0; //TODO

    // Shooter ID's \\
    public static final int kTopShooterMotorID = 0; //TODO
    public static final int kBottomShooterMotorID = 0; //TODO

    // Turret ID's \\
    public static final int kTurretMotorID = 0; //TODO
    public static final int kTurretLimitID = 0; //TODO

    // Xbox Controller Variables \\
    public final static int JoystickPortID = 1;
    public final static int xboxLeftJoystickHorizontal = 0;
    public final static int xboxLeftJoystickVertical = 1;
    public final static int xboxLeftTrigger = 2;
    public final static int xboxRightTrigger = 3;
    public final static int xboxRightJoystickHorizontal = 4;
    public final static int xboxRightJoystickVertical = 5;
    public final static int xboxButtonA = 1;
    public final static int xboxButtonB = 2;
    public final static int xboxButtonX = 3;
    public final static int xboxButtonY = 4;
    public final static int xboxLeftBumper = 5;
    public final static int xboxRightBumper = 6;
    public final static int xboxButtonBack = 7;
    public final static int xboxButtonStart = 8;
    public final static int xboxLeftJoystickButton = 9;
    public final static int xboxRightJoystickButton = 10;

}
