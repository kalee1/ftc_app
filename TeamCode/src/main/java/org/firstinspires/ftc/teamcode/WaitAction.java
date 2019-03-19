package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class WaitAction extends RobotAction
{


    WaitAction(String id, double duration, String theNextAction)
    {
        name = id;
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
    public void entry()
    {
        super.entry();
    }

    @Override
    public void exit()
    {

    }

    @Override
    public boolean execute()
    {
        telemetry.addData("timeout: ", timeout);
        telemetry.addData("get run time: ", getRuntime());
        return super.execute();
    }

}
