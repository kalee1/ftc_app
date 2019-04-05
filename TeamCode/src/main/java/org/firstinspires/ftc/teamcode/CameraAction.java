package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        super( id, nextAction, 10);
//        theId = id;
//        if(nextAction.isEmpty())
//        {
//            theNextAction = null;
//        }
//        else
//        {
//            theNextAction = nextAction;
//        }
//        timeout = 600;
    }

    CameraAction(String[] params)
    {
        this(params[0], params[1]);
    }


    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    public void entry()
    {
        //robot.start();
        count = 0;
        super.entry();
    }

    @Override
    public boolean execute()
    {
        try
        {
            goldPosition = robot.goldPosition();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

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
        telemetry.addData("Gold Position: ", goldPosition);
        telemetry.addData("Camera Action done? ", done);

        return done && super.execute();  // the super.execute is temporary so that we have time to see what is going on.
    }

    @Override
    public void exit()
    {
        robot.tfodShutdown();
        super.exit();
    }
}


