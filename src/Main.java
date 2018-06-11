import java.io.File;

import stuyvision.VisionModule;
import stuyvision.ModuleRunner;
import stuyvision.capture.CaptureSource.ResizeDimension;
import stuyvision.capture.DeviceCaptureSource;
import stuyvision.capture.VideoCaptureSource;
import stuyvision.capture.ImageCaptureSource;
import stuyvision.gui.VisionGui;

public class Main {
    public static void main(String[] args) {
        ModuleRunner runner = new ModuleRunner(5);
        processLessonZero(runner);
        VisionGui.begin(args, runner);
    }

    public static void processCamera(ModuleRunner runner) {
        runner.addMapping(new DeviceCaptureSource(0), new Vision());
        runner.addMapping(new ImageCaptureSource("sampleImages/colorwheel.png"), new Vision());
    }

    public static void processSamples(ModuleRunner runner) {
        String imagesDir = Main.class.getResource("").getPath() + "sampleImages/";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            imagesDir = imagesDir.substring(1); // Chop off leading / that appears before C:
        }
        System.out.println("Getting images from " + imagesDir);
        File directory = new File(imagesDir);
        File[] directoryListing = directory.listFiles();
        for (int i = 16; i < directoryListing.length && i < 20; i++) {
            // NOTE: there is no 1.jpg or 2.jpg in the sample images. There is a 0.jpg
            String path = imagesDir + directoryListing[i].getName();
            runner.addMapping(new ImageCaptureSource(path), new Vision());
        }
        String colorwheel = imagesDir + "colorwheel.png";
        runner.addMapping(new ImageCaptureSource(colorwheel), new Vision());
    }

    public static void processLessonZero(ModuleRunner runner) {
        String imagesDir = Main.class.getResource("").getPath();
        imagesDir = imagesDir.substring(0, imagesDir.length() - 6);
        imagesDir += "sampleImages/lesson0/";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            imagesDir = imagesDir.substring(1); // Chop off leading / that appears before C:
        }
        File[] directoryListing = listImagesInDir(imagesDir);
        

        for (int i = 0; i < directoryListing.length && i < 10; i++) {
            String path = imagesDir + directoryListing[i].getName();
            runner.addMapping(new ImageCaptureSource(path), new LessonZeroVision());
        }
        String colorwheel = imagesDir + "colorwheel.png";
        runner.addMapping(new ImageCaptureSource(colorwheel), new LessonZeroVision());

    } 
    // path is a relative path
    // e.g. to refer to sampleImages you pass in "sampleImages/"
    public static File[] listImagesInDir(String imagesDir) {
        System.out.println("Getting images from " + imagesDir);
        File directory = new File(imagesDir);
        return directory.listFiles();
    }
}
