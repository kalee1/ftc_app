package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="TestBot Teleop", group="Teleop")
public class TestBotTeleop extends OpMode
{
    private DcMotor rightMotor = null;
    private DcMotor leftMotor = null;

    public void init()
    {
        rightMotor = hardwareMap.dcMotor.get("right");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor = hardwareMap.dcMotor.get("left");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void loop()
    {
        if(gamepad1.right_stick_y != 0)
        {
            rightMotor.setPower(-gamepad1.right_stick_y);
        }
        else
        {
            rightMotor.setPower(0.0);
        }
        if(gamepad1.left_stick_y != 0)
        {
            leftMotor.setPower(gamepad1.left_stick_y);
        }
        else
        {
            leftMotor.setPower(0.0);
        }

//        if(gamepad1.left_stick_y != 0)
//        {
//            if(gamepad1.left_stick_y > 1)
//            {
//                rightMotor.setPower(1);
//                leftMotor.setPower(-1);
//            }
//            else
//            {
//                rightMotor.setPower(-1);
//                leftMotor.setPower(1);
//            }
//        }
//        else if(gamepad1.right_stick_x != 0)
//        {
//            if(gamepad1.right_stick_x > 1)
//            {
//                rightMotor.setPower(1);
//                leftMotor.setPower(1);
//            }
//            else
//            {
//                rightMotor.setPower(-1);
//                leftMotor.setPower(-1);
//            }
//        }
//        else
//        {
//            leftMotor.setPower(0.0);
//            rightMotor.setPower(0.0);
//        }

    }

    public void stop()
    {
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }
}
