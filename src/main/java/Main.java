/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.vision.VisionThread;

public final class Main extends MainBase {

  private Main() {
  }

  /**
   * Example pipeline.
   */

  /**
   * Main.
   */
  public static void main(String... args) {

    standardInit(args);

    if (cameras.size() > 0) {
      VisionThread visionThread = new VisionThread(cameras.get(0),
              new TargetingPipeline(), pipeline -> {
        // do something with pipeline results
      });

      visionThread.start();
    }

    standardRun();

  }
}
