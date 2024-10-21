package com.example.application.views.home;

import ai.peoplecode.OpenAIConversation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.List;



@PageTitle("Music & Muses")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private OpenAIConversation conversation;
    private TextField askText;
    private Paragraph replyText;
    class MyClickListener
            implements ComponentEventListener<ClickEvent<Button>> {
        int count = 0;
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String reply= conversation.askQuestion("Create a numbered list of five contemporary artists from the provided genre. Say nothing else. No special characters.", askText.getValue());
            replyText.setText(reply);
        }
    }

    class titleClickListener
            implements ComponentEventListener<ClickEvent<Button>> {

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String reply = conversation.askQuestion("Create a numbered list of five contemporary songs with the provided word in the title, and provide the artist next to each song title. Don't say anything before or after this list.", askText.getValue());
            replyText.setText(reply);
        }
    }

    class artistClickListener
            implements ComponentEventListener<ClickEvent<Button>> {

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String reply = conversation.askQuestion("Based on the artist the user provides, recommend another artist that the user would also like.", askText.getValue());
            replyText.setText(reply);
        }
    }

    class nationClickListener
            implements ComponentEventListener<ClickEvent<Button>> {

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String reply = conversation.askQuestion("Based on the nation the user provides, provide a list of five artists who are based in that nation. Say nothing else. No special characters.", askText.getValue());
            replyText.setText(reply);
        }
    }

    public HomeView() {
        conversation = new OpenAIConversation("demo", "gpt-4o-mini");
        askText = new TextField(); // this is where the question is asked

        TextArea instructions = new TextArea();
        instructions.setLabel("Instructions");
        instructions.setValue("1. Enter a genre, song title keyword, or artist in the input field.\n"
                + "2. Click the corresponding button to get recommendations or a song with the keyword in it.\n"
                + "3. Enjoy the curated list of artists and songs!");
        instructions.setWidth("80%");
        instructions.setReadOnly(true);

        Button genreButton = new Button();
        Button titleButton = new Button();
        Button artistButton = new Button();
        Button nationButton = new Button();

        replyText = new Paragraph(); // this is the response
        replyText.setWidth("80%");
        replyText.setHeight("min-content");
        replyText.getStyle().set("border", "1px solid black");
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        askText.setLabel("Your input: ");
        askText.setWidth("80%");
        askText.setWidth("min-content");

        genreButton.setText("Genre");
        genreButton.setWidth("min-content");
        genreButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        artistButton.setText("Artist");
        artistButton.setWidth("min-content");
        artistButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        titleButton.setText("Title");
        titleButton.setWidth("min-content");
        titleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        nationButton.setText("Nation");
        nationButton.setWidth("min-content");
        nationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(instructions);
        getContent().add(askText);
        HorizontalLayout buttonLayout = new HorizontalLayout(genreButton, titleButton, artistButton, nationButton);
        // Add components to the main layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(buttonLayout);
        getContent().add(replyText);

        MyClickListener listener = new MyClickListener();
        genreButton.addClickListener(listener);
        titleButton.addClickListener(new titleClickListener());
        artistButton.addClickListener(new artistClickListener());
        nationButton.addClickListener(new nationClickListener());

        Image image = new Image("https://i.etsystatic.com/14239514/r/il/10f8c0/1155778745/il_570xN.1155778745_ij52.jpg", "Music Notes");
        image.setWidth("570px");
        image.setHeight("428px");

        getContent().add(image);
    }

}
