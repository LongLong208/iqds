package beans;

public class AnswerItem {
    int answerItemId;
    String title;
    boolean isTrue;
    int questionId;

    public AnswerItem() {

    }

    public int getAnswerItemId() {
        return answerItemId;
    }

    public void setAnswerItemId(int answerItemId) {
        this.answerItemId = answerItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
