package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Defines the robot. Has class objects for each mechanism in use on the robot and contains the all
 * the methods used by the robot (sourced from each individual mechanism class.
 *
 * @author Andrew, Error 404: Team Name Not Found
 */
public class RuckusBot
{
    /** An instance of the Chassis class. Links to the drive motors and the code relating to
     * driving in teleop and autonomous. */
    Chassis theChassis =  null;
    /** An instance of the MarkDeploy class. Links to the mechanism and the code that deploys the
     * team marker in autonomous. */
    MarkDeploy depoDeposit = new MarkDeploy();
    /** In instance of the FieldVision class. Links to the code that locates the gold minerals on
     * the sampling field. */
    FieldVision theEyeOfSauron = new FieldVision();

    /** An instance of the MotorArm class. Links to the motors and the code relating to the
     * operation of the mineral arm in teleop. */
    MotorArm theArm = new MotorArm();
    /** An instance of the Collector class. Links to the motor and code relating to sucking minerals
     * into the mineral basket on the end of the mineral arm. */
    Collector theCollect = new Collector();

    /** An instance of the Collector class. Links to the motor and code relating to sucking minerals
     * into the mineral basket on the end of the mineral arm. */
    Gen2_Hang theHang = new Gen2_Hang();

    /** The truth value that is whether or not to initialize the camera in init. */
    boolean theUseCamera = false;

    /** Determines which chassis type to use: Mecanum or Tank.
     *
     * @param chassisType  A string that is the type of chassis the code will use.
     * */
    public RuckusBot(String chassisType)
    {
        if (chassisType.equals("MecanumChassis"))
        {
            theChassis = new MecanumChassis();
        }
        else if (chassisType.equals("TankChassis"))
        {
            theChassis = new TankChassis();
        }
    }

    /** Triggers the initialization of the selected classes.
     *
     * @param hwMap  An instance of the FIRST-provided HardwareMap which is passed onto more
     *               specific classes for initilizng hardware.
     * @param telem  An instance of Telemetry which allows the use of Telemtry in this class.
     * */
    public void init(HardwareMap hwMap, Telemetry telem, boolean useCamera)
    {
        theUseCamera = useCamera;
        theChassis.init(hwMap, telem);
        if(useCamera)
        {
            theEyeOfSauron.init(hwMap, telem);
        }

        theHang.init(hwMap, telem);
        theArm.init(hwMap, telem);
        theCollect.init(hwMap, telem );
        depoDeposit.init(hwMap, telem);
    }

    /** Runs once when start is hit: starts the camera and the hang mechanism. */
    public void start()
    {
        if(theUseCamera)
        {
            theEyeOfSauron.start();

        }
        theHang.start();
    }

    /** A go-between between the opmode class and the actual chassis class.
     * Drives the mineral arm using joystick inputs.
     *
     * @param RightStickY  The y-axis of the right stick on the gamepad. Controls the elbow joint.
     * @param LeftStickY  The y-axis of the left stick on the gamepad. Controls the shoulder joint.
     * */
    public void armDrive(double RightStickY, double LeftStickY)
    {
        theArm.armDrive(RightStickY, LeftStickY);
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the collector wheels inward to suck in minerals.
     * */
    public void intake()
    {
        theCollect.intake();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the collector wheels outward to eject minerals.
     * */
    public void eject()
    {
        theCollect.eject();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Tells the collector wheels to stop turning.
     * */
    public void collectorStop()
    {
        theCollect.stop();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the left collector wheel inward to suck in minerals.
     * */
    public void intakeL()
    {
        theCollect.intakeL();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the left collector wheel outward to eject minerals.
     * */
    public void ejectL()
    {
        theCollect.ejectL();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Tells the left collector wheel to stop turning.
     * */
    public void collectorStopL()
    {
        theCollect.stopL();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the right collector wheel inward to suck in minerals.
     * */
    public void intakeR()
    {
        theCollect.intakeR();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the right collector wheel outward to eject minerals.
     * */
    public void ejectR()
    {
        theCollect.ejectR();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Tells the right collector wheel to stop turning.
     * */
    public void collectorStopR()
    {
        theCollect.stopR();
    }

    /** A go-between between the opmode class and the actual chassis class.
     * The primary drive method in use by 404. All other drive methods call this one. JoystickDrive
     * uses mecanum calculations to interpret joystick inputs and give directional power to the
     * motors that allows for omni-directional driving.
     *
     * @param leftStickX  The x-axis on the left stick of the gamepad. Controls the robot's strafing.
     * @param leftStickY  The y-axis on the left stick of the gamepad. Controls the forward and
     *                    backward motions on the robot.
     * @param rightStickX  The x-axis on the right stick of the gamepad. N/A
     * @param rightStickY  The y-axis on the right stick of the gamepad. Controlls the robot's turning.
     * */
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX,
                              double rightStickY, double powerLimit)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY, powerLimit);
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, can drive the robot in any direction for a set distance in inches while
     * using the gyro to preserve the robot's initial orientation and having a timout feature that
     * makes sure the algorithm doesn't get stuck.
     *
     * @param power  The power at which the robot will drive.
     * @param direction  The direction in which the robot will drive (there are eight possible
     *                   directions: forward, backwards, side to side, and 45-degree angle in any
     *                   direction).
     * @param gain  The amount the algorithm will correct error by.
     * @param distance  The distance in inches the robot will drive (uses either the front right of
     *                  front left motor to measure distance, depending on which direction the robot
     *                  is driving).
     * @param time  The maximum time the move can take before the algorithm terminates the move and
     *              continues to the next command (prevents the software from freezing up and
     *              ruining the run).
     * */
    public boolean drive(double power, double direction, double gain, double distance, double time)
    {
        return theChassis.drive(power, direction, gain, distance, time);
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, drives the robot forward and backward only and uses the gyro to preserve
     * an absolute heading captured at the beginning of the match.
     *
     * @param power  The power at which the robot will drive.
     * @param direction  The direction the robot wil drive at (an enum with two choices: forward
     *                   and backward).
     * @param gain  The amount the algorithm will correct error by.
     * @param distance  The distance in inches the robot will drive.
     * @param time  The maximum time the move can take before the algorithm terminates the move and
     *              continues to the next command (prevents the software from freezing up and
     *              ruining the run).
     * */
    public boolean tankDrive(double power, Chassis.TankDirection direction, double gain,
                             double distance, double time)
    {
        return theChassis.tankDrive(power, direction, gain, distance, time);
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, turns the robot to a target heading. The algorithm finds the shortest
     *      direction to the target heading and turns that direction.
     *
     * @param power  The power at which the robot will turn.
     * @param targetHeading  The heading the robot wil turn to.
     * @param time  The maximum time the move can take before the algorithm terminates the move and
     *              continues to the next command (prevents the software from freezing up and
     *              ruining the run).
     */
    public boolean pointTurn(double power, double targetHeading, double time)
    {
        return theChassis.pointTurn(power, targetHeading, time);
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Sets all motors to zero-power.
     * */
    public void stopMotors()
    {
        theChassis.stopMotors();
        theArm.stop();
        theHang.stopHangMotor();
    }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, deposits the team marker off the side of the robot.
     * */
    public void markDeploy(double power)
    {
        depoDeposit.markDeploy(power);
    }

    public String goldPosition()
    {
        return theEyeOfSauron.tensorFlowMineralDetection();
    }

    /** Shuts down the camera and tensorflow algorithm */
    public void tfodShutdown()
    {
        theEyeOfSauron.tfodShutdown();
    }

//    public boolean armExtend()
//    {
//        return theArm.armExtend();
//    }

//    public void landerHang() { theHang.hangerHang();}
//
//    public void landerPrep() { theHang.hangerDeploy(); }
//
//    public void hangerHome() { theHang.hangHome(); }

    /** Used to move the hanger up and down with d-pad controls.
     *
     * @param down  A boolean that is whether or not to move up.
     * @param up  A boolean that is whether or not to move down.
     * @param power  The power that the hanger will move at.
     * */
    public void hangControl(boolean down, boolean up, double power)
    {
        theHang.hangControl(down, up, power);
    }

    /** Drives the hanger a set distance.
     * @param power  The power that the hanger will move at.
     * @param distance  The distance the hanger will move.
     * @param direction  The direction the hanger will move in (two options: IN or OUT).
     * */
    public boolean hangDrive(double power, double distance, Gen2_Hang.HangDirection direction)
    {
        return theHang.hangDrive(power, distance, direction);
    }

    /** Drives the arm to the home position where it is fully stowed.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
    public boolean armHome()
    {
        return theArm.armHome();
    }

    
    public boolean armGoldCollect(boolean elbowSecond)
    {
        return theArm.goldCollect(elbowSecond);
    }

    /** Drives the arm to the position where it is fully extended into the crater
     * in preparation for collecting minerals.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
    public boolean armCraterExtend()
    {
        return theArm.craterExtend();
    }

    /** Drives the arm to the position where it is where it is partially extended to make it
     *  easier to drive around between the crater and the lander.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
    public boolean armDrivingExtend(){ return theArm.drivingExtend();}

    /** Drives the arm to the position where it is up next to the lander in preparation
     *  for putting minerals into the lander cargo hold.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
    public boolean armLanderExtend(){ return theArm.landerExtend();}

    /** Drives the arm to the position specified by the parameter.
     *
     * @param position  The target position to drive the arm to.
     * @param elbPower  The power to drive the elbow at.
     * @param shouldPower  The power to drive the shoulder at.
     * */
    public boolean goTo(MotorArm.ArmPositions position, double elbPower, double shouldPower)
    {
        return theArm.goTo(position, elbPower, shouldPower);
    }

    /** Turns the robot back to the initial heading it started out at.  Useful for correcting
     *  heading errors introduced when landing in autonomous.
     *
     * @param power  The power at which the robot will drive.
     * @param time  The maximum amount of time the move can take before timing out.
     * @return A boolean that is whether or not the robot is moving.
     * */
    public boolean reset(double power, double time)
    {
        return theChassis.reset(power, time);
    }
    /** Determines whether or not the robot is stuck on the lander using the pitch of the robot.
     * @return A boolean that is whether or not the robot is stuck.
     * */
    public boolean goodPitch()
    {
        return theChassis.goodPitch();
    }

    /** Turns the chassis to the original heading caputred during init.*/
    public double getResetHeading()
    {
        return ((MecanumChassis)theChassis).resetHeading;
    }

    public double getHeadingDbl()
    {
        return ((MecanumChassis)theChassis).getHeadingDbl();
    }



}
