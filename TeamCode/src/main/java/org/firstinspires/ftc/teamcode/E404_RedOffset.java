package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

//@Autonomous(name="Red Offset", group="Field Position")
/** An unused child class of Error404Autonomous. Contains the drive values for an alternate drive
 * path that wasn't actually needed.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_RedOffset extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override
    public void init()
    {
        super.init();

//        mineralDriveDistance = 55.0;
//        mineralSlideDistance = 0.0;
//        depoTurnHeading = 45.0; // ~45 degrees
//        depoDriveDistance = 0.0;
        headingReset = 45;
        craterDriveDistance = 65;
        craterTurnHeading = 70;
        craterSlideDistance = 15;
        enterCraterDistance = 14;
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
