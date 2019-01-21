package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
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
    FieldVision theEyeOfSauron = new FieldVision();
//    MineralProcessing area51 = new MineralProcessing();

    /** An instance of the MotorArm class. Links to the motors and the code relating to the
     * operation of the mineral arm in teleop. */
    MotorArm theArm = new MotorArm();
    /** An instance of the Collector class. Links to the motor and code relating to sucking minerals
     * into the mineral basket on the end of the mineral arm. */
    Collector theCollect = new Collector();

    /** Determines which chassis type to use: Mecanum or Tank.
     *
     * @param chassisType  A string that is the type of chassis the code will use.
     * */
    public RuckusBot(String chassisType) {
        if (chassisType.equals("MecanumChassis")) {
        theChassis = new MecanumChassis();
        }
        else if (chassisType.equals("TankChassis"))
            {
            theChassis = new TankChassis();
            }
    }

    /** Triggers the initilization of the selected classes.
     *
     * @param hwMap  An instance of the FIRST-provided HardwareMap which is passed onto more
     *               specific classes for initilizng hardware.
     * @param telem  An instance of Telemetry which allows the use of Telemtry in this class.
     * */
    public void init(HardwareMap hwMap, Telemetry telem)
        {
            theChassis.init(hwMap, telem);
            theEyeOfSauron.init(hwMap, telem);

            theArm.init(hwMap, telem);
            theCollect.init(hwMap, telem );
            depoDeposit.init(hwMap, telem);
        }

    public void start() {
        theEyeOfSauron.start();
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
     * Turns the collector wheels inward to suck in minerals.
     * */
    public void intake(Telemetry telem)
        {
            theCollect.intake(telem);
        }
    /** A go-between between the opmode class and the actual chassis class.
     * Turns the collector wheels outward to eject minerals.
     * */
    public void eject(Telemetry telem)
        {
            theCollect.eject(telem);
        }
    /** A go-between between the opmode class and the actual chassis class.
     * Tells the collector wheels to stop turning.
     * */
    public void collectorStop()
        {
            theCollect.stop();
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
        }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, deposits the team marker off the side of the robot.
     * */
    public void markDeploy()
        {
            depoDeposit.deploy();
        }
    /** A go-between between the opmode class and the actual chassis class.
     * Used in autonomous, spins the servo that deposits the team marker the other way.
     * */
    public void markRetract()
        {
            depoDeposit.retract();
        }

    public String goldPosition()
        {
            return theEyeOfSauron.tensorFlowMineralDetection();
        }

    public void tfodShutdown()
        {
            theEyeOfSauron.tfodShutdown();
        }

    public boolean armDeploy(int shoulderTarget, int elbowTarget, boolean elbowSecond)
        {
            return theArm.armDeploy(shoulderTarget, elbowTarget, elbowSecond);
        }

    public boolean armHome()
        {
            return theArm.armHome();
        }

    public boolean armExtend()
        {
            return theArm.armExtend();
        }


    }
