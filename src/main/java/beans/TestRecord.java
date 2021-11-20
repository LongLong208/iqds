package beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestRecord {

    private List<Question> questions;
    private Map<Integer, List<AnswerItem>> map;
    private List<Integer> chosen;
    private Date time;

    public TestRecord() {
        time = new Date();
    }

    public TestRecord(List<Question> questions, Map<Integer, List<AnswerItem>> map) {
        this();
        this.questions = questions;
        this.map = map;
    }

    public Date getTime() {
        return time;
    }

    public Map<Integer, List<AnswerItem>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<AnswerItem>> map) {
        this.map = map;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Integer> getChosen() {
        return chosen;
    }

    public void setChosen(List<Integer> chosen) {
        this.chosen = chosen;
    }

}
