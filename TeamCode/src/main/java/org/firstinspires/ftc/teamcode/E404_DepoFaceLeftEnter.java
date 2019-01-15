package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepoFace (Left Enter)", group="Field Position")
/** Contains the distance and heading values for the DepoFaceLeftEnter autonomous drive path.
 * Starts on the silver side and drives from the lander to the alliance depo, then from the depo to
 * the crater, and enters the crater from the left side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_DepoFaceLeftEnter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override public void init()
    {
        mineralDriveDistance = 55.0;
        mineralSlideDistance = 0.0;
        depoTurnHeading = 45.0; // ~45 degrees
        depoDriveDistance = 0.0;
        headingReset = 45;
        craterDriveDistance = 65;
        craterTurnHeading = 70;
        craterSlideDistance = 0.0;
        enterCraterDistance = 20;
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
