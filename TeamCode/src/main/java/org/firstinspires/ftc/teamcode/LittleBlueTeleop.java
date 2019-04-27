package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Little Blue Teleop", group="Teleop")
public class LittleBlueTeleop extends OpMode
{
    DcMotor left = null;
    DcMotor right = null;

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
    public void start(){}

    public void loop()
    {

        right.setPower(gamepad1.right_stick_y);
        left.setPower(gamepad1.left_stick_y);

    }
    public void stop()
    {
        right.setPower(0.0);
        left.setPower(0.0);
    }
}
