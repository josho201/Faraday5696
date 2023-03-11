// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    
    public static final int DriverControllerPort = 0;
    public static final int ArmControllerPort = 1;
    
  }
  public static class DriveConstants{

    
    public static final int CANid_chassis_l = 1;
    public static final int CANid_chassis_2 = 2;
    public static final int CANid_chassis_3 = 3;
    public static final int CANid_chassis_4 = 4;
    public static final double Revoluionspermeter = 10.75/((15.25*Math.PI)/100);

    // el robot characterization dara valores mas exactos, NO USAR ESTOS VALORES .22,1.98,.2
    public static final double ksVolts = 0.1781;
    public static final double kvVoltsSecondPerMeter = 1.3494; 
    public static final double kaVoltSecondsSquaredperMeter = 0.30011;
    public static final double kPdriveSpeed = 1.884;
    public static final DifferentialDriveKinematics driveKmtics = 
      new DifferentialDriveKinematics(.55);// robot track width (meters)

    
  }

  public static class AutoConst{
    public static final double kPauto = 0.5;
    public static final double kIauto = 0;
    public static final double kDauto = 0;



  }     
  public static class ArmConstans{
    public static final int[] EVOcanIDs = new int[]{5,6};
    public static final int[] channels_encoder_evo = new int[]{};
    public static final int[] solenoidPorts = new int[]{0,7};
    public static final double elevatorIddle = -.438 ;
    public static final int elevadorCANid = 7;
    public static final int[] encoderElevador = new int[]{9,8};
    public static final double kP = 1.3;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double iddle = 0.01;


    public static final double ekP = 1.4;
    public static final double ekI = 0;
    public static final double ekD = 0;
    public static final double relacionElevador = 1;  
  }
}
