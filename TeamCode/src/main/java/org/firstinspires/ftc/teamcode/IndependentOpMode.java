package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by timi on 27.01.2017.
 */

@TeleOp(name="Independent OpMode **THE GOOD ONE**", group="Iterative Opmode")
public class IndependentOpMode extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private boolean isRotatingForward = false;
    private boolean isRotatingBackward = false;

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    private DcMotor perieMotor = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        perieMotor = hardwareMap.dcMotor.get("motor_perie");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    int rotatiePerie = 0;
    boolean prevstate = false;

    private void switchPerie() {
        if (rotatiePerie == 1)
            rotatiePerie = 0;
        else rotatiePerie = 1;
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        rightMotor.setPower(-gamepad1.right_stick_y);
        leftMotor.setPower(gamepad1.left_stick_y);

        if (gamepad1.a && !prevstate) {
            switchPerie();
        }
        prevstate = gamepad1.a;

        if (gamepad1.y) {
            if (rotatiePerie == -1)
                rotatiePerie = 0;
            else rotatiePerie = -1;
        }

        if (rotatiePerie == 1)
            perieMotor.setPower(1);
        else if (rotatiePerie == -1)
            perieMotor.setPower(-1);
        else perieMotor.setPower(0);
    }
}
