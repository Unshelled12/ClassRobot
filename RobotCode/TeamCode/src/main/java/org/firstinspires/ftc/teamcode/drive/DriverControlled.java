package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(group = "drive")
public class DriverControlled extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor launch;
    private DcMotor suck;
    private DcMotor crane;

    private Servo hook;
    private Servo level;
    private Servo clutch;

    public void runOpMode() throws InterruptedException{
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        launch = hardwareMap.get(DcMotor.class, "launch");
        suck = hardwareMap.get(DcMotor.class, "suck");
        crane = hardwareMap.get(DcMotor.class, "crane");

        hook = hardwareMap.get(Servo.class, "hook");
        level = hardwareMap.get(Servo.class, "level");
        clutch = hardwareMap.get(Servo.class, "clutch");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        suck.setDirection(DcMotor.Direction.REVERSE);
        crane.setDirection(DcMotor.Direction.REVERSE);
        crane.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setPosition(0.35);

        //Level Target
        int craneTarget = 0;
        double target = 0;

        waitForStart();

        while(!isStopRequested())
        {
            if(gamepad2.left_trigger > 0)
            {
                target = (gamepad2.right_stick_y + 1)/2;
            }

            telemetry.addData("Target:", target);
            telemetry.update();

            //Drive Controls
            double y = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double spin = gamepad1.right_stick_x;

            //set motor power to 1/2 speed
            if(gamepad1.left_bumper)
            {
                leftFront.setPower((y + strafe + spin) / 2);
                leftRear.setPower((y - strafe + spin) / 2);
                rightFront.setPower((y - strafe - spin) / 2);
                rightRear.setPower((y + strafe - spin) / 2);
            }
            //set motor power to full
            else
            {
                leftFront.setPower(y + strafe + spin);
                leftRear.setPower(y - strafe + spin);
                rightFront.setPower(y - strafe - spin);
                rightRear.setPower(y + strafe - spin);
            }

            //set hook to down position
            if(gamepad1.a)
            {
                hook.setPosition(0);
            }
            //set hook to up position
            else if(gamepad1.y)
            {
                hook.setPosition(0.35);
            }

            //spin launch motor
            if(gamepad1.b)
            {
                launch.setPower(-1);
            }
            else
            {
                launch.setPower(0);
            }

            //spin suck motor
            if(gamepad1.x && gamepad1.right_bumper)
            {
                suck.setPower(-1);
            }
            else if(gamepad1.x)
            {
                suck.setPower(1);
            }
            else {
                suck.setPower(0);
            }

            //Lift/lower crane
            if(-0.05 <= gamepad2.left_stick_y && gamepad2.left_stick_y <= 0.05)
            {
                crane.setTargetPosition(craneTarget);
                crane.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                crane.setPower(0.5);
            }
            else
            {
                crane.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                crane.setPower(gamepad2.left_stick_y/1.5);
                craneTarget = crane.getCurrentPosition();
            }

            //Level Crane
            level.setPosition(target);

            //Close Grip
            if(gamepad2.left_bumper)
            {
                clutch.setPosition(0.275);
            }
            else if(gamepad2.right_bumper)
            {
                clutch.setPosition(0.55);
            }
        }
    }
}
