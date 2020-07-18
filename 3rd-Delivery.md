## Comments and our answers

*- "The project makes sense [...] but in terms of IoT is basic, there is nothing more than the most fundamental idea of sending and recieving a beacon"* <br>
We don't totally agree with the fact that our project is too basic in terms of IoT. We sustain to have a lot of IoT components and arguments in MuSa:
- The *Data collection and Data analysis*: we collect informations about visitors and users for several reasons like to provide personalized tours and improve those tour through visitor's behavior. TO DO: attuatore delle opere con piu visite
- The *Edge computing*: we pre-process the data collected to send them to the cloud in a better structured way and to save on the number of messages sent, for better exploit the Cloud's free-plan
- The use of two different types of *Boards*
- The use of a *Cloud service*: Microsoft Azure
- The use of *MQTT*: a famous lightweight messaging protocol in IoT for small sensors and mobile devices
- The use of *Bluetooth Low Energy (BLE)* technology
- The use of *Devices* sending beacons, like smartphones

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
We did not change anything in the [Design plan](docs/Design.md) because we had no need to do that. Contrariwise, for what concerns the Architecture and the Evaluation plans, we made some improvements and some minor adjustements.

[Architecture plan](docs/Architecture.md):
- we decided to switch from a Web App to an Android App
- we improved the positioning accuracy through the use of a Kalman Filter and RSSI
- TO DO: ESP32 perchè Stefano aveva quelle????

[Evaluation plan](docs/Evaluation.md) we added more information about:
- TO DO

## Techincal work done since the 2nd delivery
TO DO

## Evaluation conducted since the 2nd delivery 
TO DO

## Functionalities that are still missing and which we aspect we did not manage to evaluate
TO DO