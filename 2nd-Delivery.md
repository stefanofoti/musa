## Comments and our answers

*"What are your competitors? The idea of personalized visits has been explored before. Did you explore previous solutions? Did they succed? Did they fail? Why? "* <br>
We thought about the fact that the idea of "personalized experience" has been explored before, but honestly it has been hard to find some papers or researches about precisely "personalized tours". Explaining better, there are a lot of ways to personalize or make different a museum visit and we analyzed it more deeply in the Design document, but our goal is exactly to propose personalized tours to visitors, according with their profiles and interests, and examples about previous solutions about this are very few or quite hard to find. 

*"BLE proximity is ok even if is not exciting ;-). Very good to mention an ISO standard, but what about more specifically related to the accuracy of BLE? False positive and negative?"* <br>
When you think about indoor tracking one of the main problems is of course the precision of the localization. What we really need to implement is proximity identification more than a complete indoor localization: for a trivial example we supposed that if there are many artworks near each other, maybe they have a link in their historical background or in their argument, so for our purpose, that is providing customized tours, it should not be a problem because simply the provided tour will propose entirely that group of statues.
A very deep analysis of the indoor localization technologies is available in the Architecture document.

## Changes made to design, architecture and evaluation
We didin't make a lot of techincal or conceptual changes since the first delivery, contrariwise we have done deeper researches about technologies and tools that we are going to use, making more consistent our idea.

In the Desing document we added more information about: 
- how our project takes life
- previous solutions about personalized experiences
- interaction with artworks in famous expositions 
- famous uses of beacons in museums
- references

In the Architecture document we added more information about:
- the choice of BLE
- a comparison on indoor localization technologies
- the choice and the need of using a master board for each room
- master board switched in Raspberry Pi for having more computational power

In the Evaluation document we added more information about:
- the utility of having a master STM-Nucleo board for each room
- a first use of the "Code City" tool for a first analysis of our backend code
- giving an online [questionnaire](https://docs.google.com/forms/d/e/1FAIpQLScuXQogq65TNMCWS0vha5jCFXTIvuk0Vr5boziSh9H5GiGm-w/viewform?usp=sf_link) we collected some data for improving MuSa's features

## Techincal work done since the 1st delivery

———> TO DO - credo sia necessario vedere cosa riusciamo a fare prima di completare questo punto

## Functionality to implement
Obviously, we miss several features to implement before the finaly delivery such as:
- the deploy on Azure (since that part of the demo is simulated or runs in local)
- providing the personalized tour to the visitor through a profiling questionnaire
- the data collection for both visitors tipologies types: the one who uses MuSa and the one that only wants to help the improving with the data collecting
- the "interaction" with the artwork, understood as when a visitor is near an artwork, through his smartphone he can ask for some extra information or curiosities
- (optional) the machine learning algorithm that analyzes the data collected to improve the service

## Evaluation conducted since the 1st delivery 
- a first use of the "Code City" tool for a first analysis of our backend code
- through an online [questionnaire](https://docs.google.com/forms/d/e/1FAIpQLScuXQogq65TNMCWS0vha5jCFXTIvuk0Vr5boziSh9H5GiGm-w/viewform?usp=sf_link) we got some information about user's behavior and appreciation

## Evaluation to do
We are going to evaluate these aspects, about which you can find more information in the Evaluation document:
- the user experience with a *Moment method* and an *Episode method*
- an overall evaluation through the standard ISO/IEC 9126-1
- power consumption, communication complexity, scalability of the sensors network
- revision about the expected price of the whole system

