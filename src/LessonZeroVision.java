import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class LessonZeroVision extends VisionModule {

    public IntegerSliderVariable minHue = new IntegerSliderVariable("Min Hue", 80, 0, 255); 
    public IntegerSliderVariable maxHue = new IntegerSliderVariable("Max Hue", 80, 0, 255);
    public IntegerSliderVariable minVal = new IntegerSliderVariable("Min Value", 80, 0, 255);
    public IntegerSliderVariable maxVal = new IntegerSliderVariable("Max Value", 80, 0, 255);
    public IntegerSliderVariable minSat = new IntegerSliderVariable("Min Saturation", 80, 0, 255);   
    public IntegerSliderVariable maxSat = new IntegerSliderVariable("Max Saturation", 80, 0, 255);

    public void run(Mat frame) {

        
        
        postImage(frame, "Feed");
        
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);
        
        ArrayList<Mat> channels = new ArrayList<Mat>();

        Core.split(frame, channels);
        
        postImage(channels.get(0), "Hue");

        postImage(channels.get(1), "Saturation");

        postImage(channels.get(2), "Value");

        Mat filteredHue = new Mat();

        Mat filteredSat = new Mat();

        Mat filteredValue = new Mat();

        Core.inRange(channels.get(0), new Scalar(minHue.value()), new Scalar(maxHue.value()), filteredHue);
    
        Core.inRange(channels.get(1), new Scalar(minSat.value()), new Scalar(maxSat.value()), filteredSat);

        Core.inRange(channels.get(2), new Scalar(minVal.value()), new Scalar(maxVal.value()), filteredValue);

        postImage(filteredHue, "Filtered Hue");

        postImage(filteredSat, "Filtered Saturation");

        postImage(filteredValue, "Filtered Valu");

        frame.release();
        
        // Your code here
        // Remember to release your Mats before setting them to something else!
        // You may create helper functions if you wish
        // You can also create variables outside this function if necessary
    }

}
