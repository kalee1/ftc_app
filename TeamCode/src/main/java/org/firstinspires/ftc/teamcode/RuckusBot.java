package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * @author Error 404: Team Name Not Found
 */
public class RuckusBot
{
    Chassis theChassis =  null;
    MarkDeploy depoDeposit = new MarkDeploy();
//    FieldVision theEyeOfSauron = new FieldVision();
//    MineralProcessing area51 = new MineralProcessing();

    MotorArm theArm = new MotorArm();
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
        theChassis.init(hwMap, telem);
//        theEyeOfSauron.init(hwMap, telem);

        theArm.Arminit(hwMap, telem);
        theCollect.init(hwMap, telem );
        depoDeposit.init(hwMap, telem);
//        area51.init(hwMap);
    }

    public void ArmDrive(double RightStickY, double LeftStickY, Telemetry telem)
    {
        theArm.ArmDrive(RightStickY, LeftStickY);

    }
    public void intake()
    {
        theCollect.intake();
    }
    public void eject()
    {
        theCollect.eject();
    }
    public void collectorStop()
    {
        theCollect.stop();
    }
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY, double powerLimit)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY, powerLimit);
    }

    public boolean drive(double power, double direction, double gain, double distance, double time)
    {
        return theChassis.drive(power, direction, gain, distance, time);
    }

    public boolean tankDrive(double power, Chassis.TankDirection direction, double gain, double distance, double time)
    {
        return theChassis.tankDrive(power, direction, gain, distance, time);
    }

    public boolean pointTurn(double power, double targetHeading, double time)
    {
        return theChassis.pointTurn(power, targetHeading, time);
    }

    public void stopMotors()
    {
        theChassis.stopMotors();
    }

    public void markDeploy()
    {
        depoDeposit.deploy();
    }

    public void markRetract()
    {
        depoDeposit.retract();
    }


}
