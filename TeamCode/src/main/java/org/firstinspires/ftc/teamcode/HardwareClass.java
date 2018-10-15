package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareClass
{

    public DcMotor  leftFront   = null;
    public DcMotor  rightFront  = null;
    public DcMotor  leftBack = null;
    public DcMotor  rightBack  = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public HardwareClass(){
    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        DcMotor rightBack;
        DcMotor leftBack;
        DcMotor rightFront;
        DcMotor leftFront;

        rightBack = hwMap. dcMotor .get( "rightBack" );
        leftBack = hwMap . dcMotor .get( "leftBack" );
        rightFront = hwMap . dcMotor .get( "rightFront" );
        leftFront = hwMap . dcMotor .get( "leftFront" );
    }
}

