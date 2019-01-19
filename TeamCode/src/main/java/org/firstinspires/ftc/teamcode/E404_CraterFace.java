package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Crater Face", group="Field Position")
/** Contains the distance and heading values for the CraterFace autonomous drive path.
 * Starts on the gold side of the lander and drives from the lander to the alliance depo, then from
 * the depo to the crater, and enters the crater from the right side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_CraterFace extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override
    public void init()
    {
        mineralDriveDistanceL = 14.0;
        mineralDriveDistanceR = 12.0;
        mineralDriveDistanceC = 14.0;
        depoTurnHeading = 20;
        depoDriveDistanceL = 45; //done
        depoDriveDistanceR = 45;
        depoDriveDistanceC = 50;
        headingReset = 135;
        craterDriveDistance = 45;
        craterTurnHeading = 175;
        craterSlideDistance = 65;
        enterCraterDistance = 32;

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
