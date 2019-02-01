package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/** Contains the hardware and methods for the mineral collector mechanism.
 *
 * @author Ben, Error 404: Team Name Not Found
 * */
public class Collector {

    /** A continuous rotation servo called theServo. */
    public CRServo theServoR = null;
    public CRServo theServoL = null;
    /** A telemetry object called telemtry. */
    Telemetry telemetry;

    /** Initializes the hardware used by the class. The try-catch statement prevents the code from
     * crashing if it can't find a hardware object. Instead it posts a message to the phone
     * informing the driver of the missing object.
     *
     * @param hwmap  An instance of HardwareMap that allows hardware objects to be initialized.
     * @param telem  An instance of Telemtry that allows this class to use telemetry statements.
     * */
    public void init(HardwareMap hwmap, Telemetry telem)
    {
        telemetry = telem;

        try
        {
            theServoR = hwmap.crservo.get( "CollectorR" );
            theServoR.setDirection(CRServo.Direction.FORWARD);

        }
        catch (Exception p_exeception)
        {
            theServoR = null;
        }
        try
        {
            theServoL = hwmap.crservo.get( "CollectorL" );
            theServoL.setDirection(CRServo.Direction.FORWARD);

        }
        catch (Exception p_exeception)
        {
            theServoL = null;
        }
    }

    /** Turns the intake wheels inward to suck in minerals. */
    public void intake()
    {
        theServoR.setPower(1.0);
        theServoL.setPower(1.0);
    }
    /** Turns the intake wheels outward to spit our minerals. */
    public void eject()
    {
        theServoR.setPower(-1.0);
        theServoL.setPower(-1.0);
    }
    /** Stops the intake wheels. */
    public void stop()
    {
        theServoR.setPower(0.0);
        theServoL.setPower(0.0);
    }
}


