package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Joystick Telemetry")
public class SampleJoystickTelemetry extends OpMode {
    public DcMotor motor;

    @Override
    public void init() {
        // intentionally empty
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
        double speed = gamepad1.left_stick_y;

        motor.setPower(speed);

        telemetry.addData("joystickX", gamepad1.left_stick_x);
        telemetry.addData("joystickY", gamepad1.left_stick_y);
    }
}
