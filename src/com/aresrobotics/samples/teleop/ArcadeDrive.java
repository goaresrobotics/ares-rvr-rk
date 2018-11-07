package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive")
public class ArcadeDrive extends OpMode {

    private DcMotor motorRight;
    private DcMotor motorLeft;

    @Override
    public void init() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");



    }

    @Override
    public void loop() {

        double v = gamepad1.right_stick_x;
        double omega = -gamepad1.left_stick_y;

        double right = v - omega;
        double left = v + omega;

        motorRight.setPower(right);
        motorLeft.setPower(left);

    }

}