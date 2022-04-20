package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(group = "drive")
public class DriverControlled extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;

    public void runOpMode() throws InterruptedException{
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(!isStopRequested())
        {
            double y = -gamepad1.left_stick_y;
            double strafe = gamepad1.right_stick_x;
            double spin = gamepad1.left_stick_x;

            leftFront.setPower(y + strafe + spin);
            leftRear.setPower(y - strafe + spin);
            rightFront.setPower(y - strafe - spin);
            rightRear.setPower(y + strafe - spin);
        }
    }
}
