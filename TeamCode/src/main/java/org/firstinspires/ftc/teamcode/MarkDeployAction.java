package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class MarkDeployAction extends RobotAction
{
    double thePower;

    MarkDeployAction(double power, double duration, String id, String nextAction)
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
