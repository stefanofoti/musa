Design Document


# Design
MuSa is an "experience manager" that collects data and provides interactive-customized tours for museum visitors. A user can decide to use the app in two ways:<br/>
- just collect data: the user isn't interested in personalized tours, but gives the consensus to data collection.<br/>
  This will allow our application to identify which pieces of art are the most appreciated and how users move in the building.<br/>
- do a personalized tour with MuSa: depending on the user's profile, MuSa will suggest him a tour, providing an interactive experience.<br/>
<br/>
Obviously, the user can refuse both alternatives, and just visit the museum without interacting at all with our application.<br/>

## Profiling users and personas
To understand which "type" of users will interact with our application, we started from the data provided by "Museo Sapieza dell'Arte Classica"
in the PDF they provided to our IoT class. In this slide<br/>

![image](musa/docs/src/images/Museum_users.png)

they summarize the profiles of their visitors. We decided to take them as a first model for our personas.<br/>
To better tune this model, we broadcasted a questionnaire online to a large amount of people, through which we tried to understand
general information about users, and how do they fit in the categories that we already had: [form](https://docs.google.com/forms/d/e/1FAIpQLScHOCgfRfKwQW0pYXJSsJNKqSPaXVRaSIak9BZPZeact22I4w/viewform).<br/>
We analyzed the data collected and found that the most frequent types of users where the Hobbyst/Professional, the Recharger, and something in between the Experience Seeker and the Explorer, which we fused into the Fun Explorer.<br/>
These three categories were given life in our personas:<br/>

Hobbyst/Professional<br/>

![image](musa/docs/src/personas/elena_rossi.PNG)

<br/>

Recharger<br/>

![image](musa/docs/src/personas/enea_bianchi.PNG)

<br/>

Fun Explorer<br/>

![image](musa/docs/src/personas/ettore_verdi.PNG)

## User experience and storyboard

We created two storyboard for both use cases (just collect data or do a personalized tour).<br/>
When a generic user enters the museum, he is asked by a stuff member if he wants to try MuSa. They'll be given the reference to the web app and
the user will find himself in the Home page, where a brief explanation of MuSa's services will be written.<br/>

### Collect data
The protagonist of this storyboard is Elena, and we figured she'll just want to collect data:<br/>

![image](src/images/Elena_storyboard.png)

The museum stuff and Elena will both be happy, since they will have data to better their services, and Elena has been able to do the tour she
had planned without being disturbed.<br/>

### Do a personalized tour

Here we imagined what would be Enea's experience. He'd like to try MuSa, and he will exploit all the functionalities offered.<br/>

![image](src/images/Enea_storyboard.png)
