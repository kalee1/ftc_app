package org.firstinspires.ftc.teamcode;

public class GyroAction extends RobotAction
{
    boolean notStuck = false;
    boolean done = false;
    static int count = 0;

    GyroAction(String id, String nextAction, double duration)
    {
        theId = id;
        theNextAction = nextAction;
        timeout = duration;
    }

    GyroAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]));
    }

    @Override
    public boolean execute()
    {
        notStuck = robot.theChassis.goodPitch();

        if(notStuck)
        {
            done = true;
        }
        else if(count > 3)
        {
            theNextAction = "allDone";
            done = true;
        }
        else
        {
            done = true;
            theNextAction = "failS1";
            count++;
        }

        return done;
    }

}
