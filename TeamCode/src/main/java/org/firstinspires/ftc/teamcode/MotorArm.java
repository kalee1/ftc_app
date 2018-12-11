package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.EventListener;


public class MotorArm
{
    Telemetry telemetry;

    private DcMotor elbow = null;
//    private DcMotorEx ElbowEx = null;
    private DcMotor shoulder = null;

//    protected TouchSensor Shoulderfront = null;

//    int targetEncoder = 0;


    protected TouchSensor shoulderFront = null;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void init(HardwareMap hwmap, Telemetry telem)
    {
        elbow = hwmap.dcMotor.get("Elbow");
        shoulder = hwmap.dcMotor.get("Shoulder");
//        Shoulderfront = hwmap.touchSensor.get("Shoulderfront");
//        ElbowEx = (DcMotorEx)Elbow;
//        targetEncoder = Elbow.getCurrentPosition();
//        ElbowEx.setTargetPositionTolerance(10);

        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry = telem;
    }

    public void armDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {

        if (shoulderFront.isPressed())
        {
            shoulder.setPower(-0.2);
        }
        else
        {
           elbow.setPower(RightStickY * .5);
           shoulder.setPower(LeftStickY * .6);
        }

    }
}

