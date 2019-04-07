package com.almin.freecomic;

import android.os.Bundle;

import com.almin.freecomic.module.comic.contract.Intent;
import com.almin.freecomic.module.comic.contract.ComicInfoContract;
import com.almin.freecomic.module.comic.presenter.ComicInfoPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Almin on 2018/11/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class Testttt {

    @Mock
    private ComicInfoContract.ViewRenderer viewRenderer;

    @Mock
    private ComicInfoContract.DataSource dataSource;

    @Test
    public void testSS(){
        ComicInfoPresenter presenter = new ComicInfoPresenter(viewRenderer,dataSource);
        Bundle bundle = mockBundle();
        bundle.putString(Intent.BUNDLE_KEY_COMIC_AVATAR_URL,"12121");
        bundle.putString(Intent.BUNDLE_KEY_COMIC_ID,"12121");

//        when(bundle.getString(Intent.BUNDLE_KEY_COMIC_AVATAR_URL)).thenReturn("123");
//        when(bundle.getString(Intent.BUNDLE_KEY_COMIC_ID)).thenReturn("123");

        presenter.start(bundle);
//        verify(viewRenderer).displayComicInfo();
    }

    private Bundle mockBundle() {
        final Map<String, String> fakeBundle = new HashMap<>();
        Bundle bundle = mock(Bundle.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                String key = ((String) arguments[0]);
                String value = ((String) arguments[1]);
                fakeBundle.put(key, value);
                return null;
            }
        }).when(bundle).putString(anyString(), anyString());

        when(bundle.getString(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                String key = ((String) arguments[0]);
                return fakeBundle.get(key);
            }
        });
        return bundle;
    }
}
