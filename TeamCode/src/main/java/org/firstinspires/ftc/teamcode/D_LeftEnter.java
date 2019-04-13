package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="D-LeftEnter", group="Zombie")
/** The autonomous class that handles autonomous from the depot side of the lander where the robot
 * ends up in the crater on the same side of the field as its own alliance.
 * This class loads data from a spreadsheet and uses the data to create a sequential list of robot
 * actions.  It lands, samples, goes to the depot, drops the marker, and comes back to the crater.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see E404_Autonomous
 * */
public class D_LeftEnter extends E404_Autonomous
{
    /** Calls the init methods for needed classes and locates the pathway for the CSV file for the
     * depo left enter drive path. */
    @Override
    public void init()
    {
        autoFile = new File("/storage/9016-4EF8/D_LeftEnter.csv");
        if (!autoFile.exists())
        {
            autoFile = new File("/storage/3338-6131/D_LeftEnter.csv");
        }
        super.init();
    }
}
