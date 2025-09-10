Music & Muses

In collaboration with my classmate Summer Liu.

What This Program Does:
Music & Muses is a music recommender powered by ChatGPT that recommends music based on genre, song title, artist, and nation. By inputting content and clicking on the corresponding button ("genre", "artist", "title", or "nation"), the program outputs ChatGPT recommendations based on user input. The "genre" button provides five songs of the genre the user inputs. The "title" button provides five songs with the user-inputted word in the title. The "nation" button provides five artists based in the user-inputted country. The "artist" button provides an artist the user might like based on the artist they input. 
______________________________________________

How to Build and Run:

In IntelliJ:
1. Under the Code button on GitHub, select HTTPS and copy the URL
2. In IntelliJ, click on Get from VCS
3. Click Clone
4. In the file tree in the left panel, click music-and-muses
5. Navigate to src/main/java/com.example.application/Application 
6. Run Application
7. localhost:8080 will open in a new tab
8. Play around to find some cool new additions to your Spotify, Apple Music, or listening method of choice!

______________________________________________

What This Program Entails:
It is written in Java using the Vaadin library, which uses CSS, TypeScript, and HTML to create a user interface, and the PeopleCodeOpenAI library, which has methods that respond to generative AI input. It uses the "demo" OpenAI key from LangChain4j on the GPT 4o Mini model. It has an overall vertical layout. 

Features: 

-- Instructions box
   A TextArea object that lists instructions on how to use the tool

-- Text window
   A TextField object where the user types in a genre, title word, or artist.

-- Buttons
   Four Button objects: genreButton, titleButton, artistButton, and nationButton. Each button has an event listener that implements the ComponentEventListener class. These event listeners use the PeopleCodeOpenAI askQuestion method to ask ChatGPT one of the following prompts:

   "Create a numbered list of five contemporary artists from the provided genre. Say nothing else. No special characters."

   "Create a numbered list of five contemporary songs with the provided word in the title and the artist of that song. Say nothing else. No special characters."

   "Based on the artist the user provides, recommend another artist that the user would also like."

   "Based on the nation the user provides, provide a list of five artists who are based in that nation. Say nothing else. No special characters."

   The prompts were refined based on ChatGPT's initial behavior of listing as many as 10 artists, writing about each artist, and saying things like "Here is your list" and "Let me know if you'd like any further details", which we felt were either simply unnecessary or incompatible with the program, which does not support an ongoing back-and-forth with ChatGPT. 

   The buttons have a horizontal layout from the HorizontalLayout class. 

-- Reply box
   A Paragraph object whose size expands based on the length of the response. It does not accumulate replies from multiple questions.

