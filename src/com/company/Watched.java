package com.company;

public interface Watched {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
