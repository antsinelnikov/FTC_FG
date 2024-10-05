package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor fLMotor = hardwareMap.dcMotor.get("fLMotor");
        DcMotor bLMotor = hardwareMap.dcMotor.get("bLMotor");
        DcMotor fRMotor = hardwareMap.dcMotor.get("fRMotor");
        DcMotor bRMotor = hardwareMap.dcMotor.get("bRMotor");


        fRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bRMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        double k = 1.1;


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * k;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            fRMotor.setPower(frontRightPower);
            bRMotor.setPower(backRightPower);
        }
    }
}