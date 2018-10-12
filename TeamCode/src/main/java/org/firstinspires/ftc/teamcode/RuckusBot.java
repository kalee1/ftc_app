package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by andrew on 9/13/18.
 */

public class RuckusBot
{
    Chassis theChassis =  null;
    MarkDeploy depoDeposit = new MarkDeploy();
    FieldVision sauron = new FieldVision();
    MineralProcessing area51 = new MineralProcessing();

    public RuckusBot(String chassisType)
    {
        if (chassisType.equals("MecanumChassis"))
        {
            theChassis = new MecanumChassis();
        }
        else if (chassisType.equals("TankChassis"))
        {
            theChassis = new TankChassis();
        }
    }


    public void init(HardwareMap hwMap)
    {
        theChassis.init(hwMap);
        depoDeposit.init(hwMap);
        sauron.init(hwMap); //darkness is coming. Can you feel it? ;)
        area51.init(hwMap);
    }

    public void joystickDrive(double xStick, double yStick)
    {
        theChassis.joystickDrive(xStick, yStick);
    }


}
