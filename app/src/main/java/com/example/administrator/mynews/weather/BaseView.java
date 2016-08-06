package com.example.administrator.mynews.weather;

/**
 * view家规要求要依赖Presenter而活.
 */
public interface BaseView<T> {
    void setPresenterImpl(T presenterImpl);
}
