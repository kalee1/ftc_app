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
    protected TouchSensor Shoulderback = null;
    protected TouchSensor Elbowfront = null;
    protected TouchSensor Elbowback = null;

    double Pos1 = 0;
    double Pos2 = 0;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void Arminit(HardwareMap hwmap, Telemetry telem)
    {

        Elbow = hwmap.dcMotor.get("Elbow");
        Shoulder = hwmap.dcMotor.get("Shoulder");

        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void ArmDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {

        if (RightStickY > 0)
        {
            Elbow.setPower(.15);
        }
        else if (RightStickY < 0)
        {
            Elbow.setPower(-.15);
        }
        else
        {
            Elbow.setPower(0.0);
        }

        Shoulder.setPower(LeftStickY * .6);




    }

}

