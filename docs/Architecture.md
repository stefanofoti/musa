# Architecture

### Introduction

This application needs a lot of components to work properly. The three main parts are:<br/>
- the sensor network<br/>
- the interaction between the client on the smartphone and the backend on the cloud<br/>
- cloud services, which comprehend also a machine learning algorithm<br/>
<br/>
Near every piece of art, there is a board, which communicates with the user's phone to localize him and understand how much time he spends near it. The data are sent to the cloud platform to be stored and elaborated. A machine learning algorithm creates customized tours according to the
information collected and on the reports generated for each user at the end of the visit. The user's smartphone is connected with the backend to
provide interaction with the visitor.<br/>

## Architecture and components

The architecture is the following:<br/>

![image](src/architecture/architecture.png)

### List
#### Hardware
- STM-Nucleo boards (x pieces of art + 1)<br/>
- user smartphone<br/>

#### Software
- Azure Cloud Platform:<br/>
  - Azure IoT Hub<br/>
  - Azure Database<br/>
  - Azure Machine Learning<br/>
  - Azure Web App Service Plan<br/>
- Google Docs<br/>
- RIOT OS

#### Technologies
- Bluetooth<br/>
- MQTT-SN<br/>
- MQTT<br/>

### Sensor network

The boards we decided to use are STM-Nucleo, one for each piece of art, and the user's smartphone communicates with them via Bluetooth.
Through beacons, we are able to locate the visitor inside the museum and the data gathered is sent to a main STM-Nucleo with MQTT-SN. This main board serves as a gateway: it makes some pre-processing and sends them to the cloud platform through MQTT protocol.<br/>  

### Backend and smartphone front-end

The backend of the application lives in the cloud and takes care of the interaction with the user's smartphone. MuSa accompanies the visitor,
proposing him a personalized tour, providing information about the different artworks, and making sure he's enjoying the itinerary.<br/>
When the user arrives at the museum connects to the web app with his mobile and it runs until the end of the visit. At the end of the tour, it asks the user to fill in a survey to provide feedback about the quality of the service. This answers are stored in a Google Document, in a way that makes them easily accessible to the machine learning algorithm.<br/>

### Cloud

It's a crowded place: here we can find the IoT Hub, our application code (backend and frontend) with its database and the machine learning algorithm.<br/>
The IoT Hub receives the MQTT messages from the STM-Nucleo gateway and forwards them to the backend. The backend stores them locally and will use them mainly for two purposes: tracking the user during his visit and make the report at the end of the itinerary.<br/>
Azure Machine Learning takes as input the dataset saved on the Google Doc, elaborates them creating the tours for the different type of users (the personas we identified) and it pushes them into the database of the application. It runs as a batch.<br/>
The database contains also information about the pieces of art and when the application needs to present the user a tour or details about an
artwork, it will make a query to it.<br/>
