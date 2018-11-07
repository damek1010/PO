package com.company;

import java.util.*;

public abstract class AbstractTimetable implements ITimetable, Watched {
    protected LinkedHashMap<Term, Lesson> lessons;
    protected List<Observer> observers;

    @Override
    public boolean put(Lesson lesson) {
        if (this.canBeTransferredTo(lesson.getTerm(), lesson.isFullTime())) {
            lessons.put(lesson.getTerm(), lesson);
            this.observers.add(lesson);
            return true;
        }
        throw new IllegalArgumentException("This term is already taken");
    }

    @Override
    public void perform(Action[] actions) {
        for (int id = 0; id < lessons.size();) {
            for (Lesson lesson : lessons.values()) {
                switch (actions[id]) {
                    case DAY_LATER:
                        lesson.laterDay();
                        break;
                    case DAY_EARLIER:
                        lesson.earlierDay();
                        break;
                    case TIME_LATER:
                        lesson.laterTime();
                        break;
                    case TIME_EARLIER:
                        lesson.earlierTime();
                        break;
                }
                lessons.put(lesson.getTerm(), lessons.remove(lesson.getTerm()));
                id++;
                if (id >= lessons.size()) break;
            }
        }
    }

    @Override
    public Object get(Term term) {
        return lessons.getOrDefault(term, null);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        Iterator<Observer> it = this.observers.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }
}
