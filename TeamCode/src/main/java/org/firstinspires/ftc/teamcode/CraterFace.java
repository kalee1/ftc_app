package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="Crater Face", group="Zombie")
/** The autonomous class that handles autonomous from the crater side of the lander.
 * This class loads data from a spreadsheet and uses the data to create a sequential list of robot
 * actions.  It lands, samples, goes to the depot, drops the marker, and comes back to the crater.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see E404_Autonomous
 * */
public class CraterFace extends E404_Autonomous
{
    /** Calls the init methods for needed classes. */
    @Override
    public void init()
    {
        autoFile = new File("/storage/9016-4EF8/CraterFace.csv");
        super.init();
    }
}
