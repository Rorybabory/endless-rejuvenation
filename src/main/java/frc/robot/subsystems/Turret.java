/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.TurretConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private CANSparkMax shooterL;
  private CANSparkMax shooterR;
  private SpeedController turretAngleL, turretAngleR, susan;
  private SpeedControllerGroup shooter;
  private DigitalOutput turret;
  AnalogEncoder encoder = new AnalogEncoder(new AnalogInput(3));
  public Turret() {
    shooterL = new CANSparkMax(TurretConstants.SHOOTER_CAN_LEFT, MotorType.kBrushless);
    shooterR = new CANSparkMax(TurretConstants.SHOOTER_CAN_RIGHT, MotorType.kBrushless);
    encoder.reset();
    turret = new DigitalOutput(5);
  }

  public void shoot(){
    // shooter.set(1);
    if (shooterL == null) {
      System.out.println("LEFT SHOOTER IS NULL");
    }else {
      shooterL.set(0.75);
      shooterR.set(-0.75);
    }
  }

  public void stop(){
    if (shooterL == null) {
      System.out.println("LEFT SHOOTER IS NULL");
    }else {
      shooterL.set(0);
      shooterR.set(0);  
    }
  }
  public double getEncoderValue() {
    System.out.println("ENCODERVALUE: " + encoder.get());
    SmartDashboard.putNumber("ENCODER VALUE", encoder.get());
    return encoder.get();
  }
  public void resetTurret() {
    encoder.reset();
  }
  public void moveAtSpeed(double speed) {
    turret.updateDutyCycle((speed*0.5+1.5)/4);
  }
  public void stopMovingTurret() {
    turret.updateDutyCycle(0.0);
  }
 public int angle() {
   turret.updateDutyCycle((0.25*0.5+1.5)/4);
   return 0;
 }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
