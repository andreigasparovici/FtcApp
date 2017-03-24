package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by DragosTrett on 24.03.2017.
 */
@Autonomous(name = "RangeSensorTest", group = "test")
public class RangeSensorTest extends LinearOpMode {
    ModernRoboticsI2cRangeSensor rangeSensor;
    public final static String RANGE_SENSOR_NAME = "range_sensor";

    public void runOpMode(){
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, RANGE_SENSOR_NAME);
        waitForStart();
        while(opModeIsActive()){
            double x=rangeSensor.getDistance(DistanceUnit.CM);
            telemetry.addData("cm", "%.2f cm", x);
            telemetry.update();
        }
    }

}
