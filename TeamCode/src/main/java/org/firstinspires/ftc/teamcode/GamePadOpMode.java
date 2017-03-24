package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by andrei on 26.01.2017.
 */
@TeleOp(name="GamePad", group="Iterative Opmode")
@Disabled
public class GamePadOpMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor  = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
    }
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        leftMotor.setPower(-gamepad1.left_stick_y);
        rightMotor.setPower(-gamepad1.right_stick_y);
    }

    @Override
    public void stop() {

    }

}
