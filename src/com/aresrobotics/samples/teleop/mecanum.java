package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mecanum")
public class mecanum extends OpMode {


    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;

    @Override
    public void init()
    {

        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");

    }

    @Override
    public void loop()
    {

        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;

        leftFront.setPower(v1/1.5);
        rightFront.setPower(-v2/1.5);
        leftRear.setPower(v3/1.5);
        rightRear.setPower(-v4/1.5);

    }

}