package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * Created by Gasparovici on 31.01.2017.
 */

@TeleOp(name="IrSeekerOpMode", group="Iterative Opmode")
public class IrSeekerOpMode extends OpMode {

    IrSeekerSensor irSeekerSensor;

    public void init() {
        irSeekerSensor = hardwareMap.irSeekerSensor.get("irseeker");
    }

    public void loop() {
        telemetry.addData("Signal detected",irSeekerSensor.signalDetected());
        telemetry.addData("Strength",irSeekerSensor.getStrength());
    }
}
