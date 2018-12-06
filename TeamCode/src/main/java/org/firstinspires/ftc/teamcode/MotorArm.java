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

    protected TouchSensor Shoulderfront = null;

//    int targetEncoder = 0;


    // Constructor for Class
    public MotorArm()
    {
    }

    public void Arminit(HardwareMap hwmap, Telemetry telem)
    {
        Elbow = hwmap.dcMotor.get("Elbow");
        Shoulder = hwmap.dcMotor.get("Shoulder");
        Shoulderfront = hwmap.touchSensor.get("Shoulderfront");
//        ElbowEx = (DcMotorEx)Elbow;
//        targetEncoder = Elbow.getCurrentPosition();
//        ElbowEx.setTargetPositionTolerance(10);

        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry = telem;
    }

    public void ArmDrive( double RightStickY, double LeftStickY)
    {
//        if(RightStickY > .05)
//        {
//            targetEncoder += 1;
//        }
//        else if(RightStickY < -.05)
//        {
//            targetEncoder -= 1;
//        }
//        ElbowEx.setTargetPosition(targetEncoder);
//        ElbowEx.setPower(.5);

        Elbow.setPower(RightStickY * .5);
        Shoulder.setPower(LeftStickY * .6);

        if (Shoulderfront.isPressed())
        {
            Shoulder.setPower(0.0);
            Shoulder = null;
        }
    }

//    public void armDriveInfo()
//    {
//        telemetry.addData("target position tolerance", ElbowEx.getTargetPositionTolerance());
//        telemetry.addData("PIDF Coefficients RUE", ElbowEx.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
//        telemetry.addData("PIDF Coefficients RTP", ElbowEx.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION));
//    }

}

