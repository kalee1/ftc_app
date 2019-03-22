package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class MarkDeployAction extends RobotAction
{
    double power;

    MarkDeployAction(double thePower, double duration, String theId, String theNextAction)
    {
        power = thePower;
        timeout = duration;

        if(theNextAction.isEmpty())
        {
            nextAction = null;
        }
        else
        {
            nextAction = theNextAction;
        }

    }

    @Override
    public void exit()
    {
        robot.markDeploy(0.0);
        super.exit();
    }

    @Override
    public boolean execute()
    {
        robot.markDeploy(power);
        return super.execute();
    }
}
