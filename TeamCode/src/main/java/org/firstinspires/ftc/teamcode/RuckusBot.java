package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;


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

    ArmTeleop theArm = new ArmTeleop();

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

    public void armDrive()
    {

    }

    public void init(HardwareMap hwMap)
    {
        theChassis.init(hwMap);
//        depoDeposit.init(hwMap);
//        theEyeOfSauron.init(hwMap); //darkness is coming. Can you feel it? ;)
//        area51.init(hwMap);
    }

    public void armMove(double right_stick_y ,double right_stick_x)
    {
        theArm.armPosition(right_stick_y ,right_stick_x);
    }

    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY);
    }

    public boolean drive(double power, double direction, double distance, double time)
    {
        theChassis.drive(power, direction, distance, time);
        return theChassis.moving;
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
