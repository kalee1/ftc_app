package org.firstinspires.ftc.teamcode;

import android.provider.Settings;

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
    double shoulderCurrentDis = 0;
    double shoulderDis = 0;

    private DcMotor Elbow = null;
    //    private DcMotorEx ElbowEx = null;
    private DcMotor Shoulder = null;

//    int targetEncoder = 0;


    // Constructor for Class
    public MotorArm()
        {
        }

    public void init(HardwareMap hwmap, Telemetry telem)
        {

            try
                {
                Elbow = hwmap.dcMotor.get("Elbow");
                Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
            catch (Exception p_exception)
                {
                Elbow = null;
                telem.addData("Elbow not found", "");
                }
            try
                {
                Shoulder = hwmap.dcMotor.get("Shoulder");
                Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                Shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
            catch (Exception p_exception)
                {
                Shoulder = null;
                telem.addData("Shoulder not found", "");
                }
            telemetry = telem;
        }

    public void armDrive( double RightStickY, double LeftStickY)
        {

            Elbow.setPower(RightStickY * .5);

            Shoulder.setPower(LeftStickY * .6);

        }
    public void ArmDeploy()
        {
        /*
        shoulderCurrentDis = Shoulder.getCurrentPosition();
        shoulderDis = Shoulder.getCurrentPosition() + 5000;

        if (shoulderCurrentDis <= shoulderDis)
        {

        }
        */
        }
    }

