import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

public class LessonZeroVision extends VisionModule {

    public IntegerSliderVariable minHue = new IntegerSliderVariable("Min Hue", 80, 0, 255); 
    public IntegerSliderVariable maxHue = new IntegerSliderVariable("Max Hue", 80, 0, 255);
    public IntegerSliderVariable minSat = new IntegerSliderVariable("Min Saturation", 80, 0, 255);   
    public IntegerSliderVariable maxSat = new IntegerSliderVariable("Max Saturation", 80, 0, 255);
    public IntegerSliderVariable minVal = new IntegerSliderVariable("Min Value", 80, 0, 255);
    public IntegerSliderVariable maxVal = new IntegerSliderVariable("Max Value", 80, 0, 255);
    public void run(Mat frame) {
        
        postImage(frame, "Feed");        
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);
        ArrayList<Mat> channels = new ArrayList<Mat>();
        Core.split(frame, channels);
//        postImage(channels.get(0), "Hue");
//        postImage(channels.get(1), "Saturation");
//        postImage(channels.get(2), "Value");
        Mat filteredHue = new Mat();
        Mat filteredSat = new Mat();
        Mat filteredValue = new Mat();
        Core.inRange(channels.get(0), new Scalar(18), new Scalar(38), filteredHue);
        Core.inRange(channels.get(1), new Scalar(104), new Scalar(255), filteredSat);
        Core.inRange(channels.get(2), new Scalar(50), new Scalar(182), filteredValue);
        
        Mat dilateKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
        Mat erodeKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5));
        Imgproc.erode(filteredHue, filteredHue, erodeKernel);
        Imgproc.dilate(filteredHue, filteredHue, dilateKernel);
        Imgproc.erode(filteredSat, filteredSat, erodeKernel);
        Imgproc.dilate(filteredSat, filteredSat, dilateKernel);
        Imgproc.erode(filteredValue, filteredValue, erodeKernel);
        Imgproc.dilate(filteredValue, filteredValue, dilateKernel);
        postImage(filteredHue, "Filtered Hue");
        postImage(filteredSat, "Filtered Saturation");
        postImage(filteredValue, "Filtered Value");
        frame.release();
        Mat filtered = new Mat();
        Core.bitwise_and(filteredHue, filteredSat, filtered);
        Core.bitwise_and(filteredValue, filtered, filtered);
        postImage(filtered, "Filtered all 3 values");

        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();

        Imgproc.findContours(filtered, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Mat drawn = filtered.clone();

        Imgproc.drawContours(drawn, contours, -1, new Scalar(255, 255, 0), 1);
        postImage(drawn, "Contoured");
   }

}
