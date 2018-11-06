package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "navx test", group = "test")

public class NavxTest extends OpMode
{
    protected IntegratingGyroscope gyro;
    private NavxMicroNavigationSensor navx = null;
    double currentHeading = getHeadingDbl();


    @Override
    public void init ()
    {
        try {
            navx = hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
            gyro = (IntegratingGyroscope)navx;
        } catch (Exception p_exeception) {
            telemetry.addData("navx not found in config file", 0);
            navx = null;
        }

    }

    public void loop()
    {
        telemetry.addData("navx heading", currentHeading);

    }

    public double getHeadingDbl()
    {
        Orientation angles = navx.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
    }

    public void stop()
    {

    }

}
