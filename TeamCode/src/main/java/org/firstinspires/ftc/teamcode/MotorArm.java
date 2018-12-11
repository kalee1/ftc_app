package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MotorArm
{

    DcMotor elbow = null;
    DcMotor shoulder = null;

    protected TouchSensor shoulderFront = null;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void init(HardwareMap hwmap, Telemetry telem)
    {

//        elbow = hwmap.dcMotor.get("elbow");
//        shoulder = hwmap.dcMotor.get("shoulder");
//        shoulderFront = hwmap.touchSensor.get("shoulderFront");
//
//        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void armDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {
//
//        elbow.setPower(RightStickY * .5);
//
//        shoulder.setPower(LeftStickY * .6);
//
        if (shoulderFront.isPressed())
        {
            shoulder.setPower(-0.2);

            shoulder = null;
        }

    }
}

