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
        //case 4
        leftMineral = -55;
        //case 8
        rightMineral = -120;
        //case 6 & 11
        centerMineral = -87;
        //case 12
        mineralDriveDistanceL = 31; //done
        mineralDriveDistanceR = 37; //done
        mineralDriveDistanceC = 45; //done
        //case13
        backup = 6;
        //case 14
        faceDepoHeadingL = 45; //done
        faceDepoHeadingR = 120; //done
        faceDepoHeadingC = 135; //done
        //case 15
        mineralSlideDistanceL = 21; //done
        mineralSlideDistanceR = 0; //18
        mineralSlideDistanceC = 25; //done
        //case 15
        directionL = right;
        directionG = left;
        //case 16
        depoDriveDistanceL = 30; //done
        depoDriveDistanceR = 32; //done
        depoDriveDistanceC = 8; //done
        //case 17
        depoTurnHeadingL = 135;
        depoTurnHeadingR = 135;
        depoTurnHeadingC = 135;
        //case 18
        markerSlideDistance = 7;
        //case 20
        faceCraterHeading = 133;
        //case21
        craterDriveDistance = 44;
        //case 22
        craterTurnHeading = 173;
        //case 23
        craterSlideDistance = 63;


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
