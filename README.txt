Music & Muses

By Summer Liu and Brette Fitzgibbon. A generative AI project for the University of San Francisco CS 514 Bridge Software Development course.

Music & Muses is a music recommender powered by ChatGPT that recommends music based on genre, song title, and artist. By inputting
 content and clicking on the corresponding button ("genre", "artist", or "title keyword"),
 the program outputs ChatGPT recommendations based on user input. The "genre" button provides five artists of the genre the user inputs. The 
"title" button provides five songs with the user-inputted word in the title. The "artist" button provides an artist the user might like based
on the artist they input. 

It is written in Java using the Vaadin library, which uses CSS, TypeScript, and HTML to create a user interface, and the PeopleCodeOpenAI library, 
which has methods that respond to generative AI input. It uses the "demo" OpenAI key from LangChain4j on the GPT 4o Mini model. It has an overall vertical 
layout. 

Features: 

-- Instructions box
   A TextArea object that lists instructions on how to use the tool

-- Text window
   A TextField object where the user types in a genre, title word, or artist.

-- Buttons
   Three Button objects: genreButton, titleButton, and artistButton. Each button has an event listener that implements the ComponentEventListener class.
   These event listeners use the PeopleCodeOpenAI askQuestion method to ask ChatGPT one of the following prompts:

   "Create a numbered list of five contemporary artists from the provided genre. Say nothing else. No special characters."
   "Create a numbered list of five contemporary songs with the provided word in the title. Say nothing else. No special characters."
   "Based on the artist the user provides, recommend another artist that the user would also like."

   The prompts were refined based on ChatGPT's initial behavior of listing as many as 10 artists, writing about each artist, and saying things like "Here is 
   your list" and "Let me know if you'd like any further details", which we felt were either simply unnecessary or incompatible with the program, which 
   does not support an ongoing back-and-forth with ChatGPT. 

   The buttons have a horizontal layout from the HorizontalLayout class. 

-- Reply box
   A Paragraph object whose size expands based on the length of the response. It does not accumulate replies from multiple questions. 

The most challenging aspect was calling OpenAI. In a prior attempt, our program could not be pushed to GitHub because the OpenAI API key was exposed. It was 
considered a "secret" by OpenAI and was disabled after we pushed it. Using the LangChain4j demo key that came from class worked because the exact string
of numbers and letters was not exposed. 


