package com.egocorp;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Date;

/**
 * Created by FormsDeveloper on 5/18/16.
 */
public class TextLabelPanel extends Panel
{
    public TextLabelPanel(String id, IModel<?> model, boolean done) {
        super(id, model);
        Label label1 = new Label("text", model);
        Label label2 = new Label("status");

        if(model.getObject().getClass() == Date.class) {
            Date taskDate = (Date) model.getObject();
            Date currentDate = new Date();

            if(done)
            {
                label2 = new Label("status", new Model("Выполненная"));
                label2.add(new AttributeAppender("class", "label label-success"));
            }
            else if (taskDate.compareTo(currentDate) < 0) {
                if ((currentDate.getTime() - taskDate.getTime()) < 86400000) {
                    label2 = new Label("status", new Model("Срочная"));
                    label2.add(new AttributeAppender("class", "label label-warning"));
                } else {
                    label2 = new Label("status", new Model("Просроченная"));
                    label2.add(new AttributeAppender("class", "label label-danger"));
                }
            }
        }
        add(label1);
        add(label2);
    }
}