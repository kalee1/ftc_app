package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by andrew on 9/13/18.
 */

public class Tank extends Chassis
{

    private DcMotor rFront = null;
    private DcMotor lFront = null;

    @Override
    public void init(HardwareMap hwMap)
    {
        rFront = hwMap.get(DcMotor.class, "rightFront");
        rFront.setDirection(DcMotorSimple.Direction.FORWARD);
        lFront = hwMap.get(DcMotor.class, "leftFront");
        lFront.setDirection(DcMotorSimple.Direction.FORWARD);
    }


}
