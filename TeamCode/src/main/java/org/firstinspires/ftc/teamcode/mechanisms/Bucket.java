package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class Bucket implements Mechanism {
    NextServo bucketServo = new NextServo(RobotController.controlHub(), 0);

    private final double HOME_POS = 0;
    private final double DROP_POS = 0;

    public Bucket(){
        bucketServo.setPosition(HOME_POS);
    }
    private Command setPos(double x){
        return Commands.instant(() -> bucketServo.setPosition(x));
    }

    public Command setHome(){
        return setPos(HOME_POS);
    }

    public Command setDrop(){
        return setPos(DROP_POS);
    }
}
