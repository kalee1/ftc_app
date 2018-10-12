package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;


public class FieldVision
{


    private static final String VUFORIA_KEY = "Ad6PrLn/////AAABmcL6fnsAN0NVnKxQV1Ko3bJpu5A7aDKat+BYcDQbPbdYejMpWXxDoqI5CO5fBqZtYHbBhcG1jjPL4bS/SvDqwI1He1kkqtw4YnZex3qhNUDsABTRdBeaiTyARIf5fGihCakVaCwzGHcPX6tdmJDofA/Q397J9cndk946HOeSqVAtj5/N8lJIXIyaW8s8rXULNgU7XQvQ0v+CC1O6yecH4/kDIYlXjGREV734h4JAKHFeVNuOB3/y8spjIcRCXRc3WPR80d9dAbs5ZB+NsITpCqjkxHGJOKBGDCI4xbQzDJs1JMTRAUWi+GhlIY2AfLWiNWX1d/R/J9+lq5C7UuqnMiyojSk+gJDD37c5H3D2Q/Ni";

    // Select which camera you want use.  The FRONT camera is the one on the same side as the screen.
    // Valid choices are:  BACK or FRONT
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;



    public void init(HardwareMap hwMap)
    {

        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         * We can pass Vuforia the handle to a camera preview resource (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY ;
        parameters.cameraDirection   = CAMERA_CHOICE;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void goldMineralPosition()
    {

    }


}
