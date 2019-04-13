package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Loaded into the run map as an action that turns the robot. Each action is parameterized by the CSV file.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class ResetHeadingAction extends RobotAction
{
    /** The power the robot will run at. */
    double thePower;

    /** Creates a new object from the supplied parameters. */
    ResetHeadingAction(String id, String nextAction, double duration, double power)
    {
        super(id, nextAction, duration);
        thePower = power;
    }

    /** Takes the parameters from the CSV file, converts them appropriately, and calls the
     * parameterized constructor */
    ResetHeadingAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]));
    }

    /** Placeholder for initialization. Currently only calls the parent init method. */
    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    /** Placeholder for entry. Calls the parent entry method.  */
    @Override
    public void entry()
    {
        super.entry();
    }

    /** The body of the action to be executed: Calls the reset() method in MecanumChassis
     * */
    @Override
    public boolean execute()
    {
        return robot.reset(thePower, timeout);
    }

    /** Placeholder for exit. Calls the parent exit method. */
    @Override
    public void exit()
    {
        super.exit();
    }
}
