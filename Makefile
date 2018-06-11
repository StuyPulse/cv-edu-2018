PROJECT_ROOT = $(shell pwd)

LIB = $(PROJECT_ROOT)/lib

SRC = $(PROJECT_ROOT)/src
BIN = $(PROJECT_ROOT)/build

STUYVISION = $(LIB)/stuyvision.jar
JAVAFX = $(LIB)/jfxrt.jar

OPENCV = $(LIB)/opencv-3.4.1
OPENCV_SCH = /var/tmp/robo-software/opencv-3.4.1

OPENCV_JAR = $(OPENCV_SCH)/build/bin/:$(OPENCV_SCH)/share/OpenCV/java/opencv-341.jar:$(OPENCV)/build/bin/opencv-341.jar:/usr/local/Cellar/opencv3/3.4.1/share/OpenCV/java/opencv-341.jar
OPENCV_LIBS = $(OPENCV_SCH)/build/lib/:$(OPENCV_SCH)/share/OpenCV/java/:$(OPENCV)/build/lib/:$(PROJECT_ROOT)/java/x64:/usr/local/Cellar/opencv3/3.4.1/share/OpenCV/java/

JAVAC = javac
CLASSPATH = $(BIN):$(STUYVISION):$(JAVAFX):$(OPENCV_JAR)
JAVA_FLAGS = -g -d $(BIN) -cp $(CLASSPATH)
COMPILE = $(JAVAC) $(JAVA_FLAGS)

.DEFAULT_GOAL := all

class=Main

all: clean init-bin
	find $(SRC) -name '*.java' -print | xargs $(COMPILE)

clean:
	rm -rf $(BIN)

init-bin:
	mkdir $(BIN)

run:
	java -cp $(CLASSPATH) -Djava.library.path=$(OPENCV_LIBS) $(class)

