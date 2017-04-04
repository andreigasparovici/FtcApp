package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by DragosTrett on 25.03.2017.
 */
@TeleOp(name = "Sugeo Puscasu", group = "fuck you")

public class TestSugeoPuscasu extends LinearOpMode {
    ModernRoboticsI2cGyro gyro;
    int xVal, yVal, zVal = 0;     // Gyro rate Values
    int heading = 0;              // Gyro integrated heading
    int angleZ = 0;


    @Override
    public void runOpMode(){
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        telemetry.addData(">", "Gyro Calibrating. Do Not move!");
        telemetry.update();
        gyro.calibrate();
        while (!isStopRequested() && gyro.isCalibrating())  {
            sleep(50);
            idle();
        }
        telemetry.addData(">", "Gyro Calibrated.  Press Start.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            xVal = gyro.rawX();
            yVal = gyro.rawY();
            zVal = gyro.rawZ();
            heading = gyro.getHeading();
            angleZ  = gyro.getIntegratedZValue();
            telemetry.addData("0", "Heading %03d", heading);
            telemetry.update();
        }
    }
}
