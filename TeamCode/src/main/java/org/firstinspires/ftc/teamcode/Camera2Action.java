package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class Camera2Action extends RobotAction
{
    String goldPosition = "null";
    Boolean done = false;
    Boolean theShutdown = false;
    int count;

    Camera2Action(String id, String nextAction, Boolean shutdown) //default nextAction to center
    {

        super( id, nextAction, 30);
        theShutdown = shutdown;

    }

    Camera2Action(String[] params)
    {
        this(params[0], params[1], Boolean.parseBoolean(params[2]));
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
            theNextAction = "RightPosition";
            telemetry.addData("Gold Position: ", goldPosition);
            done = true;
        }
        else if(goldPosition.equals("center"))
        {
            theNextAction = "CenterPosition";
            telemetry.addData("Gold Position: ", goldPosition);
            done = true;
        }
        else if(goldPosition.equals("left"))
        {
            theNextAction = "LeftPosition";
            telemetry.addData("Gold Position: ", goldPosition);
            done = true;
        }
        else
        {
            done = false;
        }
        telemetry.addData("Gold Position: ", goldPosition);
        telemetry.addData("Camera Action done? ", done);

        return done || super.execute();  // the super.execute is temporary so that we have time to see what is going on.

    }

    @Override
    public void exit()
    {
        if(theShutdown)
        {
            robot.tfodShutdown();
        }
        super.exit();
    }
}
