# Architecture

### Index
- [Introduction](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#introduction)
- [Architecture and components](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#architecture-and-components)
  - [List of components](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#list)
    - [Software](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#software)
    - [Hardware](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#hardware)
    - [Technologies](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#technologies)
      - [Clarification about the ESP32 board](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#clarification-about-the-ESP32-board)
      - [Clarification about the Azure Database](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#clarification-about-Azure-Database)
- [IoT aspects](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#iot-aspects)
- [Sensor Network](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#sensor-network)
  - [About device-to-cloud messages](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#about-device-to-cloud-messages)
   - [About the main board's messages](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#about-the-main-boards-messages)
   - [About the choice of having a second Raspberry Pi](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#about-the-choice-of-having-a-second-raspberry-pi)
  - [The RGB Led actuator](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#the-rgb-led-actuator)
- [Backend and smartphone frontend](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#backend-and-smartphone-front-end)
  - [Keeping track of user's visit](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#keeping-track-of-users-visit)
  - [Frontend](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#frontend)
  - [From an Angular web-app to an Android application](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#from-an-angular-web-app-to-an-android-application)
- [Cloud](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#cloud)
- [About the choice to use Bluetooth Low Energy](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#about-the-choice-to-use-bluetooth-low-energy)
  - [RSSI and Kalman Filter](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#rssi-and-kalman-filter)
- [References](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#References)


------------------------------------------------------------------------------------------------------------------------------------------------------
### Introduction

This application needs a lot of components to work properly. The three main parts are:<br/>
- the sensor network<br/>
- the interaction between the client on the smartphone and the backend on the cloud<br/>
- Cloud services<br/>
<br/>
Near every piece of art or cluster of artworks, there is a board, which communicates with the user's phone to understand how much time he spends near it. The data are sent to the cloud platform to be stored and elaborated. A machine learning algorithm creates customized tours according to the
information collected and on the reports generated for each user at the end of the visit. The user's smartphone is connected with the backend to provide interaction with the visitor.<br/>

------------------------------------------------------------------------------------------------------------------------------------------------------

### Architecture and components

The architecture is the following:<br/>

![image](src/architecture/architecture.png)

### List
#### Hardware
- STM-Nucleo boards (x pieces of art or cluster of artworks)<br/>
- 2 Raspberry Pi boards (one to be the gateway, the other for backup)<br/>
- Wi-Fi and BLE hardware for STM-Nucleo*<br/>
- user's smartphone (Android, with BLE supported)<br/>
- ESP32 Board* ([clarification about the ESP32 board](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#clarification-about-the-ESP32-board))
- Led RGB

#### Software
- Azure Cloud Platform:<br/>
  - Azure Event Hub<br/>
  - Azure IoT Hub<br/>
  - Azure Database (*not implemented*) ([clarification about the Database](https://github.com/stefanofoti/musa/blob/master/docs/Architecture.md#Clarification-about-Azure-Database))<br/>
  - Azure Machine Learning (*not implemented*)<br/>
  - Azure Web App Service Plan<br/>
- Google Docs (*not implemented*)<br/>
- Python, C#, Java, Android Studio


#### Technologies
- Bluetooth Low Energy (BLE)<br/>
- MQTT-SN<br/>
- MQTT<br/>

##### Clarification about the ESP32 board
We are conscious that in our architecture plan we mentioned the STM-Nucleo as the chosen board. We are also conscious that the STM-Nucleo is mandatory for this project. Anyway, one of the team members had a personal ESP32 board already available and due to the restrictions of this particular period (a global pandemic that made difficult to us to get a STM-Nucleo from our laboratory or to find one in a reasonable time), we decided to use his ESP32 for the final delivery demo.

##### Clarification about Azure Database
Even if we declared to use the Azure Database, for our demo we used a DbContext through the Entity Framework even for the artworks' details; for the purposes that we set for our delivery, it is good and we already tuned it up for managing positioning on BE side. In a real deployment, it is easy to integrate the Azure Database or any external database/data source.

------------------------------------------------------------------------------------------------------------------------------------------------------

### IoT aspects
We argue that MuSa project is filled with IoT components and topics.
- The *Data collection and Data analysis* are two of the main goals of the IoT. With MuSa, we collect information about visitors and users for several reasons like to provide personalized tours and improve those tours through visitor's behavior. Even if it is not planned in our project, the curators may use the collected data also for several other aspects, like reorganize the museum's artworks' positioning, simply analyzing those data. The backend already exposes a good number of REST services to get even the row data we catch.
- The *Edge computing* is a widespread computing paradigm used in IoT; we pre-process the data collected to send them to the cloud in a better-structured way and to save on the number of messages sent, to better exploit the Cloud's free-plan (refer to Evaluation document for more details). During this processing, we also apply a specific algorithm to reduce noise in RSSI values, and so track users in a more precise way.
- We use two different types of *Boards*: a single-board computer (like a Raspberry Pi) and a SoC microcontroller (like ESP32).
- The use of a *Cloud service*: Microsoft Azure, the Azure IoT hub, and Event hub.
- The use of *MQTT*: a famous lightweight messaging protocol in IoT for small sensors and mobile devices.
- The use of *Bluetooth Low Energy (BLE)* technology.
- The use of *Devices* for beacons advertising, that in our case are smartphones.
- A led Actuator, that shows through a green light the ten artworks most appreciated in the current day.
The RGB led is turned on for green color by the gateway during data processing, using MQTT messages on topics /Musa/ARTWORK_ID/led and payload G_ON/G_OFF to turn on and off the diode as needed. The RED color is reserved for issues, startup phase, and status, so it is managed directly by the board.xx.  

------------------------------------------------------------------------------------------------------------------------------------------------------

### Sensor network

Near each piece of art or cluster of artworks, we have an ESP32 board, communicating with its gateway through MQTT and catching beacons advertised by visitor's smartphones over Bluetooth Low Energy (BLE). But how do those boards work?
At the startup, every board connects to WiFI, connects to the MQTT broker, and publishes the first message on a specified topic to notify to be online. What is the topic? Every board publishes messages on a different topic, i.e. "/musa/ID5", where ID5 is the unique identifier of that artwork in the whole system. Once the board is online it just starts the beacon scan in several iterations, sending messages to the gateway. Due to noise and the next processing, it is not helpful to send a message for each iteration. We can group messages for iterations range and send "bigger" messages. How big are the messages? How many iterations? To read more details about this part, please refer to Evaluation document. Let us make two considerations: our board does not have enough space to send too big messages, and if we group too many iterations we get bad results in terms of response time and reliability of results. How are those messages board-to-gateway made? The body is just a simple JSON Array, containing a list of iterations. For each iteration, we have a tuple beacon identifier-RSSI value in the JSON for each detected beacon in that iteration, so in case of two iterations we have:<br/>

[<br/>
&nbsp;{<br/>
&nbsp;&nbsp;"iteration": 0,<br/>
&nbsp;&nbsp;"beaconID_0": -72,<br/>
&nbsp;&nbsp;"beaconID_1": -56,<br/>
&nbsp;&nbsp;"beaconID_2": -53<br/>
&nbsp;},<br/>
&nbsp;{<br/>
&nbsp;&nbsp;"iteration": 1,<br/>
&nbsp;&nbsp;"beaconID_0": -71,<br/>
&nbsp;&nbsp;"beaconID_2": -55,<br/>
&nbsp;&nbsp;"beaconID_3": -60<br/>
&nbsp;}<br/>
]<br/>
<br/>
The number of iterations is dynamic and depending on the number of beacons detected by the board. In the case of many visitors inside the museum, the board can even do only one iteration due to available space reasons. If this happens, the maximum number of users we can manage is depending on the available space on the board, again refer to the evaluation document for such estimations. In any case, we also fixed a top boundary as the the maximum number of iterations.
As you may think, we put a lot of code and libraries on our boards, since we have to connect to WiFi, use MQTT, use BLE, produce JSON. In order to use the small space in the best possible custom way, we partitioned ad hoc our boards and set a custom size for sent messages.
Every board has also a RED status led, that turns on in case of error (i.e. WiFi disconnection, broker not reachable, ...) and blinks at the startup until the connection to both WiFi and Broker is successful.  

In the museum there is also a Raspberry Pi board that serves as a gateway to process, structure and forward the data collected to the cloud structure.<br/>
We decided to use a single-board computer because we needed a little more power and memory to execute the following tasks: catch row messages sent by each artworks board, process and store it in a suitable format, apply the kalman filter for each user, detect the closest artwork for each user, connect to Azure endpoint and forward data to our Azure IoT hub. All of them require space since we get messages from all the boards,  and power, since we process and compute Kalman filter for each one of them.

 <br/>
We noticed that we didn't need to have a single board near each piece of art: if some artworks are very close to each other, we can treat them as a single artwork group. In fact, what we really need to do is proximity detection, that is, understand the pieces of art which are nearest to the user in real-time to provide him valuable and funny information about them, and to understand if he's following the tour proposed by MuSa.<br/>
<br/>

![image](src/architecture/Sensor_network_architecture.png)

#### About device-to-cloud messages
The main idea is the following: the user's smartphone enables beacon advertising, the artworks' boards catch them periodically. Every board sends all the beacons detected in all the iterations made since the last sent message, as we saw in the previous paragraph. The messages are processed as we saw, and then are sent to the Azure IoT hub by the gateway using Azure best practices device-client. The report sent every 5 seconds looks like this:<br/>

{<br/>
&nbsp;"timestamp":"2020-2xxx",<br/>
&nbsp;"users":[{<br/>
&nbsp;&nbsp;"id":"beaconID_0",<br/>
&nbsp;&nbsp;"artworks":ID1<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;"id":"beaconID_1",<br/>
&nbsp;&nbsp;"artworks":ID3<br/>
&nbsp;&nbsp;}]<br/>
}<br/>

It is again a JSON object. Each message has a timestamp and a list of users, which contains, for each element, the id of the user (the beaconID got through the artworks board) and the last detected position for that user.<br/>

##### About the main board's messages
Notice that we decided to use a main board as a gateway because, besides the fact that in this way we can send better pre-processed data by doing some edge computing, we can have a significant saving in terms of the number of messages sent. Think about the fact that the free plan of our cloud service, Microsoft Azure, provides 8000 messages-per-day; with a main board that sends a single message that groups all the messages received from every single board, considering 1 message every 5 seconds, MuSa can work about 11 hours. If every board sends each message by itself directly to the cloud, the free plan will expire in a few minutes. (More details in the [Evaluation document](https://github.com/stefanofoti/musa/blob/master/docs/Evaluation.md#sensor-networks-reliability))<br/>

##### About the choice of having a second Raspberry Pi
For reliability reasons (more details in the [Evaluation document](https://github.com/stefanofoti/musa/blob/master/docs/Evaluation.md#sensor-networks-reliability)), we propose to keep also another Raspberry board in hot standby, since having a main gateway board exposes MuSa to the risk of having a single point of failure; when the main gateway forwards the report to the cloud, also the second "backup board" receives it. If it doesn't receive any report for some time, it will assume that the main gateway has suffered a failure, and it will take its place to avoid the stop of the service. In case there are no strict reliability requirements, ignore this paragraph.<br/>

#### The RGB Led actuator
We decided to implement an RGB Led actuator, positioned on each board, to show the most appreciated artworks in the current day (easily switchable to a weekly or monthly period). The Led is lighted up with a green light if an artwork is in the top ten of the most liked operas, based on how much time each visitor spends in front of it. It also lights up or blinks with a red light if there are connection issues.
The RGB led is turned on for green color by the gateway during data processing, using MQTT messages on topics /musa/ARTWORK_ID/led and payload G_ON/G_OFF to turn on and off the diode as needed. The RED color is reserved for issues, startup phase and status, so it is managed directly by the board.

------------------------------------------------------------------------------------------------------------------------------------------------------

### Backend and smartphone front-end

The backend of the application lives in the cloud and takes care of the interaction with the user's smartphone. MuSa accompanies the visitor, proposing him a personalized tour, providing information about the different artworks, and making sure he's enjoying the itinerary.<br/>
When the user arrives at the museum, he download the Android app and uses it until the end of the visit. At the end of the tour, the user is asked to fill in a survey to provide feedback about the quality of the service. These answers can be stored in a Google Document, in a way that makes them easily accessible to the machine learning algorithm.
The backend also manages and stores into the well known Main Memory database (for next machine learning analysis and statistics) the position of each user.
<br/>

#### Keeping track of the user's visit

A message that arrives at the cloud from the gateway board is sent to the backend thanks to Azure's Event Hub. The application has for each user a record inside the DbContext, which is a database in the used main memory, where the messages relative to that specific user are stored. Each time a new report arrives from the gateway board, for each user the application checks if there is a record for that user near the same artwork, if there isn't a new one is initialized. Corresponding to each user, there is a list of the pieces of art visited: for each piece of art there is also an integer that keeps track of the seconds a user has spent looking at that artwork.<br/>
The tuple corresponding to the user is inspected: if the last artwork visited according to the Context is the same as the one in the tuple, the integer is increased by one, otherwise, a new element is appended to the list.<br/>
For example, after receiving the previous message, two records for the two users exist in the db context:<br/>

- Record 1:<br/>
  "id":"beaconID_0",<br/>
  "list":[{<br/>
  &nbsp;"ID1":5<br/>
  &nbsp;}]<br/>

- Record 2:<br/>
  "id":"beaconID_1",<br/>
  "list":[{<br/>
  &nbsp;"ID3":5<br/>
  &nbsp;}]<br/>

If a new message like this arrives:<br/>

{<br/>
&nbsp;"timestamp":"2020-2xxx",<br/>
&nbsp;"users":[{<br/>
&nbsp;&nbsp;"id":"beaconID_0",<br/>
&nbsp;&nbsp;"artworks":ID1<br/>
&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;"id":"beaconID_1",<br/>
&nbsp;&nbsp;"artworks":ID4<br/>
&nbsp;&nbsp;}]<br/>
}<br/>

the records would be updated in this way:<br/>

- Record 1:<br/>
  "id":"beaconID_0",<br/>
  "list":[{<br/>
  &nbsp;"ID1":10,<br/>
  &nbsp;}]<br/>

- Record 2:<br/>
  "id":"beaconID_1",<br/>
  "list":[{<br/>
  &nbsp;"ID3":5,<br/>
  &nbsp;"ID4":5<br/>
  &nbsp;}]<br/>

Please notice that from Record 1 we can deduce that the user beaconID_0 did not move from artwork "ID1". Thanks to duplicates in the list we can have an understanding of the path the user is following while visiting the museum, so we can appreciate the fact a user can come back to look at a certain artwork again; in other words, we take care also of this kind of details.<br/>
*Not implemented for the final delivery*: when a user terminates his tour, the list corresponding to him is saved to the Google Doc that is the dataset for the machine learning algorithm, together with the profile of the user for further tuning of the tours (using this data we can understand which pieces of art were most liked by a specific type of user).<br/>

#### Frontend

If a user decides he only wants to help collect data, the only thing that MuSa will do is essentially send beacons, and all the work will be done by the rest of the architecture described in a completely transparent way for the user, who will not be disturbed in the slightest.<br/>
If a user wants to follow MuSa for a personalized tour, the application firstly will present the visitor with a survey to outline his profile. Once the type of user is identified, MuSa asks for the backend an appropriate tour, which will be fetched from it, where all the tours are stored (at least, for our demo), and will propose it to the user. During the itinerary, the frontend, in addition to periodically send beacons, can ask the backend for information about the user's current location (the nearest piece of art), and the interaction will be dealt with providing information about it.<br/>
The Android app was written following a Model-View-Controller architecture.<br/>
#### From an Angular web-app to an Android application

Even if at the beginning we expected to expose the frontend through an Angular web-app, it has been necessary to move towards an Android application, due to some problems. The main problem has been that using beacons was not easy at all through a web app: for example, every time that a user encountered a new board, he was explicitly asked to authorize the board to receive beacons, making the user's experience too intense and annoying. Fortunately, in one of our questionnaires, we asked people if they would download an app at their arrival to the museum for having better performances, and they answered in enough positive way. <br/>
<div align="center"><img src="src/architecture/app_question.png"/></div>

------------------------------------------------------------------------------------------------------------------------------------------------------

### Cloud

Microsoft Azure is a crowded place: here we can find the IoT Hub, the Event Hub, our application code, backend, and frontend, with its database and the (*not implemented*) machine learning algorithm.<br/>
In a real deployment, the database contains information about the pieces of art and when the application needs to present the user a tour or details about an artwork, it will make a query to it. In our demo, the information is stored in the DbContext, which also takes care also to provide them to the application.<br/>
(*Not implemented*) Azure Machine Learning takes as input the dataset saved on the Google Doc, elaborates it creating the tours for the different types of users (the personas we identified), and pushes them into the database of the application. It runs as a batch.
<br/>

------------------------------------------------------------------------------------------------------------------------------------------------------

### About the choice to use Bluetooth Low Energy

We need to use BLE to understand the pieces of art the user is nearest to. So, what we really need to implement is proximity identification more than a complete indoor localization. To achieve this goal we could also use other technologies, therefore we decided to compare the different solutions before making a definite choice. In particular, we considered the most used technologies for these types of problems, which are BLE (we found documentation about the particular iBeacon protocol, but the following considerations can be generalized), NFC, RFID, and GPS. Reading various articles, we made a summary with this table, where the cells which contain aspects that were a disadvantage with respect to our objectives are colored yellow, and we marked in red the cells which contain major drawbacks that made us decide to discard the corresponding technology.<br/>

![image](src/architecture/BLE_vs_altri_1.JPG)

![image](src/architecture/BLE_vs_altri_2.JPG)

![image](src/architecture/BLE_vs_altri_3.JPG)

![image](src/architecture/BLE_vs_altri_4.JPG)

![image](src/architecture/BLE_vs_altri_5.JPG)

<br/>

To see the table more clearly, consider also looking at the original ([Word file](src/architecture/BLE_vs_others.docx)).

Now let's consider briefly the technologies presented one by one:<br/>
- GPS: it's widely used for localization, but it doesn't really serve our purposes. Moreover, its accuracy indoor is not very high and it consumes a lot of energy, and we don't want our user's smartphone to remain with low battery after or during the visit, this could bother him.<br/>
- NFC: it would have been a good alternative, but the major issue is that it has a range too small (up to 10 cm), which would definitely be not enough to understand if a user is near a piece of art.
- RFID: it was a close call. Different factors pieced together made us discard this technology. First of all there's the problem of accuracy. To achieve good proximity identification we would need a range wider than 1 m (some users may like to admire artworks, especially bigger ones, from a longer distance), so we would have used Ultra High Frequency (range up to 5 - 6 m). The problem is that the accuracy with this frequency drops and the energy consumed is more.<br/>
There is also the problem that is technology is not natively supported by smartphones, and we don't want to bother our users by asking them to install additional software or to carry around an RFID tag, we want the process to be as transparent as possible to the user, especially considering the ones that don't want to be disturbed during the visit but are still willing to participate in data collection.<br/>
- BLE: this technology met all our requirements: it's not expensive, compatible with mobile phones, has a wide enough range, can be used to do proximity identification. The only issue is the accuracy, but as explained in the Evaluation document, there are techniques to mitigate this disadvantage.<br/>

#### RSSI and Kalman Filter
Since one of the general comments about our project was that the BLE technology is not very exciting for indoor positioning, also because sometimes it is not very accurate, as suggested we implemented the use of RSSI crossed to the inclusion of a Kalman Filter for improving the precision of our system. <br>
*Received signal strength indicator (RSSI)* is a measurement of the power present in a received signal: we get the RSSI from each beacon and we can estimate how much is close a user to an artwork and also which is the closest artwork. <br>
The *Kalman Filter* is an algorithm that uses a series of measurements observed over time, containing statistical noise and other inaccuracies, and produces estimates of unknown variables that tend to be more accurate than those based on a single measurement alone. The Raspberry-Pi applies the Kalman Filter to a series of beacon's RSSI receved, so in this way it can avoid abrupt variations or it can ignore strange values caused by wrong beacons measurements.

------------------------------------------------------------------------------------------------------------------------------------------------------

### References
These are the articles we mainly consulted:<br/>

- RFID vs BLE: https://blog.beaconstac.com/2015/10/rfid-vs-ibeacon-ble-technology/

- BLE vs NFC vs GPS: https://blog.beaconstac.com/2015/07/ibeacon-vs-nfc-vs-gps-which-indoor-location-technology-will-your-business-benefit-from/

- RFID frequencies: https://rfid.it/cos-e-la-tecnologia-rfid/

- RFID battery: https://www.nordicid.com/resources/expert-article/how-different-power-saving-options-affect-your-rfid-readers-battery-life-and-power-consumption/

- RFID security and privacy: https://www.electronics-notes.com/articles/connectivity/rfid-radio-frequency-identification/security-privacy.php

- Advantages of BLE and historical comparison: https://blog.roambee.com/supply-chain-technology/evolution-in-supply-chain-visibility-barcodes-to-rfid-to-ble-beacons
