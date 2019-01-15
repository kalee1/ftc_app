package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//@Autonomous(name="Red Center", group="Field Position")
/** An unused child class of Error404Autonomous. Contains the drive values for an alternate drive
 * path that wasn't actually needed.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_RedCenter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override
    public void init()
    {
        mineralDriveDistance = 15;
        mineralSlideDistance = 45;
        depoTurnHeading = 125;
        depoDriveDistance = 35;
        headingReset = 125;
        craterDriveDistance = 65;
        craterTurnHeading = 180;
        craterSlideDistance = 15;
        enterCraterDistance = 30;

        super.init();
    }

    /** Calls the parent start method. */
    @Override public void start()
    {
        super.start();
    }

    /** Calls the parent loop method. */
    @Override public void loop()
    {
        super.loop();
    }
}
