Evaluation Document

# Evaluation

In this document we will give a brief introduction on the evaluation methods we thought of using for our project.<br/>
We will evaluate with different methods the user experience and the technologies used. In particular, the quality of the hardware and software
components will be measured taking into account their peculiarities, but also the quality of the whole system.

(However, keep in mind that this is a project developed for an IoT course, so we will cover this section to provide a comprehensive analysis, but clearly it will not be at a professional level

## Objectives

With our application we tried to realize something useful for both the museum and the users. In particular, we want to satisfy these demands:<br/>
- make the museum more attractive and collect data useful for the curators<br/>
- make sure people will enjoy their visits at the museum<br/>
<br/>
MuSa meets these requirements by collecting the users' movements in the museum, storing the data and then analyzing them to provide customized tours.<br/>

## User experience

The happiness of users is important, and we want to make sure they enjoyed our services. MuSa is designed to understand the needs of the visitors:<br/>
- the user has limited time, therefor MuSa will provide him a tour with only the most relevant pieces of art (based on the user's interests)<br/>
- the users wants some curiosity about a statue or its historical or mythological background, and MuSa will give him all the knowledge he wants<br/>
- the user doesn't want to do a guided tour of the museum, but he would like some support all the same, and MuSa will be happy to accompany him<br/>

To measure the users' appreciation we are going to use the following UXEMs (User Experience Evaluation Methods):<br/>
- a _Moment_ method: through the interaction with MuSa we can understand the visitor's mood (for example, if he is not following the proposed tour we can assume he probably is not enjoying it).<br/>
 Furthermore, for this purpose we suggest to use also a commercial tool like PrEmo that allows MuSa to know the user's feeling in real-time<br/>
 _Link_: https://www.premotool.com/<br/>

- an _Episode_ method: at the end of the visit the user will be asked to fill a short survey about his experience. This questionnaire can be implemented using also the AttrakDiff tool (_Link_: http://www.attrakdiff.de/index-en.html).<br/>

## Technology
Under the technological stack, MuSa uses many different hardware and software solutions. We decided to evaluate each single part and the overall system. Further details are in the following paragraphs.<br/>

### Overall evaluation
Software quality is defined by a set of regulations and guidelines by ISO/IEC 9126-1. We used a criteria-based evaluation which gives a measurement of quality in a number of areas, including usability, sustainability and maintainability (the original document that we used as model is [this one](https://software.ac.uk/sites/default/files/SSI-SoftwareEvaluationCriteria.pdf?_ga=2.151004923.318823281.1587909367-13184924.1587909367)). Of course, we did not use the criteria we did not need for, so we produced this lighter customized version:

[MuSa Criteria](src/evaluation/MuSa_criteria.pdf) 

Furthermore, we suggest one of [these](https://owasp.org/www-community/Vulnerability_Scanning_Tools) tools to run security tests. 

### Sensor network
The sensor network evaluation has to consider aspects like:<br>
- Power consumption
- Communication complexity
- Scalability
<br>
The suggested board for the project is STM Nucleo. We chose to keep it because of its low power consumption and low price; it is also widely used, rich of documentation and examples.<br>

This is not the only possible solution. Among the others, a great idea may be to convert our [server based positioning to client based positioning](https://developex.com/blog/indoor-navigation-with-ble/) and to use BLE (Bluetooth Low Energy) tags, like _NRF51822 ibeacon tag_. Their cost is very low, just 3-4 euro/pcs.<br>

Our STM nucleo costs about 10-15 euro, but it is much more flexible than a BLE tag and offers many possibilities for further development ideas.<br> 

### Backend
Our backend code quality will be tested with [CodeCity](https://wettel.github.io/codecity.html). It is a very simple tool that allows to check the most common software metrics in a new way in which software systems are visualized as interactive, navigable 3D cities. The classes are represented as buildings in the city, while the packages are depicted as the districts in which the buildings reside. The more the city is well structured, the higher the code quality is. Further evaluations will be provided [here](src/evaluation/MuSa_criteria.pdf).

### Cloud System
Most of this project lives in the Azure platform, including:  
- backend + frontend ([Azure App Service Plan](https://azure.microsoft.com/it-it/pricing/details/app-service/plans/))
- iot services ([Azure IoT Hub](https://azure.microsoft.com/it-it/services/iot-hub/))
- machine learning algorithm ([Azure Machine Learning](https://azure.microsoft.com/it-it/services/machine-learning/))
- database ([Azure Database](https://azure.microsoft.com/it-it/services/sql-database/))

We chose Azure among all the cloud services providers seen so far in our IoT course since it looks to be the most suitable for our purposes. Moreover, it has all the needed services out of the box in a single place.<br>
Since this project is developed as part of our IoT course, the machine learning algorithm will not be evaluated. 

### About the price

#### Hardware
We need as many boards as the number of artworks displayed in the museum plus one for the gateway; each one costs about 10-15 euro. 

#### Cloud
Azure is a commercial product offering a minority of free services for small projects. Using the [Microsoft Pricing Calculator](https://azure.microsoft.com/it-it/pricing/calculator/) we checked for the cheapest possible solution for study purposes.<br>
The Azure IoT hub is free until 8000 messages/day. Exploiting our architecture choice to use a main STM nucleo board as a bridge for the whole sensor network we are able to save a big amount messages.<br>
The Azure Database is free for the previous Azure Database generation (the 4th), with few GB of space, but the fact you can not have any backup possibility is a major drowback.<br>
The Azure App Service Plan offers for free a complete solution to deploy a full stack application with both FE and BE. The free solution offers 1GB of storage, 1GB of RAM and a shared CPU. Please note that in such a way you can not keep your application always running.<br>
Unfortunately the Azure Machine Learning does not offer any free plan, and it starts at about 4 USD/month.<br>
We attach a short [excel report](src/evaluation/azure_plan.xlsx) in order to better visualize all the plans.<br>
It is very easy to scale this plan and evaluate how this affects costs using the [Azure Pricing Calculator](https://azure.microsoft.com/it-it/pricing/calculator/).<br>
