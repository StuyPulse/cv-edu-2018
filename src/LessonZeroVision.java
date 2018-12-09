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

public class LessonZeroVision extends VisionModule {

    public IntegerSliderVariable minHue = new IntegerSliderVariable("Hue Min", 86, 0, 255);
    public IntegerSliderVariable maxHue = new IntegerSliderVariable("Hue Max", 138, 0, 255);
    public IntegerSliderVariable minSat = new IntegerSliderVariable("Sat Min", 93, 0, 255);
    public IntegerSliderVariable maxSat = new IntegerSliderVariable("Sat Man", 157, 0, 255);
    public IntegerSliderVariable minVal = new IntegerSliderVariable("Val Min",3,0,255);
    public IntegerSliderVariable maxVal = new IntegerSliderVariable("Val Max",255,0,255);

    public void run(Mat frame) {

        postImage(frame, "Original Picture");
        
        Mat hsvFrame = new Mat();
        Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);
        postImage(hsvFrame, "HSV Converted Image");

        Mat hueFiltered = new Mat();
        ArrayList<Mat> channel = new ArrayList<Mat>();
        Core.split(frame, channel);
        Core.inRange(channel.get(0), new Scalar(minHue.value()), new Scalar(maxHue.value()), hueFiltered);
        //Core.merge(hues,hueFiltered);
        postImage(hueFiltered, "Hue Filtered Image");

        Mat saturationFilter = new Mat();
        Core.inRange(channel.get(1), new Scalar(minSat.value()), new Scalar(maxSat.value()), saturationFilter);
        postImage(saturationFilter,"Saturation Filtered Image");

        Mat valFilter = new Mat();
        Core.inRange(channel.get(2),new Scalar(minVal.value()),new Scalar(maxVal.value()),valFilter);
        postImage(valFilter,"Value Filtered Image");

        Mat filteredImage = new Mat();
        Core.bitwise_and(hueFiltered,saturationFilter,filteredImage);
        postImage(filteredImage,"Filtered Image");

        Mat boxBlur = new Mat();
        Imgproc.blur(filteredImage,boxBlur,new Size(2,2));
        postImage(boxBlur,"Box Blur");

        Mat medBlur = new Mat();
        Imgproc.medianBlur(filteredImage,medBlur, 3);
        postImage(medBlur,"Median Blur");

        Mat gausBlur = new Mat();
        Imgproc.GaussianBlur(filteredImage,gausBlur,new Size(0,0),1,1);
        postImage(gausBlur,"Gaussian Blur");

        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Mat erodeImg = new Mat();
        Imgproc.erode(saturationFilter,erodeImg,kernel);
        postImage(erodeImg,"Eroded Saturation Image");

        Mat dilateImg = new Mat();
        Imgproc.dilate(filteredImage,dilateImg,kernel);
        postImage(dilateImg,"Dilated Filtered Image");

        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(gausBlur,contours,new Mat(),Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_NONE);
        Mat drawn = frame.clone();
        Imgproc.drawContours(drawn,contours,-1,new Scalar(0,255,0),3);
        postImage(drawn,"RETR_EXTERNAL");

        Imgproc.findContours(gausBlur,contours,new Mat(),Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_NONE);
        Mat contourFrame = frame.clone();
        Imgproc.drawContours(contourFrame,contours,-1,new Scalar(0,255,0),3);
        postImage(contourFrame,"RETR_LIST");
    }

}
