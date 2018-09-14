package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by andrew on 9/13/18.
 */

public class Bot
{

    Chassis theChassis = new Chassis();

    public void init(HardwareMap hwMap)
    {
        theChassis.init(hwMap);
    }


}
