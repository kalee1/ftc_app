package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 *
 * @author Error 404: Team Name Not Found
 */
public class RuckusBot
{
    Chassis theChassis =  null;
//    MarkDeploy depoDeposit = new MarkDeploy();
//    FieldVision theEyeOfSauron = new FieldVision();
//    MineralProcessing area51 = new MineralProcessing();

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
//        depoDeposit.init(hwMap);
//        theEyeOfSauron.init(hwMap); //darkness is coming. Can you feel it? ;)
//        area51.init(hwMap);
    }

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY);
    }

    public void drive(double power, double distance)
    {
        theChassis.drive(power, distance);
    }

    public void pointTurn(double power, double heading)
    {
        theChassis.pointTurn(power, heading);
    }

    public void stopMotors()
    {
        theChassis.stopMotors();
    }


}
