package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DragosTrett on 25.03.2017.
 */
@Autonomous(name="Rotirica", group = "final")
public class Rotire_test extends LinearOpMode {
    DcMotor leftmotor,rightmotor, trebuchet;
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String TREBUCHET_MOTOR = "catapult_motor";

    public void stop_move()
    {
        rightmotor.setPower(0);
        leftmotor.setPower(0);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        leftmotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightmotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        trebuchet = hardwareMap.dcMotor.get(TREBUCHET_MOTOR);
        waitForStart();

        /*rightmotor.setPower(1);
        leftmotor.setPower(-1);
        sleep(900);

        stop_move();
        sleep(900);*/

        rightmotor.setPower(-1);
        leftmotor.setPower(-1);
        sleep(2400);
        ///400
        stop_move();
        /*sleep(900);

        trebuchet.setPower(1);
        sleep(375);
        stop_move();
        sleep(900);

        rightmotor.setPower(-1);
        leftmotor.setPower(1);
        sleep(2000);*/
    }
}
