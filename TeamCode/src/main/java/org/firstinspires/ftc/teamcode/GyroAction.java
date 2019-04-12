package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class GyroAction extends RobotAction
{
    boolean notStuck = false;
    boolean done = false;
    static int count = 0;

    GyroAction(String id, String nextAction, double duration)
    {
        super(id, nextAction, duration);
//        theId = id;
//        theNextAction = nextAction;
//        timeout = duration;
    }

    GyroAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]));
    }

    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    @Override
    public void entry()
    {
        super.entry();
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
            theNextAction = "AllDone";
            done = true;
        }
        else
        {
            done = true;
            theNextAction = "FailS1";
            count++;
        }

        return done;
    }

    @Override
    public void exit()
    {
        super.exit();
    }


}
