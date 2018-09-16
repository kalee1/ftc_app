package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by andrew on 9/13/18.
 */

public class Tank extends Chassis
{

    private DcMotor rFront = null;
    private DcMotor lFront = null;

    public void stop()
    {
        rFront.setPower(0.0);
        lFront.setPower(0.0);
    }

    /**
     * Converts encoder ticks to inches
     *
     * @param distanceInch  A double that is the number of inches to convert to encoder ticks
     * */
    public double ticksToInches(double distanceInch)
    {
        static final double     COUNTS_PER_MOTOR_REV    = 600 ;    // eg: TETRIX Motor Encoder
        static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
        static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
        static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);

        int distanceTicks = -1;
        return distanceTicks;
    }

    /**
     * Drive forward at a specified power for a specified distance in inches
     *
     * @param distance  A double that is the distance in inches to drive
     * @param power  A double that is the power at which to drive
     * */
    public void forwardDrive(double distance, double power)
    {
        rFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rFront.setPower(power);
        lFront.setPower(power);

        ticksToInches(distance);
    }


    @Override
    public void init(HardwareMap hwMap)
    {
        rFront = hwMap.get(DcMotor.class, "rightFront");
        rFront.setDirection(DcMotorSimple.Direction.FORWARD);
        lFront = hwMap.get(DcMotor.class, "leftFront");
        lFront.setDirection(DcMotorSimple.Direction.FORWARD);
    }


}
