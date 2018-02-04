# cv-edu-2018

CV Education for the 2018 season

OpenCV needs to be installed at `lib/opencv-3.2.0`.

On Linux, you can install OpenCV with the
`install-opencv-unix.sh` script.  There are more detailed
instructions on this in [the README of
github.com/Team694/stuyvision-lib](https://github.com/Team694/stuyvision-lib#installing-opencv-on-your-machine).
Once CV is setup, run `make` to compile and then `make run` to run the project.
To set up on Windows, see `windows-setup.md` for instructions.

## Reference

- ReadTheDocs [tutorials](http://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html)
  for OpenCV with Java (and GUIs with JavaFX).

- Official OpenCV 3.1.0 [JavaDocs](http://docs.opencv.org/java/3.1.0/). (There are no JavaDocs for OpenCV 3.2.0.)

- Official OpenCV 3.2.0 [documentation](http://docs.opencv.org/3.2.0/). This has
  much more detail than the JavaDocs, but is intended for
  those using OpenCV with C++. There are no examples in Java
 (all are C/C++).

More resources
[here](https://github.com/Team694/stuyvision-lib#other-cv-resources), in
stuyvision-lib.

#### Note:

If you're working on your own laptop and you have an installation of OpenCV 3.2.0 elsewhere on your laptop, you don't need to install it again. Instead, create a symbolic link with this command:

```bash
$ ln -s /path/to/OpenCV-3.2.0/installation lib/opencv-3.2.0
```

You may also do the same with your `jfxrt.jar` (which should be installed at `/usr/lib/jvm/default-java/jre/lib/ext/jfxrt.jar` if you are running Ubuntu.)

```bash
$ ln -s /path/to/jfxrt.jar lib/jfxrt.jar
```

### Working on the school desktops

To develop on the desktops, log in to either a Guest session or your own
account, then clone this repo and set up your environment:

```bash
$ git clone https://github.com/Team694/cv-edu-2018
$ cd cv-edu-2018
$ source setup-workstation.sh
```

As usual, use `make` to build:

```bash
$ make
# code compiles
$ make run
# code runs. Ctrl-C to interrupt (like force quit)
```

If you encounter an error while running `make run` similar to the one below:

```
Exception in thread "main" java.lang.UnsatisfiedLinkError: /path/to/cv-edu-2018/lib/opencv-3.2.0/build/lib/libopencv_java300.so: libopencv_photo.so.3.0: cannot open shared object file: No such file or directory
```

you may need to run the command:

```bash
$ export LD_LIBRARY_PATH=/path/to/cv-edu-2018/lib/opencv-3.2.0/build/lib/
```

### StuyVision methods:

Documentation written by Wilson for relevant StuyVision methods can be found [here](stuyvision-docs.md).

### OpenCV functions:

Documentation written by Wilson for relevant OpenCV functions can be found [here](opencv-docs.md).

## Lessons

The links to the lessons are here:

* [Lesson 0](lessons/lesson-0.md)
* [Lesson 1](lessons/lesson-1.md)

These lessons may be done on your own time, but you can always contact Helen on Slack if you need help!
