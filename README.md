Gesture recognition for Exercise
=================

Balázs Pete's final year project in part fulfilment of the degree of BSc Hons. Computer Science at University College Dublin.


Code licensed under the MIT License, see LICENSE.txt for more information.

-----------------

Project Specification
=================

The goal of the project is to develop a program which will recognize 3D gestures made by human users. The gestures are input to the system using a handheld tracking device. The program receives a timestamped list of 3D coordinates representing the position of the tracking device over time. The program analyses these coordinates and determines the gesture that the user was making, e.g. move up/down/left/right, single/double tap, rotate clockwise/anticlockwise. The difficulty in gesture recognition is dealing with the variability of human motion. The goal of the project is to accurately distinguish between different gestures and non-gestures.

Note, the software is to be designed for use with a 3D input device (e.g, a 3D mouse) not a vision-based input device. The 3D input device is either hand held or attached to the user's body.

Mandatory
---------

The program should take data input in the form of a text file with a list of timestamps and 3D co-ordinates.

The program should analyze the text file and accurately detect 10 different control-type gestures made by a variety of users, e.g. start, stop, left, right. The gesture recognized and the time of the gesture should be output to a text file.

The accuracy of the program should be verified in user tests.

Note that the program should be suitable for online gesture detection. That is, gestures should be detected as soon completed, based on previous 3D positions, not future 3D positions. 

Discretionary
----------

The program should be extended to detect 10 larger exercise-type movements, e.g. jumping. As well as detecting the movement type, the program should output statistics about the movement, e.g. height, speed. These statistics should be accurate, even though the 3D position measurements are noisy.

Again, the accuracy of the program should be verified in user tests.

Exceptional
-----------
 
An exercise monitoring system should be developed which only uses voice prompts and gestures.

The voice prompts should tell the user what to do. The control gestures should provide user feedback (e.g. yes, no, option 1,2,3, abort). The exercise movements should be measured and the statistics feedback via voice prompts.

No other input or output device should be needed, i.e. no keyboards, no screens.

Due to the limitations of the current prototype, the 3D positions may have to be recorded in advance.

Aims
==========

- [x] Collect data for 20 different gestures
- [x] Recognise 10 control-type gestures
- [x] Test the recognition capability for the control-type gestures
- [x] Recognise 10 exercise-type gestures
- [x] Test the recognition capability for the exercise-type gestures
- [x] Analyse the gesture data, and give statistics (such as length, speed, height, distance)
- [ ] Implement heuristic methods to ignore incorrect recognition, or to select one gesture in case of the detection of multiple gestures at the same time
- [ ] Extend system into an exercise monitoring application

------------------------

Components
==========

FYPLibrary
----------

A library of shared components.


Gesture Grapher
----------

Tool to visualise the recorded data and to identify and extract gesture patterns

To start the tool, run
> GestureGrapher/ui.MainWindow.java

or use the packaged version:
> GestureGrapher.jar



GestureCreator
-----------

Tool to create a gesture model using the extracted gesture patterns

To start the tool, run
> GestureCreator/ui.MainWindow.java

or use the packaged version:
> GestureCreator.jar



GestureRecogniser
-----------

Application to recognise gestures from an input (file)

To start the program, run
> GestureRecogniser/GestureRecogniser.java

or use the packaged version:
> GestureRecogniser.jar


----

The collected data can be found under FYPLibrary/Data








