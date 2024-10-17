package com.example.application.views.home;

import ai.peoplecode.OpenAIConversation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
            //event.getSource()
            //        .setText("You have clicked me " + (++count) + " times");
            String reply= conversation.askQuestion("You are Socrates", askText.getValue());
            replyText.setText(reply);
        }
    }
    public HomeView() {
        conversation = new OpenAIConversation("demo", "gpt-4o-mini");
        askText = new TextField();
        Button askButton = new Button();
        replyText = new Paragraph();
        replyText.setWidth("80%");
        replyText.setHeight("300px");
        replyText.getStyle().set("border", "1px solid black");
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        askText.setLabel("Ask Socrates a Question");
        askText.setWidth("min-content");
        askButton.setText("Ask");
        askButton.setWidth("min-content");
        askButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //replyText.setWidth("min-content");
        getContent().add(askText);
        getContent().add(askButton);
        getContent().add(replyText);

        askButton.addClickListener(new MyClickListener());

    }


}
