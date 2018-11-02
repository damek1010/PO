package com.company;

import java.util.ArrayList;

public abstract class AbstractTimetable implements ITimetable {
    protected ArrayList<Lesson> lessons;

    @Override
    public boolean put(Lesson lesson) {
        if (this.canBeTransferredTo(lesson.getTerm(), lesson.isFullTime())) {
            lessons.add(lesson);
            return true;
        }
        return false;
    }

    @Override
    public void perform(Action[] actions) {
        int id = 0;

        for (Action action : actions) {
            switch (action) {
                case DAY_LATER:
                    lessons.get(id).laterDay();
                    break;
                case DAY_EARLIER:
                    lessons.get(id).earlierDay();
                    break;
                case TIME_LATER:
                    lessons.get(id).laterTime();
                    break;
                case TIME_EARLIER:
                    lessons.get(id).earlierTime();
                    break;
            }
            id++;
            id = id % lessons.size();
        }
    }

    @Override
    public Object get(Term term) {
        for (Lesson lesson : lessons) {
            if (lesson.getTerm().equals(term)) {
                return lesson;
            }
        }
        return null;
    }
}
