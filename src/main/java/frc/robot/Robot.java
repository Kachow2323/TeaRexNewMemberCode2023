// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  //For our simple drive base code we can start declaring stuff in the Robot.java file BUT
  // Most of the time we want to do this either in seperate "Drive" subsystem or at in the Container
  // Start by declaring your drive motors (Motors varies)
  // TeaRex 2023 uses the Talon Fx with a coupler on both sides for a total of 4 motors
  // Note: There are 2 motors per side.

  private TalonFX rightFrontDriving;
  private TalonFX rightBackDriving;
  private TalonFX leftFrontDriving;
  private TalonFX leftBackDriving;

  // These above-mentioned motor types are primarily used for Drive
  //However, smaller versatile motors may come in handy
  // Ex: NEO 550 (small) or NEO 650 (big)

  private CANSparkMax armTurning;
  private CANSparkMax intakeMotor;
  // NEO's require SPARK Max's for comms so declare SM's instead of NEOS

  //To control the robot, we use Xbox Controllers.
  //The controls are already mapped for us and all we have to do is import

  private XboxController driverController; //often called player1 by Ryan Chan :D
  // Note Driver must be Port 0 while Op is Port 1

  //In the future there may also be sensors so here is an example.

  private DigitalInput sensorIntake; //example only

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    // So this were the electronics portion happens
    // Confirm ID numbers for identification
    // Notice how this is similiar to Scanner?!?!
    rightFrontDriving = new TalonFX(4);
    rightBackDriving = new TalonFX(2);
    leftFrontDriving = new TalonFX(3);
    leftBackDriving = new TalonFX(1);
    armTurning = new CANSparkMax(1, MotorType.kBrushless);
    intakeMotor = new CANSparkMax(3, MotorType.kBrushless);
    sensorIntake = new DigitalInput(9);
    driverController = new XboxController(0);

    // Around this time you may encounter some undefined class erros
    //Hover over the error and go QUICK FIX
    // If an import appears, import it and see if the error goes away
    // If not, contact a lead :(
    
    leftBackDriving.follow(leftFrontDriving); 
    // The back motor should be slaved to the front master mtor.
    rightBackDriving.follow(rightFrontDriving);
    // repeat on right side

    // Since the Motors are facing the wrong direction, we should invert the motors in order for them to face the correc directions
    leftFrontDriving.setInverted(true);
    leftBackDriving.setInverted(true); 

    //Motors spin so we should prevent the motors from free-rolling everywhere
    // When 0 current is flowing through, it will be set on a lock mode
    rightFrontDriving.setNeutralMode(NeutralMode.Brake);
    rightBackDriving.setNeutralMode(NeutralMode.Brake);
    leftFrontDriving.setNeutralMode(NeutralMode.Brake);
    leftBackDriving.setNeutralMode(NeutralMode.Brake);

  } 

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // Arcade drive is drive style that allows you to drive forward with one joy while turning with a different one
    // We will use left joystick's Y for going forward and backward
    // & use the right joystick's X for turning controls
    double leftWheelsControlArcade = driverController.getLeftY();
    double rightWheelsControlArcade = driverController.getRightX();
    double left = leftWheelsControlArcade + rightWheelsControlArcade ;
    double right = leftWheelsControlArcade - rightWheelsControlArcade;

    //Setting hard limits on controls so the maximum power the motors can receive is 1
    if(left > 1.0){
      left = 1.0; //sets a hard limit since it will go over 1
    }else if(right > 1.0){
      right = 1.0; // same, sets a hard limit
    }else if(left < -1.0){
      left = -1.0; // same, sets a hard limit
    }else if(right < -1.0){
      right = -1.0; // same, sets a hard limit
    }

    rightFrontDriving.set(ControlMode.PercentOutput, right);
    leftFrontDriving.set(ControlMode.PercentOutput, left);

    // Smart Dashboard is a Dashboard that displays values you want in real time
    SmartDashboard.putNumber("Right Wheel Speed", rightWheelsControlArcade);
    SmartDashboard.putNumber("Left Wheel Speed", leftWheelsControlArcade);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
