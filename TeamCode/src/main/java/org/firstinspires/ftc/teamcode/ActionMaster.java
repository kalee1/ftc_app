package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * */
public class ActionMaster
{
    Map<String, RobotAction> actionMap = new HashMap<String, RobotAction>();
    Map<String, RobotAction> runMap = new HashMap<String, RobotAction>();
    List<String>  nextList = new Vector<String>();
    Telemetry telemetry;
    Boolean firstRun = true;

    public void init(Telemetry telem)
    {
        telemetry = telem;
    }

    public void addRunAction(String action)
    {
        runMap.put(action, actionMap.get(action));
    }

    public void execute()
    {
        if(firstRun)
        {
            for(RobotAction action : runMap.values())
            {
                action.entry();
            }
            firstRun = false;
        }

        telemetry.addData("RunMap: ", keyList());
        for(RobotAction action : runMap.values())
        {
            Boolean actionDone = action.execute();
            telemetry.addData("action done: ", actionDone);

            if(actionDone)
            {
                if(action.theNextAction != null)
                {
                    nextList.add(action.theNextAction);
                }
                action.exit();
                runMap.remove(action.theId);
            }
        }

        telemetry.addData("next list: ", nextList.toString());
        for(String next : nextList)
        {
//            telemetry.addData("Size of dictionary: ", actionMap.size());
//            telemetry.addData("Keys:", actionMap.keySet());
//            telemetry.addData("Contains Camera Key?:", actionMap.containsKey(next));
//            telemetry.addData("string id to get from Map:", next );
            RobotAction nextAction = actionMap.get(next);
            telemetry.addData("Name of action got: ", nextAction.theId);
//            telemetry.addData("next action: ", nextAction);

            nextAction.entry();
            addRunAction(nextAction.theId);
        }
        nextList.clear();
    }

    public void buildActionMap()
    {
        actionMap.clear();
        runMap.clear();
        nextList.clear();
    }

    public void addAction(RobotAction action)
    {
        actionMap.put(action.theId, action);
        telemetry.addData("Adding Action Named: ", action.theId);
    }

    public Set<String> keyList()
    {
        return runMap.keySet();
    }

    public int getRunListSize()
    {
        return runMap.size();
    }

}
