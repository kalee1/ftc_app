package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


public class FieldVision
{

    Telemetry telemetry;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "Ad6PrLn/////AAABmcL6fnsAN0NVnKxQV1Ko3bJpu5A7aDKat+BYcDQbPbdYejMpWXxDoqI5CO5fBqZtYHbBhcG1jjPL4bS/SvDqwI1He1kkqtw4YnZex3qhNUDsABTRdBeaiTyARIf5fGihCakVaCwzGHcPX6tdmJDofA/Q397J9cndk946HOeSqVAtj5/N8lJIXIyaW8s8rXULNgU7XQvQ0v+CC1O6yecH4/kDIYlXjGREV734h4JAKHFeVNuOB3/y8spjIcRCXRc3WPR80d9dAbs5ZB+NsITpCqjkxHGJOKBGDCI4xbQzDJs1JMTRAUWi+GhlIY2AfLWiNWX1d/R/J9+lq5C7UuqnMiyojSk+gJDD37c5H3D2Q/Ni";

    // Select which camera you want use.  The FRONT camera is the one on the same side as the screen.
    // Valid choices are:  BACK or FRONT
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;


    public void init(HardwareMap hwMap, Telemetry telem)
    {
        telemetry = telem;

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

        int tfodMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);

    }

    public void goldMineralPosition()
    {
    }

    public String tensorFlowMineralDetection()
    {
        String goldPosition = "null";
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null)
            {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3)
                {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions)
                    {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL))
                        {
                            goldMineralX = (int) recognition.getLeft();
                        }
                        else if (silverMineral1X == -1)
                        {
                            silverMineral1X = (int) recognition.getLeft();
                        }
                        else
                        {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1)
                    {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X)
                        {
                            goldPosition = "left";
                            telemetry.addData("Gold Mineral Position", "left");
                        }
                        else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X)
                        {
                            goldPosition = "right";
                            telemetry.addData("Gold Mineral Position", "right");
                        }
                        else
                        {
                            goldPosition = "center";
                            telemetry.addData("Gold Mineral Position", "center");
                        }
                    }
                }
                telemetry.update();
            }
        }

        return goldPosition;
    }





}
