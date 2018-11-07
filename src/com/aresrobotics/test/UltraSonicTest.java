package com.aresrobotics.test;

import com.aresrobotics.library.hardware.MaxSonarEZ1UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class UltraSonicTest extends LinearOpMode
{

    MaxSonarEZ1UltrasonicSensor sonar =
            new MaxSonarEZ1UltrasonicSensor(hardwareMap.analogInput.get("Ultrasonic"));

    @Override
    public void runOpMode()
    {
        // required to be here
    }
}

