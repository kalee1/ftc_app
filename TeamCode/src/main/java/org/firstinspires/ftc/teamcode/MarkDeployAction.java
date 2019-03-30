package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class MarkDeployAction extends RobotAction
{
    double thePower;

    MarkDeployAction(String id, String nextAction, double duration, double power )
    {
        thePower = power;
        timeout = duration;
        theId = id;

        if(nextAction.isEmpty())
        {
            theNextAction = null;
        }
        else
        {
            theNextAction = nextAction;
        }

    }

    MarkDeployAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]), Double.parseDouble(params[3]));
    }

    @Override
    public void exit()
    {
        robot.markDeploy(0.0);
        super.exit();
    }

    @Override
    public void entry()
    {
        robot.markDeploy(thePower);
    }
}
