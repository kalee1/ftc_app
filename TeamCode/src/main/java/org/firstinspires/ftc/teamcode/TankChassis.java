package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



/**
 * Created by andrew on 9/13/18.
 */

public class TankChassis extends Chassis
{

    private DcMotor rFront = null;
    private DcMotor lFront = null;

    @Override
    public void init(HardwareMap hwMap)
    {
        rFront = hwMap.get(DcMotor.class, "right_drive");
        rFront.setDirection(DcMotorSimple.Direction.FORWARD);
        lFront = hwMap.get(DcMotor.class, "left_drive");
        lFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }



    /**
     * Drive forward at a specified power for a specified distance in inches
     *
     * @param distance  A double that is the distance in inches to drive
     * @param power  A double that is the power at which to drive
     * */
    public void forwardDrive(double distance, double power)
    {
        rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rFront.setPower(power);
        lFront.setPower(power);

        ticksToInches(distance);
    }

    public void joystickDrive(double leftStick, double rightStick)
    {
        lFront.setPower(leftStick);
        rFront.setPower(rightStick);
    }

    public void stop()
    {
        rFront.setPower(0.0);
        lFront.setPower(0.0);
    }


}
