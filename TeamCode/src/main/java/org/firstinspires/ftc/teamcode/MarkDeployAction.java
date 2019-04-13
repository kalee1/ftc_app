package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Loaded into the run map as an action that moves the mark deploy servo. Each action is parameterized by the CSV file.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class MarkDeployAction extends RobotAction
{
    /** The power used by the servo. */
    double thePower;

    /** Creates a new object from the supplied parameters. */
    MarkDeployAction(String id, String nextAction, double duration, double power)
    {
        super(id, nextAction, duration);
        thePower = power;
//        timeout = duration;
//        theId = id;
//
//        if(nextAction.isEmpty())
//        {
//            theNextAction = null;
//        }
//        else
//        {
//            theNextAction = nextAction;
//        }

    }

    /** Takes the parameters from the CSV file, converts them appropriately, and calls the
     * parameterized constructor */
    MarkDeployAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]), Double.parseDouble(params[3]));
    }

    /** Placeholder for initialization. Currently only calls the parent init method. */
    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    /** Starts the mark deploy servo and calls the parent entry method.  */
    @Override
    public void entry()
    {
        robot.markDeploy(thePower);
        super.entry();
    }

    /** Placeholder for execute. Calls the parent execute method. */
    @Override
    public boolean execute()
    {
        return super.execute();
    }

    /** Stops the mark deploy servo and calls the parent exit method. */
    @Override
    public void exit()
    {
        robot.markDeploy(0.0);
        super.exit();
    }

}
