/**
 * Copyright (C) 2019-2022 Vaadin Ltd
 *
 * This program is available under Commercial Vaadin Developer License
 * 4.0 (CVDLv4).
 *
 *
 * For the full License, see <https://vaadin.com/license/cvdl-4.0>.
 */
package com.vaadin.flow.portal;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.communication.WebComponentProvider;

/**
 * For internal use only.
 *
 * @author Vaadin Ltd
 * @since
 */
class PortletWebComponentProvider extends WebComponentProvider {

    public PortletWebComponentProvider() {
        // Disable tag-based cache because the same tag should yield different
        // bootstrap responses in separate portlet namespaces.
        setCacheEnabled(false);
    }

    // TODO: Update WebComponentProvider API to pass also the
    // current VaadinReponse instance, to avoid having to rely on
    // VaadinPortletResponse.getCurrent() here.
    @Override
    protected String generateNPMResponse(String tagName, VaadinRequest request,
            VaadinResponse response) {
        String namespace = ((VaadinPortletResponse) response)
                .getPortletResponse().getNamespace();
        VaadinPortletSession session = VaadinPortletSession.getCurrent();
        String webcomponentBootstrapUrl = VaadinPortlet.getCurrent()
                .getWebComponentBootstrapHandlerURL(session, namespace);
        return "var bootstrapAddress='" + webcomponentBootstrapUrl + "';\n"
                + bootstrapNpm();
    }
}
