package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

/**
 * Contains all the vision code for autonomous including the tensor flow and vuforia image
 * navigation code.
 *
 * @author  Andrew, Error 404: Team Name Not Found
 * */
public class FieldVision
{

    //standard vuforia stuff
    Telemetry telemetry;

    /** Labeling the tensor flow assets. */
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    /** The 3D asset of the gold mineral which is used by the tensor flow code. */
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    /** The 3D asset of the silver mineral which is used by the tensor flow code. */
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /** The user-unique vuforia license key which allows the use of the vuforia sofware. */
    private static final String VUFORIA_KEY = "Ad6PrLn/////AAABmcL6fnsAN0NVnKxQV1Ko3bJpu5A7aDKat+BYcDQbPbdYejMpWXxDoqI5CO5fBqZtYHbBhcG1jjPL4bS/SvDqwI1He1kkqtw4YnZex3qhNUDsABTRdBeaiTyARIf5fGihCakVaCwzGHcPX6tdmJDofA/Q397J9cndk946HOeSqVAtj5/N8lJIXIyaW8s8rXULNgU7XQvQ0v+CC1O6yecH4/kDIYlXjGREV734h4JAKHFeVNuOB3/y8spjIcRCXRc3WPR80d9dAbs5ZB+NsITpCqjkxHGJOKBGDCI4xbQzDJs1JMTRAUWi+GhlIY2AfLWiNWX1d/R/J9+lq5C7UuqnMiyojSk+gJDD37c5H3D2Q/Ni";

    // Select which camera you want use.  The FRONT camera is the one on the same side as the screen.
    // Valid choices are:  BACK or FRONT
    /** The camera that vuforia will use. There are two choices for the phone: BACK and FRONT. */
//    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;



    // Vuforia Image navigation stuff
    private static final float mmPerInch        = 25.4f;
    private static final float mmFTCFieldWidth  = (12*6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor
    private OpenGLMatrix lastLocation = null;
    private boolean targetVisible = false;
    private VuforiaTrackables targetsRoverRuckus;
    private VuforiaTrackable blueRover;
    private VuforiaTrackable redFootprint;
    private VuforiaTrackable frontCraters;
    private VuforiaTrackable backSpace;
    private List<VuforiaTrackable> allTrackables;


    //initializing all the tensor flow and vuforia navigation stuff
    /**
     * Initializes all the hardware and assets used by vuforia and tensor flow.
     *
     * @param hwMap  An instnce of the FIRST-provided HardwareMap which allows for hardware to be
     *               initilized to the robot.
     * @param telem  An instance of Telemetry which allows this class to use Telemetry.
     * */
    public void init(HardwareMap hwMap, Telemetry telem)
    {
        telemetry = telem;
        initVuforia( hwMap );
        if (ClassFactory.getInstance().canCreateTFObjectDetector())
        {
            initTfod( hwMap );
        }
        else
        {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
     }

    public void start()
    {
        /** Start tracking the data sets we care about. */
//        targetsRoverRuckus.activate();
        tfod.activate();
    }



    /**
     *  the tensor flow code
     * @return  a string that tells the location of the gold mineral
     */
    /** Sourced from the FIRST-provided tensor flow example code, this method recognizes minerals
     * in its field of vision and identifies the position of the gold mineral (LEFT, RIGHT, CENTER)
     * and returns that value as a string.
     * */
    public String tensorFlowMineralDetection()
    {
        String goldPosition = "null";

        if (tfod != null)
        {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null)
            {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() > 0)
                {
                    int goldMineralX = -1;
//                    int silverMineral1X = -1;
//                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions)
                    {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL))
                        {
                            goldMineralX = (int) recognition.getLeft();
                        }
//                        else if (silverMineral1X == -1)
//                        {
//                            silverMineral1X = (int) recognition.getLeft();
//                        }
//                        else
//                        {
//                            silverMineral2X = (int) recognition.getLeft();
//                        }
                    }
//                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
//                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
//                            telemetry.addData("Gold Mineral Position", "Left");
//                            goldPosition = "left";
//                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
//                            telemetry.addData("Gold Mineral Position", "Right");
//                            goldPosition = "right";
//                        } else {
//                            telemetry.addData("Gold Mineral Position", "Center");
//                            goldPosition = "center";
                    if(goldMineralX != -1)
                    {
                        if(goldMineralX < 200)
                        {
                            goldPosition = "left";
                        }
                        else if(goldMineralX > 550)
                        {
                            goldPosition = "right";
                        }
                        else
                        {
                            goldPosition = "center";
                        }
                    }

                    telemetry.addData("gold", goldMineralX);
//                    telemetry.addData("silver 2", silverMineral2X);
//                    telemetry.addData("silver 1", silverMineral1X);

                }

            }
        }

        return goldPosition;
    }

//    public boolean goldTrack()
//    {
//        return true;
//    }


//    /**
//     * The vuforua navigation code
//     */
//    public void vuforiaRun()
//    {
//        // check all the trackable target to see which one (if any) is visible.
//        targetVisible = false;
//        for (VuforiaTrackable trackable : allTrackables)
//        {
//            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible())
//            {
//                telemetry.addData("Visible Target", trackable.getName());
//                targetVisible = true;
//
//                // getUpdatedRobotLocation() will return null if no new information is available since
//                // the last time that call was made, or if the trackable is not currently visible.
//                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
//                if (robotLocationTransform != null) {
//                    lastLocation = robotLocationTransform;
//                }
//                break;
//            }
//        }
//
//        // Provide feedback as to where the robot is located (if we know).
//        if (targetVisible)
//        {
//            // express position (translation) of robot in inches.
//            VectorF translation = lastLocation.getTranslation();
//            telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
//                    translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);
//
//            // express the rotation of the robot in degrees.
//            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
//            telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
//        }
//        else
//        {
//            telemetry.addData("Visible Target", "none");
//        }
//    }


    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia( HardwareMap hwMap ) {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hwMap.get(WebcamName.class, "JesterCam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod( HardwareMap hwMap ) {
        int tfodMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    void doTFOD ()
    {
    }

    public void tfodShutdown()
    {
        tfod.shutdown();
    }


}
