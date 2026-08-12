package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class Drivetrain implements Mechanism {
    public Drivetrain(){}
    public final NextMotor frontLeft = new NextMotor(RobotController.expansionHub(), 0);
    public final NextMotor frontRight = new NextMotor(RobotController.controlHub(), 3);
    public final NextMotor backLeft = new NextMotor(RobotController.expansionHub(), 3);
    public final NextMotor backRight =  new NextMotor(RobotController.controlHub(), 0);




}