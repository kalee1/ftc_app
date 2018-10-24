package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;

//@I2cSensor(name = "Pixy2Cam", description = "Color camera from CMUcam", xmlTag = "---")

public class PixySee extends I2cDeviceSynchDevice<I2cDeviceSynch>
{
    // Get the device manufactuer -- CMUcam isn't listed as a manufacturer, so for now I picked "Other"
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Other;
    }

    // Checks to see if the initilization was sucessful. Because there's no way to tell if that's
    // true at the moment, always return true.
    @Override
    protected synchronized boolean doInitialize()
    {
        return true;
    }

    //Name your I2C device
    @Override
    public String getDeviceName()
    {

        return "Pixy2";
    }

    public PixySee(I2cDeviceSynch deviceClient)
    {
        super(deviceClient, true);

        // registerArmingStateCallback deals with situations involving USB cables disconnecting and reconnecting
        super.registerArmingStateCallback(false);

        // deviceClient.engage engages the device so that is can communicate
        this.deviceClient.engage();
    }



}
