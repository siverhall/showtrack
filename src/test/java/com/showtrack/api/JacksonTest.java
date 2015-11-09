package com.showtrack.api;

import com.showtrack.json.ShowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class JacksonTest {

    private String str;

    @Before
    public void setUp() throws Exception {
        str = "[{\"score\": 5, \"show\": {\"id\": 4, \"name\":\"Test\", \"other\":\"hi2\"}}," +
                "{\"score\": 3, \"show\": {\"id\": 2, \"name\":\"Test2\", \"other\":\"hi\"}}]";
    }


    @Test
    public void tree_model_test() throws Exception {
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        ShowMapper showMapper = new ShowMapper(stream);

        Assert.assertNotNull(showMapper.getShows());
    }

}
