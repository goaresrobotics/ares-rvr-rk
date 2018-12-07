package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

@Autonomous(name = "Drive by Gyro")

public class driveByGyro extends LinearOpMode {

    public DcMotor  motorLeft   = null;
    public DcMotor  motorRight  = null;

    @Override
    public void runOpMode() {

        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");





        motorLeft.setPower(0);
        motorRight.setPower(0);

        telemetry.addLine("initialized");
        telemetry.update();

        waitForStart();
        telemetry.clear();

        turn(90, 0.2, -0.2, motorLeft, motorRight);

    }

    public void turn(double angle, double left, double right, DcMotor motorLeft, DcMotor motorRight)
    {

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        Orientation orientation = imu.getAngularOrientation();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);
        /*double realAngle = angle*180/Math.PI;*/

        while (orientation.firstAngle<angle) {
            motorLeft.setPower(left);
            motorRight.setPower(right);
            imu.getAngularOrientation();
            double z = orientation.firstAngle;
            telemetry.addData("1", z);
            telemetry.update();
        }
        motorLeft.setPower(0);
        motorRight.setPower(0);

    }

}
