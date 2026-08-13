package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import static com.pedropathing.ivy.commands.Commands.*;

import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Robot;

public class AutoCommands {
    private final Robot robot;
    private final PathsAndPoses paths;
    public AutoCommands(Robot robot, PathsAndPoses paths) {
        this.robot = robot;
        this.paths = paths;
    }

    public Command moveToNearestBalls() {

        return instant(() -> {
            PathChain path = paths.buildPathToBalls();
            if (path == null) {
                return;
            }
            robot.getFollower().followPath(path);
        });
    }
}