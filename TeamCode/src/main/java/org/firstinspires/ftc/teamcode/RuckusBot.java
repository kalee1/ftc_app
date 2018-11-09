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

    ArmTeleop theArm = new ArmTeleop();
    Collector theCollect = new Collector();

    public RuckusBot(String chassisType) {
        if (chassisType.equals("MecanumChassis")) {
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

    public void init(HardwareMap hwMap, Telemetry telem)
    {
 //       theArm.init(hwMap);
        theChassis.init(hwMap, telem);
//        theEyeOfSauron.init(hwMap, telem);

        theCollect.init(hwMap, telem );
//        depoDeposit.init(hwMap);
//        area51.init(hwMap);
    }
    /*
    public void armIntake()
    {
        theCollect.intake();
    }

    public void armEject()
    {
        theCollect.eject();
    }

    public void armStop()
    {
        theCollect.stop();
    }

    public void armMove(double right_stick_y, double right_stick_x, double left_stick_y) {
        theArm.armPosition(right_stick_y, right_stick_x, left_stick_y);
    }

    public void armSleep(boolean sleepButton)
    {
        theArm.armSleep(sleepButton);
    }
    */
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
