package com.showtrack.pages;

import com.showtrack.BaseTest;
import org.junit.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void renders() throws Exception {
        getTester().startPage(LoginPage.class);
        getTester().assertRenderedPage(LoginPage.class);
    }
}