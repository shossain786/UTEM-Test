package com.utem.hooks;

import com.utem.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class UTEMHooks extends BaseTest {
    @Before
    public void before() {
        startBrowser();
        openSite();
    }

    @After
    public void after() {
        closeBrowser();
    }
}
