package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

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

        for(RobotAction action : runMap.values())
        {
            Boolean actionDone = action.execute();
            telemetry.addData("action done: ", actionDone);
            if(actionDone)
            {
                if(action.nextAction != null)
                {
                    nextList.add(action.nextAction);
                }
                action.exit();
                runMap.remove(action.name);
            }
        }

        for(String next : nextList)
        {

            RobotAction nextAction = actionMap.get(next);
            telemetry.addData("next action: ", nextAction);

            nextAction.entry();
            addRunAction(nextAction.name);
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
        actionMap.put(action.name, action);
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
