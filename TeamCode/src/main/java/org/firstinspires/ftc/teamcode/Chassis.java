package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.ForwardingDiagnosticFormatter;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Error 404: Team Name Not Found
 */

public class Chassis
{
    boolean moving = false;

    // internal time tracking
    private long startTime = 0; // in nanoseconds

    public void init(HardwareMap hwMap)
    {
    }

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY)
    {
    }

    public boolean drive(double power, double direction,  double distance, double time)
    {
        return !moving;
    }

    public void pointTurn(double power, double heading)
    {

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
        final double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
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
     * Converts encoder inches to encoder ticks
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
