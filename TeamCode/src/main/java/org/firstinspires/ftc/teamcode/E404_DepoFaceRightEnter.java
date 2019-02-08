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
        //Case 8
        mineralDriveDistanceL = 31; //done
        mineralDriveDistanceR = 37; //done
        mineralDriveDistanceC = 45; //done
        backup = 6;

        //Case 10
        faceDepoHeadingL = 45; //done
        faceDepoHeadingR = 120; //done
        faceDepoHeadingC = 135; //done

        //Case 11
        mineralSlideDistanceL = 21; //done
        mineralSlideDistanceR = 0; //18
        mineralSlideDistanceC = 25; //done
        directionL = right;
        directionG = left;
//        depoSlideDistance = 16;

        //Case 12
        depoDriveDistanceL = 30; //done
        depoDriveDistanceR = 32; //done
        depoDriveDistanceC = 8; //done


        //Case 13
        depoTurnHeadingL = 135;
        depoTurnHeadingR = 135;
        depoTurnHeadingC = 135;



        //Case 15
        faceCraterHeading = 135;

        markerTurnHeading = 90;
        markerSlideDistance = 7;

        headingReset = 45;
        craterDriveDistance = 48;
        craterTurnHeading = 255;
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
