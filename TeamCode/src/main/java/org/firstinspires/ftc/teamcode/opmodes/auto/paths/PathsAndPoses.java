package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.vision.Vision;

import dev.nextftc.hardware.RobotController;

public class PathsAndPoses {
    private Robot robot;
    private Vision vision = new Vision(RobotController.hardwareMap());
    private Follower follower = robot.getFollower();
    public Pose testPose = new Pose(72, 21.5);
    public Pose endPose = vision.getFinalPose(vision.getLimelight().getLatestResult().getTx(), vision.getLimelight().getLatestResult().getTy(), testPose);

    public PathChain pathChain = robot.getFollower().pathBuilder()
            .addPath(new BezierLine(testPose, endPose))
            .setLinearHeadingInterpolation(testPose.getHeading(), endPose.getHeading())
            .build();
}
