package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import static com.pedropathing.ivy.commands.Commands.*;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Robot;

public class AutoCommands {

    private PathsAndPoses paths;
    private Robot robot;
    public Command moveToNearestBalls(){
        return instant(() -> robot.getFollower().followPath(paths.pathChain));
    }
}
