package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by andrei on 16.03.2017.
 */

@TeleOp(name="AUTONOMIE FINAL", group="Iterative Opmode")
@Disabled
public class AutonomieFINAL extends LinearOpMode{
    //TODO THIS IS NOT THE FINAL ONE MOTHERFUCKER
    // MAPARE DEVICE-URI:

    public static final String COLOR_SENSOR_NAME = "color_sensor";
    public static final String RANGE_SENSOR_NAME = "range_sensor";

    public static final String COLOR_SERVO_NAME = "color_servo";

    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String BRUSH_MOTOR = "brush_motor";

    public final static String[] TOUCH_SENSOR = new String[]{"s1","s2","s3"};

    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;
    public final static double COLOR_SERVO_INITIAL = 0.12;


    public enum TeamColor {
        RED, BLUE
    }

    public TeamColor teamColor = TeamColor.RED;


    DcMotor leftMotor, rightMotor, brushMotor;

    byte[] range1Cache;
    I2cAddr RANGE1ADDRESS = new I2cAddr(0x14);
    public static final int RANGE1_REG_START = 0x04;
    public static final int RANGE1_READ_LENGTH = 2;
    public I2cDevice RANGE1;
    public I2cDeviceSynch RANGE1Reader;

    TouchSensor s1,s2,s3;
    int[] a=new int[9];

    ColorSensor colorSensor;

    Servo colorServo;

    private void mapDevices(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        brushMotor = hardwareMap.dcMotor.get(BRUSH_MOTOR);

        RANGE1 = hardwareMap.i2cDevice.get(RANGE_SENSOR_NAME);
        RANGE1Reader = new I2cDeviceSynchImpl(RANGE1, RANGE1ADDRESS, false);
        RANGE1Reader.engage();

        s1 = hardwareMap.touchSensor.get(TOUCH_SENSOR[0]);
        s2 = hardwareMap.touchSensor.get(TOUCH_SENSOR[1]);
        s3 = hardwareMap.touchSensor.get(TOUCH_SENSOR[2]);

        colorSensor = hardwareMap.colorSensor.get(COLOR_SENSOR_NAME);
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get(COLOR_SERVO_NAME);

        colorServo.setPosition(COLOR_SERVO_INITIAL);
    }

    private boolean isRed() {
        // TODO Find a better way to do this
        return colorSensor.red() > colorSensor.blue();
    }

    private boolean isTeamColor() {
        return (teamColor != TeamColor.RED) ^ isRed();
    }

    public void readLine(){
        if (s1.isPressed())
            a[1]=1;
        else
            a[1]=0;
        if (s2.isPressed())
            a[2]=1;
        else
            a[2]=0;
        if (s3.isPressed())
            a[3]=1;
        else
            a[3]=0;
    }

    private boolean foundLine(){
        return a[1] == 0 || a[2] == 0 || a[3] == 0;
    }

    private void pressLeft(){
        leftMotor.setPower(-1);
        sleep(300);
    }

    private void pressRight(){
        rightMotor.setPower(1);
        sleep(300);
    }

    public boolean inhim;

    public void beginColorCheck() throws InterruptedException {
        inhim = true;
        colorServo.setPosition(COLOR_SERVO_LEFT);
        sleep(200);

        if (!isTeamColor()) {
            pressLeft();
        }

        sleep(500);

        colorServo.setPosition(COLOR_SERVO_RIGHT);
        sleep(200);
        if (!isTeamColor()) {
            pressRight();
        }
        inhim = false;
    }

    public void doTheMagic() throws InterruptedException{
        while (opModeIsActive()) {

            brushMotor.setPower(.5);

            range1Cache = RANGE1Reader.read(RANGE1_REG_START, RANGE1_READ_LENGTH);
            // int ultraSonic = range1Cache[0] & 0xFF;
            readLine();

            boolean ok = false;

            while (foundLine()) {
                ok = true;
                rightMotor.setPower(0);
                leftMotor.setPower(0);
                telemetry.addData("LINE", "Found line");
                telemetry.update();
                sleep(1000);

                rightMotor.setPower(-.1);
                leftMotor.setPower(.1);

                sleep(400);

                rightMotor.setPower(.5);
                leftMotor.setPower(.9);
                sleep(1200);

                //beginColorCheck();

                break;

            }

            if (!ok) {
                leftMotor.setPower(-.05);
                rightMotor.setPower(.05);
            }

            telemetry.update();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        mapDevices();
        waitForStart();
        doTheMagic();
    }
}
