package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="crater1", group="Samples")
public class crater1 extends Auto {


    @Override
    public void run() {
            // Step through each leg of the path,
            // Note: Reverse movement is obtained by setting a negative distance (not speed)
            encoderDrive(DRIVE_SPEED, 23, 23, 10);
            aresBot.turn(90, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
            encoderDrive(DRIVE_SPEED, 25, 25, 10);
            aresBot.turn(60, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
            encoderDrive(DRIVE_SPEED, 60, 60, 10);
            aresBot.turn(180, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
            //aresBot.markerRelease.setPosition(0.13725490196);
            sleep(1000);
            //aresBot.markerRelease.setPosition(1);
            encoderDrive(1, 87, 87, 10);
    }

}
