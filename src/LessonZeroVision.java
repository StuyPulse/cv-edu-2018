import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


import org.opencv.core.Mat;

public class LessonZeroVision extends VisionModule {

    public void run(Mat frame) {
        postImage(frame, "Original Picture");
        
        Mat hsvFrame = new Mat();
        Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);
        postImage(hsvFrame, "HSV Converted Image");

        Mat hueFiltered = new Mat();
        ArrayList<Mat> hues = new ArrayList<Mat>();
        Core.split(frame, hues);
        Core.inRange(hues.get(0), new Scalar(86), new Scalar(93), hueFiltered);
        //Core.merge(hues,hueFiltered);
        postImage(hueFiltered, "Hue Filtered Image");

        Mat filterImage2 = new Mat();
        ArrayList<Mat> splitImg = new ArrayList<Mat>();
        Core.split(hueFiltered, splitImg);
        Core.inRange(splitImg.get(2), new Scalar(83), new Scalar(255), filterImage2);
        postImage(filterImage2,"Partly Filtered Image");
        
        ArrayList<Mat> channel = new ArrayList<Mat>();
        Mat filteredImage = new Mat();
        Core.split(frame, channel);
        Core.inRange(channel.get(0), new Scalar(86), new Scalar(93), channel.get(0));
        Core.inRange(channel.get(1), new Scalar(0), new Scalar(255), channel.get(1));
        Core.inRange(channel.get(2), new Scalar(83), new Scalar(255), channel.get(2));
        Core.merge(channel, filteredImage);
        postImage(filteredImage, "Filtered Image");
    }

}
