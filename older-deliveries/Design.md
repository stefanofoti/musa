# Design

### Concept
MuSa is an "experience manager" that collects data and provides interactive-customized tours for museum visitors. A user can decide to use the app in two ways:<br/>
- just collect data: the user isn't interested in personalized tours, but gives the consensus to data collection.<br/>
  This will allow our application to identify which pieces of art are the most appreciated and how users move in the building.<br/>
- do a personalized tour with MuSa: depending on the user's profile, MuSa will suggest him a tour, providing an interactive experience.<br/>
<br/>
Obviously, the user can refuse both alternatives, and just visit the museum without interacting at all with our application.<br/>

## Profiling users and personas
To understand which "type" of users will interact with our application, we started from the data provided by "Museo Sapieza dell'Arte Classica"
in the PDF they provided to our IoT class. In this slide<br/>

![image](/docs/src/design/users.png)

they summarize the profiles of their visitors. We decided to take them as a first model for our personas.<br/>
To better tune this model, we broadcasted a questionnaire online to a large number of people, through which we tried to understand
general information about users, and how do they fit in the categories that we already had: [form](https://docs.google.com/forms/d/e/1FAIpQLScHOCgfRfKwQW0pYXJSsJNKqSPaXVRaSIak9BZPZeact22I4w/viewform).<br/>
We analyzed the data collected and found that the most frequent types of users where the Hobbyist/Professional, the Recharger, and something in between the Experience Seeker and the Explorer, which we fused into the Fun Explorer.<br/>
These three categories were given life in our personas:<br/>

Hobbyst/Professional<br/>

![image](/docs/src/design/elena_rossi.png)
<br/>

Fun Explorer<br/>
![image](/docs/src/design/enea_bianchi.png)
<br/>

Recharger<br/>
![image](/docs/src/design/ettore_verdi.png)

## User experience and storyboard

We created two storyboards for both use cases (just collect data or do a personalized tour).<br/>
When a generic user enters the museum, he is asked by a staff member if he wants to try MuSa. They'll be given the reference to the web app and
the user will find himself in the Home page, where a brief explanation of MuSa's services will be written.<br/>

### Collect data
The protagonist of this storyboard is Elena, and we figured she'll just want to collect data:<br/>

![image](/docs/src/design/story_elena.png)

1. Elena has finally some free time, so she decided to visit a museum. She has been looking forward to studying some specific pieces, and has planned a tour accordingly. She arrives, and the receptionist asks her if she wants to try MuSa.
2. Elena goes to the MuSa web app the receptionist pointed her out. She sees the home page. There she can find a brief introduction about MuSa and its services. She presses “Let’s start” to proceed to the next page.
3. Elena finds a short survey she needs to complete. She is a little irked by this, but the questionnaire is short and easy to answer, so it’s not too much of a bother. It gathers basic data about her to understand to which persona she belongs and to, eventually, produce a customized tour for her. She presses “Continue”.
4. A new page loads. Elena has to say how she would like to use MuSa. She has already a clear idea of what she wants to see, and she doesn’t want to be bothered. However, she likes the idea of gathering useful data for her loved museum, so she gives her consensus to collect information. 
5. Elena sees MuSa’s gratitude and is glad she can help. She clicks “Start collect data” and proceeds to the visit.
6. Elena visits the museum according to her schedule. The app collects data about her location and how much time she spends near every piece of art. This is done thanks to the communication between the boards and her smartphone. All the work is done in background, so Elena doesn’t even notice. She is able to enjoy her visit without being bothered. 
7. Elena leaves, satisfied with her visit. The app has stopped collecting data. The museum curators are happy with the information they’ve gathered.

The museum staff and Elena will both be happy, since they will have data to better their services, and Elena has been able to do the tour she
had planned without being disturbed.<br/>

### Do a personalized tour

Here we imagined what would be Enea's experience. He'd like to try MuSa, and he will exploit all the functionalities offered.<br/>

![image](/docs/src/design/story_enea.png)

1. These days Enea wants to try something out of his comfort zone, so he decides to visit a museum of classical art. When he arrives, the receptionist asks him if he wants to try MuSa.
2. Enea goes to the MuSa web app the receptionist pointed him out. He sees the home page There he can find a brief introduction about MuSa and its services. He presses “Let’s start” to proceed to the next page.
3. Enea finds a short survey he needs to complete. He is curious about the new application and happily complies. It gathers basic data about him to understand to which persona he belongs and to, eventually, produce a customized tour for him. He presses “Continue”.
4. A new page loads. Enea has to say how he would like to use MuSa. He likes to try new things and being advised about the best itinerary for him, rather than wander around without a guide, sounds good to him. Therefore, he decides to get a customized tour and presses “Done”.
5. Enea sees MuSa’s exited response and he’s in for a ride. He clicks “Start the journey” to proceed.
6. MuSa proposes a tour tailored to his personality. Enea could ask for a different itinerary by pressing “Give me another tour”, but he’s satisfied with the suggestion and presses “Start the journey”.
7. Enea visits the museum following MuSa. The app collects data about his location and how much time she spends near every piece of art, while also accompanying him through the rooms and providing useful information about the artworks exposed. This is done thanks to the communication between his smartphone, the boards, and the backend of the application. Enea is glad he decided to use MuSa, he’s learning new things and he feels entertained.
8. Enea leaves, satisfied with her visit. The app has stopped collecting data. The museum curators are happy with the information they’ve gathered, and with the fact that a new visitor had a  good time in their museum.
