package com.ambiwsstudio.movie_shuffler.component;

import com.ambiwsstudio.movie_shuffler.modules.ApplicationModule;
import com.ambiwsstudio.movie_shuffler.modules.ServiceModule;
import com.ambiwsstudio.movie_shuffler.modules.StorageModule;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieCollectionViewModel;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ServiceModule.class, StorageModule.class})
public interface MovieComponent {

    void injectsMovieRepositoryAPI(MovieRepositoryAPI movieRepositoryAPI);
    void injectsMovieRepositoryDB(MovieRepositoryDB movieRepositoryDB);
    void injectsMovieCollectionViewModel(MovieCollectionViewModel movieCollectionViewModel);

}
