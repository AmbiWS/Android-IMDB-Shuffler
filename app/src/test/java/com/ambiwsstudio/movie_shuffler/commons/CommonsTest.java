package com.ambiwsstudio.movie_shuffler.commons;

import com.ambiwsstudio.movie_shuffler.RegexMatcher;

import org.junit.Assert;
import org.junit.Test;

public class CommonsTest {

    @Test
    public void testRandomizing() {

        RegexMatcher regexMatcher = new RegexMatcher("tt\\d{7}");

        for (int i = 0; i < 1000; i++) {

            Assert.assertTrue(regexMatcher.matches(Commons.randomizeMovieId()));

        }

    }

}