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
        processCamera(runner);
        //processSamples(runner);
        //processLessonZero(runner);
        VisionGui.begin(args, runner);
    }

    public static void processCamera(ModuleRunner runner) {
        runner.addMapping(new DeviceCaptureSource(0), new Vision());
        runner.addMapping(new ImageCaptureSource("../sampleImages/colorwheel.png"), new Vision());
    }

    public static void processSamples(ModuleRunner runner) {
        //String imagesDir = Main.class.getResource("").getPath() + "../sampleImages/";
        String imagesDir = "C:/Users/George Liang/Desktop/cvstuff/cv-edu-2018/sampleImages/";
        /*
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            imagesDir = imagesDir.substring(1); // Chop off leading / that appears before C:
            System.out.println(imagesDir);
        }
        */
        System.out.println("Getting images from " + imagesDir);
        File directory = new File(imagesDir);
        File[] directoryListing = directory.listFiles();
        for (int i = 16; i < directoryListing.length && i < 20; i++) {
            // NOTE: there is no 1.jpg or 2.jpg in the sample images. There is a 0.jpg
            String path = imagesDir + directoryListing[i].getName();
            System.out.println(path);
            runner.addMapping(new ImageCaptureSource(path), new Vision());
        }
        String colorwheel = imagesDir + "colorwheel.png";
        runner.addMapping(new ImageCaptureSource(colorwheel), new Vision());
    }

  public static void processLessonZero(ModuleRunner runner){
    String imagesDir = "C:/Users/George Liang/Desktop/cvstuff/cv-edu-2018/sampleImages/lesson0/";
    File[] directoryListing = listImagesInDir(imagesDir);
    for (int x = 0; x < directoryListing.length; x++){
      //System.out.println(directoryListing[x]);
      System.out.println(imagesDir + directoryListing[x].getName());
      String path = imagesDir + directoryListing[x].getName();
      runner.addMapping(new ImageCaptureSource(path), new LessonZeroVision());
    }
  }

  public static File[] listImagesInDir(String imagesDir) {
    System.out.println("Getting images from " + imagesDir);
    File directory = new File(imagesDir);
    return directory.listFiles();
  }
}
