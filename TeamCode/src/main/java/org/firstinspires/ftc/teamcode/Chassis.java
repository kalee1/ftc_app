package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.ForwardingDiagnosticFormatter;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Error 404: Team Name Not Found
 */

public class Chassis
{
    enum TankDirection {FORWARD, REVERSE}

    boolean pressed = false;
    boolean moving = false;
    double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    Telemetry telemetry;


    // internal time tracking
    private long startTime = 0; // in nanoseconds


    public void init(HardwareMap hwMap, Telemetry telem)
    {
        telemetry = telem;
        moving = false;
        startTime = 0;
    }

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY, double powerLimit)
    {
    }

    public boolean drive(double power, double direction, double gain, double distance, double time)
    {
        return moving;
    }

    public boolean tankDrive(double power, TankDirection irection, double gain, double distance, double time)
    {
        return moving;
    }

    public boolean pointTurn(double power, double targetHeading, double time)
    {
        return moving;
    }

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
     * Reset the start time to zero.
     */
    public void resetStartTime()
    {
        startTime = System.nanoTime();
    }

    /**
     * Converts inches to encoder ticks
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

}
