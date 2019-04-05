package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class MarkDeployAction extends RobotAction
{
    double thePower;

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

    MarkDeployAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]), Double.parseDouble(params[3]));
    }

    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    @Override
    public void entry()
    {
        robot.markDeploy(thePower);
    }

    @Override
    public boolean execute()
    {
        return super.execute();
    }

    @Override
    public void exit()
    {
        robot.markDeploy(0.0);
        super.exit();
    }

}
