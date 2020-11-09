package com.ambiwsstudio.movie_shuffler.component;

import com.ambiwsstudio.movie_shuffler.modules.ApplicationModule;
import com.ambiwsstudio.movie_shuffler.modules.ServiceModule;
import com.ambiwsstudio.movie_shuffler.modules.StorageModule;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class, StorageModule.class})
public interface MovieComponent {


    void injectsMovieViewModel(MovieViewModel movieViewModel);

}
