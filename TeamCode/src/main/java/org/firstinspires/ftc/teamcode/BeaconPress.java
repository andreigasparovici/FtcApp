package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by alex on 07.02.2017.
 */

@TeleOp(name = "Beacon press", group = "Opmode")
@Disabled
public class BeaconPress  extends OpMode{


    static final String ROATA_DREAPTA="roata_dreapta";
    static final String ROATA_STANGA="roata_stanga";
    static final String PERIE="perie";

    DcMotor roataStanga, roataDreapta;
    DcMotor perie;

    public void init(){
        try{
            roataStanga  = hardwareMap.dcMotor.get(ROATA_STANGA);
            roataDreapta = hardwareMap.dcMotor.get(ROATA_DREAPTA);

            roataDreapta.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch(Exception e){
            telemetry.addData("Eroare","Nu am găsit motoarele!");
        }

        try {

            perie = hardwareMap.dcMotor.get(PERIE);
        }
        catch(Exception e){
            telemetry.addData("Eroare","Nu am găsit peria!");
        }

    }

    private double puterePerie = 0;

    public void loop(){

        try {
            roataStanga.setPower(-gamepad1.left_stick_y);
            roataDreapta.setPower(-gamepad1.right_stick_y);


            //TODO: Rewrite this cancerous code

            if (gamepad1.y) {
                if (puterePerie == 0) {
                    puterePerie = .75;
                } else if (puterePerie == .75) {
                    puterePerie = 0;
                } else {
                    puterePerie = .75;
                }
            }

            if (gamepad1.a) {
                if (puterePerie == 0) {
                    puterePerie = -.75;
                } else if (puterePerie == -.75) {
                    puterePerie = 0;
                } else {
                    puterePerie = -.75;
                }
            }


            perie.setPower(puterePerie);
        }catch(Exception e){
            telemetry.addData("Eroare","Robot oprit!");
        }
    }
}
