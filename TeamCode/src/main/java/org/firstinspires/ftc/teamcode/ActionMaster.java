package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

import java.util.List;
import java.util.Map;

public class ActionMaster
{
    Map<String, RobotAction> actionMap;
    Map<String, RobotAction> runMap;
    List<String>  nextList;

    public void addRunAction(String action)
    {
        runMap.put(action, actionMap.get(action));
    }

    public void execute()
    {
        for(RobotAction action : runMap.values())
        {
            if(action.execute())
            {
                nextList.add(action.nextAction);
                action.exit();
                runMap.remove(action);
            }
        }
        for(String next : nextList)
        {
            RobotAction nextAction = actionMap.get(next);
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

}
