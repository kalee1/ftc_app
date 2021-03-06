package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Loaded into the run map as an action that reads the gyro. Each action is parameterized by the CSV file.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class GyroAction extends RobotAction
{
    /** A truth value that is whether or not the robot is stuck. */
    boolean notStuck = false;
    /** A truth value that is whether or not the method has finished. */
    boolean done = false;
    /** An int that contains the number of times a method has cycled through. */
    static int count = 0;

    /** Creates a new object from the supplied parameters. */
    GyroAction(String id, String nextAction, double duration)
    {
        super(id, nextAction, duration);
//        theId = id;
//        theNextAction = nextAction;
//        timeout = duration;
    }

    /** Takes the parameters from the CSV file, converts them appropriately, and calls the
     * parameterized constructor */
    GyroAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]));
    }

    /** Placeholder for initialization. Currently only calls the parent init method. */
    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    /** Placeholder for entry. Currently only calls the parent entry method.  */
    @Override
    public void entry()
    {
        telemetry.addData("count", count);
        super.entry();
    }

    /** The body of the action to be executed: Based on the boolean value returned by the goodPitch()
     * method, determines if the robot is stuck and sets the next action accordingly. */
    @Override
    public boolean execute()
    {
        notStuck = robot.theChassis.goodPitch();

        if(notStuck)
        {
            theNextAction = "SlideAway";
            done = true;
        }
        else if(count > 3)
        {
            theNextAction = "AllDone";
            done = true;
        }
        else
        {
            done = true;
            theNextAction = "FailS1";
            count++;
        }
        telemetry.addData("count", count);

        return done;
    }

    /** Placeholder for exit. Currently only calls the parent exit method. */
    @Override
    public void exit()
    {
        telemetry.addData("count", count);
        super.exit();
    }


}
