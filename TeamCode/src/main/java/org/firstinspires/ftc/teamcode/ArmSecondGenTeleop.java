package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.SortedMap;
import java.util.concurrent.BrokenBarrierException;


@TeleOp(name = "ArmSecondGenTeleop" , group = "REV" )
public class ArmSecondGenTeleop extends OpMode {

    public DcMotor Elbow = null;
    public DcMotor Shoulder = null;
    public DcMotor Swivel = null;

    double Pos1 = 0;
    double Pos2 = 0;


    // Constructor for Class
    public ArmSecondGenTeleop(){
    }

    @Override
    public void init() {

        Elbow = hardwareMap.dcMotor.get("Elbow");
        Shoulder = hardwareMap.dcMotor.get("Shoulder");
        Swivel = hardwareMap.dcMotor.get("Swivel");

//        Elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    @Override
    public void loop()
    {

        //Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Swivel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        if (gamepad1.right_stick_y > 0)
//        {
//            Elbow.setPower(.15);
//        }
//        else if (gamepad1.right_stick_y < 0)
//        {
//            Elbow.setPower(-.15);
//        }
//        else
//        {
//            Elbow.setPower(0.0);
//        }

        //Shoulder.setPower(gamepad1.left_stick_y * .6);

//        if (gamepad1.left_trigger > 0)
//        {
//            Swivel.setPower(-.3);
//        }
//        else if (gamepad1.right_trigger > 0)
//        {
//            Swivel.setPower(.3);
//        }
//        else
//        {
//            Swivel.setPower(0.0);
//        }


        telemetry.addData("", "" + Elbow.getCurrentPosition());
        telemetry.addData("", "" + Shoulder.getCurrentPosition());
        telemetry.addData("right trigger",""  + gamepad1.right_trigger);
        telemetry.addData("left trigger","" + gamepad1.left_trigger);
        telemetry.update();

        if (gamepad1.x)
        {
            Elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            Shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            Elbow.setTargetPosition(Elbow.getCurrentPosition() + 50);

//            Shoulder.setTargetPosition(50);
//            Elbow.setPower(gamepad1.right_trigger);

            telemetry.addData("current motor mode", "" + Elbow.getMode());
        }
        else
        {
            Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            Elbow.setPower(gamepad1.right_stick_y * 1);

            telemetry.addData("current motor mode", "" + Elbow.getMode());
        }


    }
    @Override
    public void stop() {

    }
}