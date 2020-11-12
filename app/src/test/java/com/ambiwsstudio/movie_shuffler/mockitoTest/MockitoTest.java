package com.ambiwsstudio.movie_shuffler.mockitoTest;

import android.content.Context;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.test.ClassWithContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    Context context;

    @Mock
    List<String> mockedList;

    ClassWithContext classWithContext;

    @Before
    public void setup() {

        classWithContext = new ClassWithContext(context);

    }

    @Test
    public void mockedListTest() {

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        Assert.assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        Assert.assertEquals(100, mockedList.size());

    }

    @Test
    public void mockedContextTest() {

        Mockito.when(context.getString(R.string.app_name)).thenReturn("IMDB_Shuffler");
        String result = classWithContext.getData();

        Assert.assertEquals(result, "IMDB_Shuffler");

    }

    @Test
    public void mockedContextColorTest() {

        Mockito.when(context.getColor(Mockito.anyInt())).thenReturn(100000);
        String result = classWithContext.divideIntColor();

        Assert.assertEquals(result, String.valueOf(50000));

    }

}
