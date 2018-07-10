import java.io.File;

import javafx.stage.Stage;
import stuyvision.VisionModule;
import stuyvision.ModuleRunner;
import stuyvision.capture.CaptureSource.ResizeDimension;
import stuyvision.capture.DeviceCaptureSource;
import stuyvision.capture.VideoCaptureSource;
import stuyvision.capture.ImageCaptureSource;
import stuyvision.gui.IntegerSliderVariable;
import stuyvision.gui.VisionGui;

public class Main {
    public static void main(String[] args) {
        ModuleRunner runner = new ModuleRunner(5);
        processLessonZero(runner);
        //processCamera(runner);
        //processSamples(runner);
        VisionGui.begin(args, runner);
        IntegerSliderVariable minHue = new IntegerSliderVariable("Hue Min", 86, 0, 255);
        IntegerSliderVariable maxHue = new IntegerSliderVariable("Hue Max", 93, 0, 255);
    }

    public static void processCamera(ModuleRunner runner) {
        runner.addMapping(new DeviceCaptureSource(0), new Vision());
        runner.addMapping(new ImageCaptureSource(imagesDir() + "colorwheel.png"), new Vision());
    }

    public static void processSamples(ModuleRunner runner) {
        System.out.println("Getting images from " + imagesDir());
        File directory = new File(imagesDir());
        File[] directoryListing = directory.listFiles();
        for (int i = 16; i < directoryListing.length && i < 20; i++) {
            // NOTE: there is no 1.jpg or 2.jpg in the sample images. There is a 0.jpg
            String path = imagesDir() + directoryListing[i].getName();
            runner.addMapping(new ImageCaptureSource(path), new Vision());
        }
        String colorwheel = imagesDir() + "colorwheel.png";
        runner.addMapping(new ImageCaptureSource(colorwheel), new Vision());
    }

    public static void processLessonZero(ModuleRunner runner) {
        File[] directoryListing = listImagesInDir(imagesDir() + "lesson0/");
        // Use the function below to list the images for lesson 0
        // And then map all the images, using your class to process it.
        runner.addMapping(new ImageCaptureSource(imagesDir() + "lesson0/0.jpg"), new LessonZeroVision());
        runner.addMapping(new ImageCaptureSource(imagesDir() + "colorwheel.png"), new LessonZeroVision());
    }

    // path is a relative path
    // e.g. to refer to sampleImages you pass in "sampleImages/"
    public static File[] listImagesInDir(String imagesDir) {
        System.out.println("Getting images from " + imagesDir);
        File directory = new File(imagesDir);
        return directory.listFiles();
    }

    public static String imagesDir() {
        String imagesDir = Main.class.getResource("").getPath();
        imagesDir = imagesDir.substring(0, imagesDir.length() - 6);
        imagesDir += "sampleImages/";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            imagesDir = imagesDir.substring(1); // Chop off leading / that appears before C:
        }
        return imagesDir;
    }
}
