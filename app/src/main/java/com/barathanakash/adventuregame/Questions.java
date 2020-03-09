package com.barathanakash.adventuregame;

public class Questions {
    private Integer id;
    private String text;
    private Integer score;
    private Answer answer;

    public Integer getId() {
        return id;
    }
    public String getText(){
        return text;
    }
    public Integer getScore(){
        return score;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setText(String text) {
        this.text = text;
    }
}
