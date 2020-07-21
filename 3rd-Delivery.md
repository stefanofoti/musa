## Comments and our answers

*- "The project makes sense [...] but in terms of IoT is basic, there is nothing more than the most fundamental idea of sending and receiving a beacon"* <br>
We don't totally agree with the fact that our project is too basic in terms of IoT. We sustain to have a lot of IoT components and arguments in MuSa:
- The *Data collection and Data analysis*: we collect information about visitors and users for several reasons like to provide personalized tours and improve those tour through visitor's behavior
- The *Edge computing*: we pre-process the data collected to send them to the cloud in a better-structured way and to save on the number of messages sent, for better exploit the Cloud's free-plan
- The use of two different types of *Boards*
- The use of a *Cloud service*: Microsoft Azure
- The use of *MQTT*: a famous lightweight messaging protocol in IoT for small sensors and mobile devices
- The use of *Bluetooth Low Energy (BLE)* technology
- The use of *Devices* sending beacons, like smartphones
- A led Actuator, that shows through a green light the ten artworks most appreciated in the current day

We analyze even deeper the IoT part in our [Architecture document](docs/Architecture.md).
<br>
<br>
*- "I'm not sure about the redundancy [...] I think is too much"* <br>
We honestly thought that the motivation of having a second Raspberry Pi, which is to have a prepared solution to our single point of failure, was clear in our documentation, but we will make sure that in the final delivery it will be even clearer :-).
<br>
<br>
*- "The idea of carrying out locally is interesting [...] but is not described properly in the document"* <br>As before, we will make our ideas clearer in the final delivery. Very briefly, through Edge-computing, we carry out something locally because in this way we have data better structured and we economize on sending messages to Azure Cloud, so we can better exploit the free-plan.
<br>
<br>
*- "Using RSSI is not difficult and it would be very nice"*<br>
One of the general comments about our project was that the BLE technology is not very exciting for indoor positioning, also because sometimes it is not very accurate. As suggested, we implemented the use of RSSI crossed to the inclusion of a Kalman Filter, that is an algorithm that uses a series of measurements observed over time, containing statistical noise and other inaccuracies, and produces estimates of unknown variables that tend to be more accurate than those based on a single measurement. We have dedicated paragraphs to RSSI and Kalman Filter in our [Architecture document](docs/Architecture.md).
<br>

## Changes made to design, architecture and evaluation
For what concerns the Design, the Architecture and the Evaluation plans, we made some improvements and some minor adjustements.

In the [Design plan](docs/Design.md):
- we implemented the minor functionality of a led actuator that highlights the most appreciated artworks in the current day

In the [Architecture plan](docs/Architecture.md):
- we decided to switch from a Web App to an Android App
- we improved the positioning accuracy through the use of a Kalman Filter and RSSI
- we clarified why we are using a ESP32 instead of an STM Nucleo
- we clarifeid that we are using a DbContext through the Entity Framework for the demo, insteaf of Azure Database
- we made a brief discussion about the led actuator

In the [Evaluation plan](docs/Evaluation.md):
- we measured the power consumption of the boards
- we filled the table containing the ISO standards for the overall evaluation
- we presented the demo to a group of people and gathered their feedback through the survey in the app
- we did another check on the backend using CodeCity

**Please, notice that** in the documents linked above you can find dedicated paragraphs that explain deeper the reasons and the details about our choises. You have also an Index at the top of the documents for finding easily what you need!

## Techincal work done since the 2nd delivery
For the final delivery we brought our project to a production-ready state:
- we implemented all the needed functions in the back-end
- we completed the code that runs in the Raspberry-Pi and in the other boards that recieve the beacons
- we developed a working Android app to let the users to use MuSa

## Evaluation conducted since the 2nd delivery
- we measured the power consumption of the boards
- we filled the table containing the ISO standards for the overall evaluation
- we presented the demo to a group of people and gathered their feedback through the survey in the app
- we did another check on the backend using CodeCity

## Functionalities that are still missing and which we aspect we did not manage to evaluate
As we declared from the start, we will not implement the Machine Learning aspect, which as the goal to improve the personalized tours through the user's experiences and through their feedback. It would be very interesting to implement this part in the future: it would make MuSa complete. <br/>
We also will not implement the "Moment method" evaluation of the user experience that was supposed to be done through the [PrEmo Tool](https://www.premotool.com/), since it is a commercial tool.<br/>
In the end, we didn't use the Azure Database, for our demo we used a DbContext through the Entity Framework since for the purposes that we set for our delivery, it is good and we already tuned it up for managing positioning on BE side. In a real deployment, it is easy to integrate the Azure Database or any external database/data source.<br/>
An interesting feature we weren't going to implement, but that would be very useful, is using the accelerometer of the mobile phone to detect when a person is standing still or walking. It would better tune data since while visitors move in the museum, their phones send beacons, and an artwork's board may catch them even if the user hasn't stopped to look at it, but he's just passing by to reach another piece of art.<br/>
