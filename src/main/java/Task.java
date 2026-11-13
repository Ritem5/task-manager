package main.java;

public class Task {
    private  String text;
    private boolean isDone;

    public Task(String text, boolean markDone) {
        this.text = text;
        this.isDone = markDone;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[x] " + text;
        } else {
        return "[ ] " + text;
        }
    }
}
