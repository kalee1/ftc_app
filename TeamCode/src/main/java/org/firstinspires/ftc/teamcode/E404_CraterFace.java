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
        leftMineral = -59;
        rightMineral = -118;
        centerMineral = -90;

        mineralDriveDistanceL = 25; //done
        mineralDriveDistanceR = 25; //done
        mineralDriveDistanceC = 21; //done
        backup = 10;

        faceDepoHeadingL = -90; //done
        faceDepoHeadingR = -90; //done
        faceDepoHeadingC = -90; //done

        mineralSlideDistanceL = 10;
        mineralSlideDistanceR = 0;
        mineralSlideDistanceC = 5;

        //Case 13
        depoTurnHeadingL = -90;
        depoTurnHeadingR = -90;
        depoTurnHeadingC = -90;

        depoSlideDistance = 0;
        directionL = right;
        directionG = right;

        depoDriveDistanceL = 0; //all same
        depoDriveDistanceR = 0;
        depoDriveDistanceC = 0;

        markerTurnHeading = -90;
        markerSlideDistance = 0;
        faceCraterHeading = -90;

        headingReset = -90;
        craterDriveDistance = 0;
        craterTurnHeading = -90;
        craterSlideDistance = 0;
        enterCraterDistance = 0;

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
