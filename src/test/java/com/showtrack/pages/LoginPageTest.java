package com.showtrack.pages;

import com.showtrack.BaseTest;
import org.junit.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void renders() throws Exception {
        getTester().startPage(LoginPage.class);
        getTester().assertRenderedPage(LoginPage.class);
    }

    @Test
    public void successful_login_redirects_to_homepage() throws Exception {
        getTester().startPage(StartPage.class);
        getTester().assertRenderedPage(LoginPage.class);

        login("secret", "secret");

        getTester().assertRenderedPage(StartPage.class);
    }

    @Test
    public void no_redirect_when_login_fails() throws Exception {
        getTester().startPage(StartPage.class);
        getTester().assertRenderedPage(LoginPage.class);

        login("fail", "now!");

        getTester().assertRenderedPage(LoginPage.class);
    }
}