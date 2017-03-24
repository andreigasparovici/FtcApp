package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utils.PerieHandler;

/**
 * Created by timi on 27.01.2017.
 */

@TeleOp(name="Lansator OpMode", group="Iterative Opmode")
@Disabled
public class LansatorOpMode extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftLansator = null;
    private DcMotor rightLansator = null;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftLansator = hardwareMap.dcMotor.get("left_lansator");
        rightLansator = hardwareMap.dcMotor.get("right_lansator");
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

        rightLansator.setPower(-0.9);
        leftLansator.setPower(0.9);
    }
}
