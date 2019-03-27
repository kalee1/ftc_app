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
        theNextAction = nextAction;
    }

    public void entry()
    {
        robot.start();
        count = 0;
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
