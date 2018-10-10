import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class LessonZeroVision extends VisionModule {
    public IntegerSliderVariable maxHue = new IntegerSliderVariable("maxHue",0,0,0);
    public IntegerSliderVariable minHue = new IntegerSliderVariable("minHue",255,255,255);
    public void run(Mat frame) {
      postImage(frame, "Original Image");
        // Your code here
        // Remember to release your Mats before setting them to something else!
        // You may create helper functions if you wish
        // You can also create variables outside this function if necessary

    }

}
