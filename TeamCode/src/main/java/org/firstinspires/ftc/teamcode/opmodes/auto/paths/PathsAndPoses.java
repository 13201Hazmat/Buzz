package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.vision.Vision;

import dev.nextftc.hardware.RobotController;

public class PathsAndPoses {

    private final Robot robot;
    private final Vision vision;

    public PathsAndPoses(Robot robot) {
        this.robot = robot;
        this.vision = new Vision();
    }

    public Pose getCurrentPose() {
        return robot.getFollower().getPose();
    }

    public Pose getBallPose() {

        Pose currentPose = robot.getFollower().getPose();

        if (currentPose == null) {
            return null;
        }

        LLResult result = vision.getLimelight().getLatestResult();

        if (result == null || !result.isValid()) {
            return null;
        }

        return vision.getFinalPose(currentPose);
    }

    public PathChain buildPathToBalls() {
        Pose startPose = robot.getFollower().getPose();

        if (startPose == null) {
            return null;
        }

        Pose endPose = getBallPose();

        if (endPose == null) {
            return null;
        }

        return robot.getFollower().pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();
    }
}