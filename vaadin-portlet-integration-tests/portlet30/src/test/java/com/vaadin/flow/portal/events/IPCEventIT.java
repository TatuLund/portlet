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
package com.vaadin.flow.portal.events;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.html.testbench.NativeButtonElement;
import com.vaadin.flow.portal.AbstractPlutoPortalTest;

public class IPCEventIT extends AbstractPlutoPortalTest {

    public IPCEventIT() {
        super("event-target");
    }

    @Test
    public void sendEventFromSourceToTarget() throws InterruptedException {
        addPortlet("event-source", getPage());
        addPortlet("other-event-target", getPage());

        waitUntil(driver -> !findElements(By.id("send-event")).isEmpty());

        NativeButtonElement sendEvent = $(NativeButtonElement.class)
                .id("send-event");

        sendEvent.click();

        waitUntil(driver -> !findElements(By.className("event")).isEmpty());

        WebElement event = findElement(By.className("event"));
        Assert.assertEquals("click[left]", event.getText());

        Assert.assertTrue(findElements(By.className("other-event")).isEmpty());

        // add an event listener programmatically
        findElement(By.id("start-listen")).click();

        sendEvent.click();

        waitUntil(driver -> findElements(By.className("event")).size() == 2);

        // event should be received by a programmatic listener
        Assert.assertFalse(findElements(By.className("other-event")).isEmpty());

        // once event is received the programmatic listener should remove
        // itself, so no more events
        sendEvent.click();

        waitUntil(driver -> findElements(By.className("event")).size() == 3);
        Assert.assertEquals(1,
                findElements(By.className("other-event")).size());
    }


    @Test
    public void sendEventFromSourceToTarget_portletsOnDifferentTabsReceiveEventsIndependently() throws InterruptedException {
        addPortlet("event-source", getPage());
        addPortlet("other-event-target", getPage());

        String firstTab = driver.getWindowHandle();
        String secondTab = openInAnotherWindow();

        driver.switchTo().window(firstTab);
        waitUntil(driver -> !findElements(By.id("send-event")).isEmpty());
        $(NativeButtonElement.class).id("send-event").click();

        driver.switchTo().window(secondTab);
        waitUntil(driver -> !findElements(By.id("send-event")).isEmpty());
        $(NativeButtonElement.class).id("send-event").click();

        driver.switchTo().window(firstTab);
        waitUntil(driver -> !findElements(By.className("event")).isEmpty());
        List<WebElement> events1 = findElements(By.className("event"));
        Assert.assertEquals(1, events1.size());
        Assert.assertEquals("click[left]", events1.get(0).getText());

        driver.switchTo().window(secondTab);
        waitUntil(driver -> !findElements(By.className("event")).isEmpty());
        List<WebElement> events2 = findElements(By.className("event"));
        Assert.assertEquals(1, events2.size());
        Assert.assertEquals("click[left]", events2.get(0).getText());

    }
}
