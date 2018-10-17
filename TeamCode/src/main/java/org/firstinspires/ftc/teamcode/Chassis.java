package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.ForwardingDiagnosticFormatter;

/**
 * Created by Andrew on 9/13/18.
 *
 */

public class Chassis
{

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY)
    {
    }


    public void drive(double distance, double power)
    {
    }

    public void stopMotors()
    {
    }

    public void pointTurn(double heading, double power)
    {

    }

    public void init(HardwareMap hwMap)
    {
    }



    /**
     * Converts encoder ticks to inches
     *
     * @param distanceInch  A double that is the number of inches to convert to encoder ticks
     * */
    public double ticksToInches(double distanceInch) {
        final double COUNTS_PER_MOTOR_REV = 600;    // eg: TETRIX Motor Encoder
        final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
        final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);

        int distanceTicks = -1;
        return distanceTicks;
    }

}
