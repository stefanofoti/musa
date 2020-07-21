## Release and install notes - MuSa 1.0.0
In this document you'll find some suggestions and steps both to tune up a development environment and to deploy the project.  <br>

#### Azure IoT hub
Create a resource group - if you do not have one<br>
``az group create --name {your resource group name} --location westus``<br>
Create a new Azure IoT hub, and get the following parameters - you will need them later on.<br>
Refer to evaluation document to get more details about plans.<br>
``az iot hub create --name {your iot hub name} --resource-group {your resource group name} --sku FREE``<br>
Event Hub-compatible endpoint<br>
``az iot hub show --query properties.eventHubEndpoints.events.endpoint --name {your IoT Hub name}``<br>
Event Hub-compatible name<br>
``az iot hub show --query properties.eventHubEndpoints.events.path --name {your IoT Hub name}``<br>
SAS key<br>
``az iot hub policy show --name service --query primaryKey --hub-name {your IoT Hub name}	``<br>

#### Backend
The backend of MuSa is developed with .NET core; it is a stand alone project, this means that you can easly deploy in on local machine or deploy it on azure. Install .NET 3.1 SDK, set your endpoint to the Azure EventHub to get IoT messages, build and deploy it.<br>

#### Deploy the backend locally:
Requirements: <br>
https://dotnet.microsoft.com/download/dotnet-core/3.1 <br>
please install .NET Core 3.1 runtime (or higher). It works on Windows, Linux and MacOS. Add installation folder to PATH. Type dotnet run to start the project. <br>

#### Deploy the backend on the cloud:
Go to the Azure Portal and create a new app service<br>
Set credentials for deployment<br>
``az webapp deployment user set --user-name [username] --password [password]``<br>
Create a new app service plan<br>
``az appservice plan create --name myAppServicePlan --resource-group myResourceGroup --sku FREE``<br>
Init a new application; We will show you how to deploy using a local git, but Azure supports many different ways.<br>
``az webapp create --resource-group myResourceGroup --plan myAppServicePlan --name [app-name] --deployment-local-git``<br>
You will get in output your git repo for deploy details.<br>
``"deploymentLocalGitUrl": "https://[username]@[app-name].scm.azurewebsites.net/[app-name].git"``<br>
Add a new remote to your project git folder<br>
``git remote add azure < deploymentLocalGitUrl-from-create-step >``<br>
``git add .``<br>
``git commit -m "Deploy"``<br>
``git push azure master``<br>
The deploy will start automatically; the exposed services will be available at http://[application_name].azurewebsites.net/<br>

#### Positioning logic gateway
It's just Python. You will just need Python 3 and the following libraries. <br>
``pip install azure-iot-device``<br>
``pip install azure-iothub-device-client``<br>
``pip install paho-mqtt``<br>
<br>
Set your MQTT broker endpoint, set the topics in which the BLE boards publish their messages, set the connection string to reach the Azure IoT hub. Please refer to architecture document to understand message format and what is going on. <br>

#### About ESP32 boards
In order to customize the code, set your broker endpoint and a topic for each board; In order to get things work properly you will need the following libraries for managing [BLE](https://www.coxlab.kr/doxygen/Nol.A-SDK/group__BluetoothLE.html), [WiFi/Network](https://pubsubclient.knolleary.net/), [MQTT](https://github.com/plapointe6/EspMQTTClient) and [JSON](https://arduinojson.org/). Repartition the board with 3MB for code, 1MB of dynamic memory. You will not be able to use OTA with this configuration. Overwrite the maximum size of the packet size for PubSubClient used by EspMQTTClient to 7000. Please refer to evaluation document to undestand limits, capabilities of the solution and also power requirements for the installation. <br>

#### Mobile client application
Set your endpoint of the services, install the application. It requires Android SDK 22 on Android Studio. All the required dependencies are managed by the gradle scripts, so the first gradle sync may take some time. The application will run on any Android device with API level 22 or higher (Android 5.1+). <br>

