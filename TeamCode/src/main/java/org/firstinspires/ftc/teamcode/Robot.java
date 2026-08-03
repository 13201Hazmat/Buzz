package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Putter;
import org.firstinspires.ftc.teamcode.subsystems.vision.Limelight;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class Robot implements NextRobot {

    private Alliance robotAlliance;
    private Follower follower;

    public Robot(Alliance robotAlliance){
        this.robotAlliance = robotAlliance;
    }

    private final Intake intake = new Intake();
    private final Lift lift = new Lift();
    private final Putter putter = new Putter();
    //private final Limelight limelight = new Limelight();

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(intake, lift, putter);
    }

    public Intake getIntake() {
        return intake;
    }

    public Lift getLift() {
        return lift;
    }

    public Putter getPutter() {
        return putter;
    }

    public Alliance getAlliance(){
        return robotAlliance;
    }

    public void setAlliance(Alliance robotAlliance){
        this.robotAlliance = robotAlliance;
    }
}
