package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class CameraAction extends RobotAction
{
    String goldPosition = "null";
    Boolean done = false;
    int count;

    CameraAction(String id, String nextAction) //default nextAction to center
    {
        theId = id;
        if(nextAction.isEmpty())
        {
            theNextAction = null;
        }
        else
        {
            theNextAction = nextAction;
        }
        timeout = 600;
    }

    CameraAction(String[] params)
    {
        this(params[0], params[1]);
    }

    public void entry()
    {
        //robot.start();
        count = 0;
        super.entry();
    }

    @Override
    public void exit()
    {
        robot.tfodShutdown();
        super.exit();
    }

    @Override
    public boolean execute()
    {
        goldPosition = robot.goldPosition();
        telemetry.addData("Gold Position: ", goldPosition);

        if(goldPosition.equals("right"))
        {
            theNextAction = "rightPosition";
            done = true;
        }
        else if(goldPosition.equals("center"))
        {
            theNextAction = "centerPosition";
            done = true;
        }
        else if(goldPosition.equals("left"))
        {
            theNextAction = "leftPosition";
            done = true;
        }
        else if(count < 3)
        {
            done = false;
            count++;
        }
        else
        {
            done = true;
        }

        return done;
    }
}
