package com.company;

public class ActionsParser {
    Action[] parse (String[] arr) {
        Action[] result = new Action[arr.length];
        int counter = 0;
        for (String element : arr) {
            switch (element) {
                case "d-":
                    result[counter++] = Action.DAY_EARLIER;
                    break;
                case "d+":
                    result[counter++] = Action.DAY_LATER;
                    break;
                case "t-":
                    result[counter++] = Action.TIME_EARLIER;
                    break;
                case "t+":
                    result[counter++] = Action.TIME_LATER;
                    break;
            }
        }
        return result;
    }
}
