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
    private DcMotor rRear = null;
    private DcMotor lRear = null;

    @Override
    public void init(HardwareMap hwMap)
    {
        try {
            rFront = hwMap.dcMotor.get("rightFront");
            rFront.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception p_exeception) {
            rFront = null;
        }
        try {
            lFront = hwMap.dcMotor.get("leftFront");
            lFront.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception p_exeception) {
            lFront = null;
        }
        try {
            rRear = hwMap.dcMotor.get("rightRear");
            rRear.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception p_exeception) {
            rRear = null;
        }
        try {
            lRear = hwMap.dcMotor.get("leftRear");
            lFront.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception p_exeception) {
            rRear = null;
        }

    }



    /**
     * Drive forward at a specified power for a specified distance in inches
     *
     * @param distance  A double that is the distance in inches to drive
     * @param power  A double that is the power at which to drive
     * */
    public void forwardDrive(double distance, double power)
    {
        if(rFront != null)
        {
            rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rFront.setPower(power);
        }

        if(lFront != null)
        {
            lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lFront.setPower(power);
        }

        if(rRear != null)
        {
            rRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rRear.setPower(power);
        }

        if(lRear != null)
        {
            lRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lRear.setPower(power);
        }

        ticksToInches(distance);
    }

    public void joystickDrive(double leftStick, double rightStick)
    {
        if(rFront != null)
        {
            rFront.setPower(rightStick);
        }

        if(lFront != null)
        {
            lFront.setPower(leftStick);
        }

        if(rRear != null)
        {
            rRear.setPower(rightStick);
        }

        if(lRear != null)
        {
            lRear.setPower(leftStick);
        }
    }

    public void stop()
    {
        double power = 0.0;
        if(rFront != null)
        {
            rFront.setPower(power);
        }

        if(lFront != null)
        {
            lFront.setPower(power);
        }

        if(rRear != null)
        {
            rRear.setPower(power);
        }

        if(lRear != null)
        {
            lRear.setPower(power);
        }
    }


}
