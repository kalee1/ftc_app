package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.ForwardingDiagnosticFormatter;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

/**
 * The basic chassis class that has all of the general objects in it used by all chassis types.
 * Currently has two child classes or chassis types: TankChassis and MecanumChassis.
 *
 * @author Andrew, Error 404: Team Name Not Found
 */

public class Chassis
{
    /** An enum that us used by the tankDrive method. Has two options: FORWARD and REVERSE. */
    enum TankDirection {FORWARD, REVERSE}

    boolean pressed = false;
    /** A boolean that tells whether or not the robot is moving. */
    boolean moving = false;
    /** A double that is the number of nanoseconds per second. */
    double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    /** An instance of Telemetry. */
    Telemetry telemetry;
    // internal time tracking
    /** A long variable that is the internal start time. */
    private long startTime = 0; // in nanoseconds

    /** Initializes the hardware and objects needed for this class. */
    public void init(HardwareMap hwMap, Telemetry telem)
    {
        telemetry = telem;
        moving = false;
        startTime = 0;
    }

    /** Uses joystick inputs the drive the robot. Allows for omni-directional movement and has a
     * selectable max power.
     *
     * @param leftStickX  The x-axis of the left joystick on the primary gamepad. Controls side to
     *                    side movement.
     * @param leftStickY  The y-axis of the left joystick on the primary gamepad. Controls forward
     *                    and backward movement.
     * @param rightStickX  The x-axis of the right joystick on the primary gamepad. Controls rotation.
     * @param rightStickY  The y-axis of the right joystick on the primary gamepad. N/A
     * @param powerLimit  The maximum power value.
     * */
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY, double powerLimit)
    {
    }

    /** Drive the robot with like a mecanum robot. Uses the gyro to preserve the orientation the
     * robot was at at the beginning of the move.
     *
     * @return  a boolean that tells whether or not the robot is currently moving.
     * */
    public boolean drive(double power, double direction, double gain, double distance, double time)
    {
        return moving;
    }

    /** Drives the robot like a tank. Uses the gyro to drive at an absolute zero (captured at the
     * beginning of the program.
     * <p>
     * Has to possible directions: forward and backward.
     *
     * @return  a boolean that tells whether or not the robot is currently moving.
     * */
    public boolean tankDrive(double power, TankDirection direction, double gain, double distance, double time)
    {
        return moving;
    }

    /** Turns the robot to a target heading. Algorithm finds the shortest direction to take to the
     * target heading.
     *
     * @return  a boolean that tells whether or not the robot is currently moving.*/
    public boolean pointTurn(double power, double targetHeading, double time)
    {
        return moving;
    }

    /** Stop drive motors */
    public void stopMotors()
    {
    }

    /**
     * Get the number of seconds this op mode has been running
     * <p>
     * This method has sub millisecond accuracy.
     * @return number of seconds this op mode has been running
     */
    public double getRuntime()
    {
        return (System.nanoTime() - startTime) / NANOSECONDS_PER_SECOND;
    }

    /**
     * Reset the internal timer to zero.
     */
    public void resetStartTime()
    {
        startTime = System.nanoTime();
    }

    /**
     * Converts inches to encoder ticks.
     *
     * @param distanceInch  A double that is the number of inches to convert to encoder ticks
     */
    public double inchesToTicks(double distanceInch)
    {
        //  Child classes should override this if it needs to be different.
        double COUNTS_PER_MOTOR_REV = 600;      // eg: TETRIX Motor Encoder
        double DRIVE_GEAR_REDUCTION = 1.0;      // This is < 1.0 if geared UP
        double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

        double distanceTicks = distanceInch * COUNTS_PER_INCH;
        return distanceTicks;
    }

    public boolean reset (double power, double time)
    {
        return moving;
    }

}
