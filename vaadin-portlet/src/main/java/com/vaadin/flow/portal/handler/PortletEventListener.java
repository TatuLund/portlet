/*
 * Copyright 2000-2018 Vaadin Ltd.
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
package com.vaadin.flow.portal.handler;

/**
 * A listener for portlet events.
 *
 * @see VaadinPortletEventContext
 *
 * @author Vaadin Ltd
 *
 */
@FunctionalInterface
public interface PortletEventListener {

    /**
     * This method gets called when an IPC event is received.
     *
     * @param event
     */
    void onPortletEvent(PortletEvent event);
}