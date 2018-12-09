import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;

public class LessonOneVision extends VisionModule {
    public IntegerSliderVariable minHue = new IntegerSliderVariable("Hue Min", 86, 0, 255);
    public IntegerSliderVariable maxHue = new IntegerSliderVariable("Hue Max", 138, 0, 255);
    public IntegerSliderVariable minSat = new IntegerSliderVariable("Sat Min", 93, 0, 255);
    public IntegerSliderVariable maxSat = new IntegerSliderVariable("Sat Man", 157, 0, 255);
    public IntegerSliderVariable minVal = new IntegerSliderVariable("Val Min",3,0,255);
    public IntegerSliderVariable maxVal = new IntegerSliderVariable("Val Max",255,0,255);
    public void run(Mat frame) {

    }
}