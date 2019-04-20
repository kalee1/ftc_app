package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Autonomous(name="Mother Autonomus", group="Zeta")

/** The main autonomous class.
 * This class loads data from a spreadsheet and uses the data to create a sequential list of robot
 * actions.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class E404_Autonomous extends OpMode
{
    /** Loads and executes each autonomous action in turn. */
    ActionMaster theMaster = new ActionMaster();
    /** The robot used in autonomous. */
    RuckusBot robot = new RuckusBot("MecanumChassis");
    /** The FTC Dashboard (displays telemetry to a web interface.  */
    FtcDashboard dashboard = FtcDashboard.getInstance();
    /** A telemetry object that uses the ftc dahsbaord. */
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    /** The file that the CSV file sourced from the phone is assigned to. */
    File autoFile = null;

    /** Initializes the robot and telemetry and creates all the actions defined in the appropriate
     * CSV file.
     * */
    @Override
    public void init()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot.init(hardwareMap, telemetry, true);
        theMaster.init(telemetry);

        telemetry.addData("gyro heading", robot.getHeadingDbl());

        BufferedReader br = null;
        String line = "null";
        try
        {
            RobotAction myAction;
//            File theFile = new File("/storage/9016-4EF8/auto.csv"); //blue phones
//            File theFile = new File("/storage/3338-6131/auto.csv"); //red phones
            FileReader inputStreamReader = new FileReader(autoFile);

            br = new BufferedReader(inputStreamReader);

            br.readLine();  // throw away first line by reading it and not looking at it

            while((line = br.readLine()) != null)
            {
                // Get a line from the CSV file and split it on the commas
                String[] theItems = line.split(",");

                // Grab the first one... this will be the type of RobotAction to create
                String type = theItems[0].trim().toUpperCase();

                // Grab the reset of the items after the first one... these are the parameters to use
                // with the constructors for each of the RobotAction subclasses.
                String[] params = (Arrays.copyOfRange(theItems, 1, theItems.length));

                // Based on the type, make the specific kind of RobotAction
                if (type.equalsIgnoreCase("WAITACTION"))
                {
                    myAction = new WaitAction(params);
                }
                else if (type.equalsIgnoreCase("DRIVEACTION"))
                {
                    myAction = new DriveAction(params);
                }
                else if(type.equalsIgnoreCase("TURNACTION"))
                {
                    myAction = new TurnAction(params);
                }
                else if(type.equalsIgnoreCase("CAMERAACTION"))
                {
                    myAction = new CameraAction(params);
                }
                else if(type.equalsIgnoreCase("HANGACTION"))
                {
                    myAction = new HangAction(params);
                }
                else if(type.equalsIgnoreCase("ARMACTION"))
                {
                    myAction = new ArmAction(params);
                }
                else if(type.equalsIgnoreCase("MARKDEPLOYACTION"))
                {
                    myAction = new MarkDeployAction(params);
                }
                else if(type.equalsIgnoreCase("GYROACTION"))
                {
                    myAction = new GyroAction(params);
                }
                else if(type.equalsIgnoreCase("RESETACTION"))
                {
                    myAction = new ResetHeadingAction(params);
                }
                else
                {
                    myAction = null;
                }

                if(myAction != null)
                {
                    myAction.init(telemetry, robot);
                    theMaster.addAction(myAction);
                }
                telemetry.addData("TheAction: ", myAction);
            }
            theMaster.addRunAction("One");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            telemetry.addData("file not found e: ", e);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            telemetry.addData("io exception: ", e);
        }
        finally
        {
            if(br != null)
            {
                try
                {
                    br.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    /** Runs once when the start button is pressed, but before
     * the loop method starts. */
    @Override
    public void start()
    {
        robot.start();
        resetStartTime();
        telemetry.addData("gyro heading: ",robot.getHeadingDbl());
    }

    /** Contains the actual movement commands of the class. Runs repeatedly until the stop button is
     *  pressed.*/
    @Override
    public void loop()
    {
        telemetry.addData("gyro heading: ",robot.getHeadingDbl());
        theMaster.execute();
    }

    /** Runs once after the driver hits the stop button on teh drivers station phone.
     * Stops the loop method and stops the drive motors. */
    @Override
    public void stop()
    {
        robot.stopMotors();
    }
}
