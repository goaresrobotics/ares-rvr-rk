package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ArcadeDrive extends OpMode {

    @Override
    public void init() {

        DcMotor motorRight;
        DcMotor motorLeft;


    }

    @Override
    public void loop() {
        left = hardwareMap.dcMotor.get("motorLeft");
        right = hardwareMap.dcMotor.get("motorRight");

        double v = -gamepad1.left_stick_y;
        double omega = -gamepad1.right_stick_x;

        double left = v - omega;
        double right = v + omega;

        if (Math.abs(left) > 1 || Math.abs(right) > 1){
            double max = Math.max(Math.abs(left), Math.abs(right));
            left /= max;
            right /= max;

            motorRight.setPower(right);
            motorLeft.setPower(left);

        }


    }

}