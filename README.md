# FGmobileApp

**1. Explanation about the project**

This application is a remote controller to operate a plane simulator app.
This application's GUI translates the user's actions to the simulator known network protocol commands.

The initial view of the app:

![image](https://user-images.githubusercontent.com/73580442/123557103-af89a880-d797-11eb-9241-a8a38a03a94e.png)

The seekbars (one horizontal and one vertical) and the joystick sets the data according to the user's action (as example, moving the joystick upwards).

**2. Explanation about the structure and files arrangement**

According to the MVVm data structure for the mobileApp, the code files in this project are divided into 3 folders: Model, View, ViewModel.
This way represents our work in the right flow of the information which passes through each structure.
The file in the viewModel folder is FGViewModel.java which is in charge of connecting between the socket to the Flight Gear Simulator (model) and the mobile application view (view).
The file in the Model folder is FGModel.java which is in charge of the connection to the Simulator itself.
The other files which are in charge of displaying the visuality of the project.

**3. Prerequisite**

Pre-aquired programs to be installed before initial running: Flight Gear (the flight simulator) to display the plane's movement according to the user.

**4. Further Documentation**

The UML file which represents the connection between the different components in the project:

![image](https://user-images.githubusercontent.com/73580442/123557553-245de200-d79a-11eb-8132-87dabcc94584.png)

**5.Video**

Link to the video of the presentation: https://drive.google.com/file/d/1DLussnJwM9saTJXLhJ0vHegNNSGvRbXT/view?usp=sharing
