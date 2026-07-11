package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Putter;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class Robot implements NextRobot {

    private Alliance robotAlliance;

    public Robot(Alliance robotAlliance){
        this.robotAlliance = robotAlliance;
    }

    private final Intake i = new Intake();
    private final Lift l = new Lift();
    private final Putter p = new Putter();


    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(i, l, p);
    }

    public Alliance getAlliance(){
        return robotAlliance;
    }

    public void setAlliance(Alliance robotAlliance){
        this.robotAlliance = robotAlliance;
    }
}
