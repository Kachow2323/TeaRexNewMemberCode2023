# TeaRexNewMemberCode2023
Training Docs for New Members 2023
Includes Kit Bot 6 Wheel Tank & Arcade Drive - 4 Talon Fx's
- Pre-requisites: WPILIB installed on VSCode
- Generate New Project
- Template (Command Robot), Name it, Choose Folder, 253, Generate Project, Build
- Correct Vendordeps installed: REV, Kauai Labs, CTRE Phoenix

  WARNING: NEVER RUN CODE ON THE ROBOT WITHOUT SUPERVISION. IN A WORST CASE SCENARIO, YOU GRIND GEARS. BREAKING THE MOTORS FOREVER.

Example only uses the Robot.java file for declaring, initializing, and programming the motors. Includes example code for NON-functioning SparkMaxs and Digitial Input Sensor.
Robot.java is the only real file we will touch but in the future, we will use other files such as the Container.java file and creating new files called "Subsystems". The main subsys being for Drive.

List of knowns:
Control the Motors which are coupled together in order (groups of 2) for the them to function in six-wheel drive system. Since 2 of the 4 motors are facing the wrong direction, we need to flip them. We need to write code for the X-box controller so we have a platform to control the robot from. 

To Do: 
Start by Declaring all the motors, speed controllers, and other controllers (X-box controller)
Note: 4 motors = 4 different objects to be declared.
Initiate Motors with their proper ID #. Numbers are labeled on the motors physically. Can also be retrieved thru wire connection and Phoenix Tuner
Initiate Controller (X-Box) with correct port 3. Port 0 = Drive Port 1 = Operator
Slave the Left Back and Right Back Motor to the Front. Thus we can send 1 signal to either left or right side without having to specify each and every motor.



TBD: Auton routines
