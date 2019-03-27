package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class WaitAction extends RobotAction
{


    WaitAction(double duration, String id, String nextAction)
    {
        theId = id;
        timeout = duration;

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
    public boolean execute()
    {
        telemetry.addData("timeout: ", timeout);
        telemetry.addData("get run time: ", getRuntime());
        return super.execute();
    }

}
