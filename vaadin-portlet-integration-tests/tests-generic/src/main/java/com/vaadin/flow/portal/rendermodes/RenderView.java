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
package com.vaadin.flow.portal.rendermodes;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.portal.VaadinPortlet;

public class RenderView extends VerticalLayout {

    public static final String STATE_MAXIMIZE = "Maximize";
    public static final String STATE_NORMALIZE = "Normalize";
    public static final String MODE_VIEW = "To view mode";
    public static final String MODE_EDIT = "To edit mode";
    public static final String WINDOW_STATE_CHANGE = "window-state-change";
    public static final String PORTLET_MODE_CHANGE = "portlet-mode-change";

    private Button windowState, portletMode;

    public RenderView() {
        VaadinPortlet portlet = RenderPortlet.getCurrent();
        windowState = new Button(
                WindowState.NORMAL.equals(portlet.getWindowState()) ?
                        STATE_MAXIMIZE :
                        STATE_NORMALIZE, event -> switchWindowState());
        windowState.setId(WINDOW_STATE_CHANGE);

        portletMode = new Button(
                PortletMode.EDIT.equals(portlet.getPortletMode()) ?
                        MODE_VIEW :
                        MODE_EDIT, event -> switchPortletMode());
        portletMode.setId(PORTLET_MODE_CHANGE);

        add(windowState, portletMode);
    }

    private void switchWindowState() {
        VaadinPortlet portlet = RenderPortlet.getCurrent();
        if (WindowState.NORMAL.equals(portlet.getWindowState())) {
            portlet.setWindowState(WindowState.MAXIMIZED);
            windowState.setText(STATE_NORMALIZE);
        } else if (WindowState.MAXIMIZED.equals(portlet.getWindowState())) {
            portlet.setWindowState(WindowState.NORMAL);
            windowState.setText(STATE_MAXIMIZE);
        }
    }

    private void switchPortletMode() {
        VaadinPortlet portlet = RenderPortlet.getCurrent();
        if (PortletMode.EDIT.equals(portlet.getPortletMode())) {
            portlet.setPortletMode(PortletMode.VIEW);
            portletMode.setText(MODE_EDIT);
        } else {
            portlet.setPortletMode(PortletMode.EDIT);
            portletMode.setText(MODE_VIEW);
        }
    }
}