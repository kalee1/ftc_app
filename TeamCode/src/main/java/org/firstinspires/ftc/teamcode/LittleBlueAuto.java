package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name="Little Blue Auto", group="auto")
public class LittleBlueAuto extends OpMode
{
    DcMotor left = null;
    DcMotor right = null;
    int state = 0;

    public void init()
    {
        try
        {
            right = hardwareMap.dcMotor.get("right");
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setDirection(DcMotorSimple.Direction.FORWARD);
            right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }
        catch (Exception p_exeception)
        {
            right = null;

        }
        try
        {
            left = hardwareMap.dcMotor.get("left");
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left.setDirection(DcMotorSimple.Direction.FORWARD);
            left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }
        catch (Exception p_exeception)
        {
            left = null;
        }
    }
    public void start()
    {
        resetStartTime();
    }

    public void loop()
    {

        switch (state)
        {
            case 0:
                right.setPower(.5);
                left.setPower(.5);
                if(getRuntime() > 5)
                {
                    right.setPower(0.0);
                    left.setPower(0.0);
                    state = 1;
                    resetStartTime();
                }
                break;

            case 1:
                right.setPower(.5);
                left.setPower(-.5);
                if(getRuntime() > 2.5)
                {
                    right.setPower(0.0);
                    left.setPower(0.0);
                    resetStartTime();
                    state = 2;
                }
                break;

            default:
                break;
        }

    }
    public void stop()
    {
        right.setPower(0.0);
        left.setPower(0.0);
    }
}
