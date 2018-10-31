package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**E
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



    public void init(HardwareMap hwMap, Telemetry telem)
    {
        theChassis.init(hwMap, telem);
//        depoDeposit.init(hwMap);
//        theEyeOfSauron.init(hwMap); //darkness is coming. Can you feel it? ;)
//        area51.init(hwMap);
    }

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY, double powerLimit)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY, powerLimit);
    }

    public boolean drive(double power, double direction, double distance, double time)
    {
        return theChassis.drive(power, direction, distance, time);

    }

    public boolean pointTurn(double power, double targetHeading, double time, boolean useExtendedGyro)
    {
        return theChassis.pointTurn(power, targetHeading, time, useExtendedGyro);
    }

    public void stopMotors()
    {
        theChassis.stopMotors();
    }


}
