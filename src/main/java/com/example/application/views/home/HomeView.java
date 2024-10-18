package com.example.application.views.home;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ai.peoplecode.OpenAIConversation;

import java.util.List;


@PageTitle("Home")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private OpenAIConversation conversation;
    private TextField askText;
    private Paragraph replyText;
    private List<String> sampleQuestion;
    Button genAIButton;

    class MyClickListener
            implements ComponentEventListener<ClickEvent<Button>> {
        int count = 0;

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            //event.getSource()
            //        .setText("You have clicked me " + (++count) + " times");
            String reply= conversation.askQuestion("You are Socrates", askText.getValue());
            replyText.setText(reply);
        }
    }

    class GenAIEventListener
            implements ComponentEventListener<ClickEvent<Button>> {
        int count = 0;

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            //event.getSource()
            //        .setText("You have clicked me " + (++count) + " times");
            String reply= conversation.askQuestion("You are Socrates", sampleQuestion.getFirst());
            replyText.setText(reply);
            sampleQuestion = conversation.generateSampleQuestions(reply, 1, 20);
            genAIButton.setText(sampleQuestion.getFirst() );

        }
    }
    public HomeView(){
        String url = "https://cdn.britannica.com/69/75569-050-7AB67C4B/herm-Socrates-half-original-Greek-Capitoline-Museums.jpg";
        Image socratesImage = new Image(url, "Socrates");

        // Optionally, you can adjust the size of the image
        socratesImage.setWidth("auto"); // Set width to 300px
        socratesImage.setHeight("auto"); // Maintain aspect ratio

        // Add the image to the layout
        getContent().add(socratesImage);
        conversation = new OpenAIConversation("demo", "gpt-4o-mini");
        sampleQuestion = conversation.generateSampleQuestions("Generate some questions to ask Socrates", 1, 20);
        // Set up the ask text field
        askText = new TextField();
        askText.setLabel("Ask Socrates a Question");
        askText.setWidth("min-content");

        // Set up the reply text paragraph
        replyText = new Paragraph();
        replyText.setWidth("80%");
        replyText.setHeight("300px");
        replyText.getStyle().set("border", "1px solid black");

        // Set up the ask button
        Button askButton = new Button();
        askButton.setText("Ask");
        askButton.setWidth("min-content");
        askButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Set up the genAI button
        genAIButton = new Button(sampleQuestion.getFirst());
        genAIButton.setWidth("min-content");
        genAIButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Create a HorizontalLayout for both buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(askButton, genAIButton);

        // Add components to the main layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(askText); // Add ask text field
        getContent().add(buttonLayout); // Add the button layout containing both buttons
        getContent().add(replyText); // Add the reply paragraph

        // Add click listener for ask button
        askButton.addClickListener(new MyClickListener());

        // Add click listener for genai button
        genAIButton.addClickListener(new GenAIEventListener());


    }


}
