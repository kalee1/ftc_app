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

    private DcMotor Elbow = null;
//    private DcMotorEx ElbowEx = null;
    private DcMotor Shoulder = null;

//    protected TouchSensor Shoulderfront = null;

//    int targetEncoder = 0;


    DcMotor elbow = null;
    DcMotor shoulder = null;

    protected TouchSensor shoulderFront = null;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void init(HardwareMap hwmap, Telemetry telem)
    {
        Elbow = hwmap.dcMotor.get("Elbow");
        Shoulder = hwmap.dcMotor.get("Shoulder");
//        Shoulderfront = hwmap.touchSensor.get("Shoulderfront");
//        ElbowEx = (DcMotorEx)Elbow;
//        targetEncoder = Elbow.getCurrentPosition();
//        ElbowEx.setTargetPositionTolerance(10);

        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

