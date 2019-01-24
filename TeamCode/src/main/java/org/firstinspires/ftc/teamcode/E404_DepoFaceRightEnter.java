package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepoFace (Right Enter)", group="Field Position")

/** Contains the distance and heading values for the DepoFaceRightEnter autonomous drive path.
 * Starts on the silver side and drives from the lander to the alliance depo, then from the depo to
 * the crater, and enters the crater from the right side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_DepoFaceRightEnter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override public void init()
    {
        mineralDriveDistanceL = 13; //done
        mineralDriveDistanceR = 12; //done
        mineralDriveDistanceC = 10; //done

        depoTurnHeading = 135;
        depoSlideDistance = 14;

        faceDepoHeadingL = 170; //done
        faceDepoHeadingR = 180; //done
        faceDepoHeadingC = 180; //done

        mineralSlideDistanceL = 35; //done
        mineralSlideDistanceR = 46; //done
        mineralSlideDistanceC = 45; //done

        depoDriveDistanceL = 52; //done
        depoDriveDistanceR = 45; //done
        depoDriveDistanceC = 55; //done

        faceCraterHeading = 45;

        headingReset = 45;
        craterDriveDistance = 48;
        craterTurnHeading = 260;
        craterSlideDistance = 60;
        enterCraterDistance = 15;
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
