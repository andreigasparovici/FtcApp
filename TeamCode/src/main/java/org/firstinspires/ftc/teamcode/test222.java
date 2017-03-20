package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by alex on 06.02.2017.
 */
@TeleOp(name="Gametest", group="Iterative Opmode")
public class test222 extends OpMode {
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    @Override
    public void init() {
        leftMotor  = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
    }

    @Override
    public void loop() {
        leftMotor.setPower(0.65);
        rightMotor.setPower(-0.65);
    }
}
