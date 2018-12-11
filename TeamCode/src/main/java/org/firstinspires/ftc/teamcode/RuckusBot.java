package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * @author Andrew, Error 404: Team Name Not Found
 */
public class RuckusBot
{
    /** An instance of the Chassis class. Links to the drive motors and the code relating to
     * driving in teleop and autonomous. */
    Chassis theChassis =  null;
    /** An instance of the MarkDeploy class. Links to the mechanism and the code that deploys the
     * team marker in autonomous. */
    MarkDeploy depoDeposit = new MarkDeploy();
//    FieldVision theEyeOfSauron = new FieldVision();

    /** An instance of the MotorArm class. Links to the motors and the code relating to the
     * operation of the mineral arm in teleop. */
    MotorArm theArm = new MotorArm();
    /** An instance of the Collector class. Links to the motor and code relating to sucking minerals
     * into the mineral basket on the end of the mineral arm. */
    Collector theCollect = new Collector();

    /** Determines which chassis type to use: Mecanum or Tank.
     *
     * @param chassisType  A string that is the type of chassis the code will use.
     * */
    public RuckusBot(String chassisType) {
        if (chassisType.equals("MecanumChassis")) {
            theChassis = new MecanumChassis();
        }
        else if (chassisType.equals("TankChassis"))
        {
            theChassis = new TankChassis();
        }
    }

    /** Triggers the initilization of the selected classes.
     *
     * @param hwMap  An instance of the FIRST-provided HardwareMap which is passed onto more
     *               specific classes for initilizng hardware.
     * @param telem  An instance of Telemetry which allows the use of Telemtry in this class.
     * */
    public void init(HardwareMap hwMap, Telemetry telem)
    {
        theChassis.init(hwMap, telem);
//        theEyeOfSauron.init(hwMap, telem);

        theArm.init(hwMap, telem);
        theCollect.init(hwMap, telem );
        depoDeposit.init(hwMap, telem);
    }

    /** */
    public void armDrive(double RightStickY, double LeftStickY)
    {
        theArm.armDrive(RightStickY, LeftStickY);
    }
    /** */
    public void intake()
    {
        theCollect.intake();
    }
    /** */
    public void eject()
    {
        theCollect.eject();
    }
    /** */
    public void collectorStop()
    {
        theCollect.stop();
    }
    /** */
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX,
                              double rightStickY, double powerLimit)
    {
        theChassis.joystickDrive(leftStickX, leftStickY, rightStickX, rightStickY, powerLimit);
    }
    /** */
    public boolean drive(double power, double direction, double gain, double distance, double time)
    {
        return theChassis.drive(power, direction, gain, distance, time);
    }
    /** */
    public boolean tankDrive(double power, Chassis.TankDirection direction, double gain,
                             double distance, double time)
    {
        return theChassis.tankDrive(power, direction, gain, distance, time);
    }
    /** */
    public boolean pointTurn(double power, double targetHeading, double time)
    {
        return theChassis.pointTurn(power, targetHeading, time);
    }
    /** */
    public void stopMotors()
    {
        theChassis.stopMotors();
    }
    /** */
    public void markDeploy()
    {
        depoDeposit.deploy();
    }
    /** */
    public void markRetract()
    {
        depoDeposit.retract();
    }

}
