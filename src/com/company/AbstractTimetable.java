package com.company;

import java.util.*;

public abstract class AbstractTimetable implements ITimetable, Observer {
    protected LinkedHashMap<Term, Lesson> lessons;

    @Override
    public boolean put(Lesson lesson) {
        if (this.canBeTransferredTo(lesson.getTerm(), lesson.isFullTime())) {
            lessons.put(lesson.getTerm(), lesson);
            return true;
        }
        throw new IllegalArgumentException("This term is already taken");
    }

    @Override
    public void perform(Action[] actions) {
        int lessonsSize = lessons.size();
        int id = 0;
        Lesson[] lessonsArray = new Lesson[lessonsSize];
        for (Lesson lesson : lessons.values()) {
            lessonsArray[id] = lesson;
            id++;
        }
        id = 0;
        for (Action action : actions) {
            switch (action) {
                case DAY_LATER:
                    lessonsArray[id].laterDay();
                    break;
                case DAY_EARLIER:
                    lessonsArray[id].earlierDay();
                    break;
                case TIME_LATER:
                    lessonsArray[id].laterTime();
                    break;
                case TIME_EARLIER:
                    lessonsArray[id].earlierTime();
                    break;
            }
            id++;
            id = id % lessonsSize;
        }
    }

    @Override
    public Object get(Term term) {
        return lessons.getOrDefault(term, null);
    }

    @Override
    public void update() {
        Set set = lessons.entrySet();
        Iterator it = set.iterator();
        boolean somethindtoChange = false;
        Term newTerm = null;
        Term oldTerm = null;

        while(it.hasNext()) {
            Map.Entry lesson = (Map.Entry) it.next();
            newTerm = ((Lesson)lesson.getValue()).getTerm();
            oldTerm = (Term)lesson.getKey();
            if(oldTerm != newTerm) {
                somethindtoChange = true;
            }
        }
        if (somethindtoChange)
            lessons.put(newTerm, lessons.remove(oldTerm));
    }
}
