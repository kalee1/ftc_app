package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MotorArm
{

    DcMotor Elbow = null;
    DcMotor Shoulder = null;

    protected TouchSensor Shoulderfront = null;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void Arminit(HardwareMap hwmap, Telemetry telem)
    {

//        Elbow = hwmap.dcMotor.get("Elbow");
//        Shoulder = hwmap.dcMotor.get("Shoulder");
//        Shoulderfront = hwmap.touchSensor.get("Shoulderfront");
//
//        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void ArmDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {
//
//        Elbow.setPower(RightStickY * .5);
//
//        Shoulder.setPower(LeftStickY * .6);
//
//        if (Shoulderfront.isPressed())
//        {
//            Shoulder.setPower(0.0);
//
//            Shoulder = null;

    }
}

