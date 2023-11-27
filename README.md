# TeaRexNewMemberCode2023
Training Docs for New Members 2023
Includes Kit Bot 6 Wheel Tank & Arcade Drive - 4 Talon Fx's
- Pre-requisites: WPILIB installed on VSCode
- Generate New Project
- Template (Command Robot), Name it, Choose Folder, 253, Generate Project, Build
- Correct Vendordeps installed: REV, Kauai Labs, CTRE Phoenix

  **WARNING: NEVER RUN CODE ON THE ROBOT WITHOUT SUPERVISION. IN A WORST CASE SCENARIO, YOU GRIND GEARS. BREAKING THE MOTORS FOREVER.
**

  Example only uses the Robot.java file for declaring, initializing, and programming the motors. Includes example code for NON-functioning SparkMaxs and Digitial Input Sensor.
Robot.java is the only real file we will touch but in the future, we will use other files such as the Container.java file and creating new files called "Subsystems". The main subsys being for Drive.

List of knowns:
Control the Motors which are coupled together in order (groups of 2) for the them to function in six-wheel drive system. Since 2 of the 4 motors are facing the wrong direction, we need to flip them. We need to write code for the X-box controller so we have a platform to control the robot from. Arcade is different from Tank in the sense that Arcade is a different dribe STYLE. There are no mechanical changes, all the changes are based in coding. Pros

To Do: 
- Start by Declaring all the motors, speed controllers, and other controllers (X-box controller)
- Note: 4 motors = 4 different objects to be declared.
- **Initiate Motors with their proper ID #. Numbers are labeled on the motors physically. Can also be retrieved thru wire connection and Phoenix Tuner**
- Tip: Name these motors extremely specific with their physical positions. Do not say "motor 1,2,3..etc". Good examples include names such a "m_frontLeftDriveMotor" or "m_armTurningMotor".
- Initiate Controller (X-Box) with correct port 3.** Port 0 = Drive** Port 1 = Operator
- Slave the Left Back and Right Back Motor to the Front one **(master)**. This alows us to send 1 signal to either left or right side without having to specify each and every motor.
- The robot when unpowered can roll around, but we don't want that happening when its powered on, we can set it to "brake" mode using the Neutral Mode command.
- Motors can be a given a signal ranging between **1 thru -1** with all decimal values included. 1 being full forward, -1 being full reverse, 0 being neutral.
- Create a command to grab each Y position of the X-box controller. ex: **.getLeftY**
- Set the output porportinal to the Y pos of the X-box controller using the command above.

  **STOP** - CHECK IN WITH A PROGRAMMING LEAD OR MENTOR TO CHECK YOUR CODE!
  - Prelimenary Tests: Check that it builds correctly. Check that the Falcon ID's are correct! Critical!!!
 
 RUN YOUR CODE ON THE BOT!!!

//v1.0 - NewMem2023KitBotTank
  TBD: Auton routines
