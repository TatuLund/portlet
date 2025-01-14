/*
 * Copyright 2000-2019 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.portal.basic;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.portal.VaadinPortlet;

public class BasicPortletContent extends VerticalLayout {

    public static final String GREETING_MESSAGE_ID = "greeting-message";

    public BasicPortletContent() {
        VaadinPortlet portlet = VaadinPortlet.getCurrent();
        String name = portlet.getPortletName();
        String serverInfo = portlet.getPortletContext().getServerInfo();
        Div message = new Div();
        message.setId(GREETING_MESSAGE_ID);
        Button button = new Button("Click me", event -> message.setText(
                "Hello from " + name + " running in " + serverInfo + "!"));
        button.setId("click-button");
        add(button, message);
    }
}
