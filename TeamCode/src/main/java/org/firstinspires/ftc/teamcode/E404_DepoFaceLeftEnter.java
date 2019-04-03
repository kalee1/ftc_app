package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepoFace (Left Enter)", group="Field Position")
/** Contains the distance and heading values for the DepoFaceLeftEnter autonomous drive path.
 * Starts on the silver side and drives from the lander to the alliance depo, then from the depo to
 * the crater, and enters the crater from the left side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus1
 * */
public class E404_DepoFaceLeftEnter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override public void init()
    {
        //case 4
        leftMineral = -55;
        //case 8
        rightMineral = -120;
        //case 6 & 11
        centerMineral = -87;
        //case 12
        mineralDriveDistanceL = 45; //done
        mineralDriveDistanceR = 37; //done
        mineralDriveDistanceC = 52; //done
        //case 13
        backup = 6;
        //case 14
        faceDepoHeadingL = 40; //done
        faceDepoHeadingR = 120; //done
        faceDepoHeadingC = 135; //done
        //case15
        mineralSlideDistanceL = 0; //done
        mineralSlideDistanceR = 0; //18
        mineralSlideDistanceC = 25; //done
        //case 15
        directionL = right;
        directionG = left;
        //case 16
        depoDriveDistanceL = 30; //done
        depoDriveDistanceR = 32; //done
        depoDriveDistanceC = 5; //done
        //case 17
        depoTurnHeadingL = 135;
        depoTurnHeadingR = 135;
        depoTurnHeadingC = 135;
        //case 18
        markerSlideDistance = 7;
        //case 20
        faceCraterHeading = 130;
        //case 21
        craterDriveDistance = 60;
        //case 22
        craterTurnHeading = 133;
        //case 23
        craterSlideDistance = 0.0;

        //not used
        markerTurnHeading = 90;
        headingReset = 45;
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
