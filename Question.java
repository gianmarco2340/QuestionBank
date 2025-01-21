/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question;

import javafx.scene.image.Image;

/**
 *
 * @author brato
 */
public class Question {

    private int contentId;
    private String cferlevel;
    private String difficulty;
    private String skill;
    private String questionText;
    private String instructionText;
    private String passage;
    private String optionsText;
    private String image;
    private String audio;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
    private String exam;

    // Constructor para language use
    public Question(int contentId, String cferlevel, String difficulty, String skill, String instructionText, String questionText, String optionsText, String image, String optionA, String optionB, String optionC, String optionD, String correctOption, String exam) {
        this.contentId = contentId;
        this.cferlevel = cferlevel;
        this.difficulty = difficulty;
        this.skill = skill;
        this.instructionText = instructionText;
        this.questionText = questionText;
        this.optionsText = optionsText;
        this.image = image;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.exam = exam;
    }

    // Constructor para Listening
    public Question(int contentId, String cferlevel, String difficulty, String skill, String instructionText, String questionText, String passage, String optionsText, String image, String audio, String optionA, String optionB, String optionC, String optionD, String correctOption, String exam) {
        this.contentId = contentId;
        this.cferlevel = cferlevel;
        this.difficulty = difficulty;
        this.skill = skill;
        this.instructionText = instructionText;
        this.questionText = questionText;
        this.passage = passage;
        this.optionsText = optionsText;
        this.image = image;
        this.audio = audio;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.exam = exam;
    }

    // Constructor para Reading
    public Question(int contentId, String cferlevel, String difficulty, String skill, String instructionText, String questionText, String passage, String optionsText, String image, String optionA, String optionB, String optionC, String optionD, String correctOption, String exam) {
        this.contentId = contentId; 
        this.cferlevel = cferlevel;
        this.difficulty = difficulty;
        this.skill = skill;
        this.instructionText = instructionText;
        this.questionText = questionText;
        this.passage = passage;
        this.optionsText = optionsText;
        this.image = image;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.exam = exam;
    }

    // Constructor para speaking y writing
    public Question(int contentId, String cferlevel, String difficulty, String skill, String instructionText, String questionText, String passage, String optionsText, String image, String audio, String exam) {
        this.contentId = contentId;
        this.cferlevel = cferlevel;
        this.difficulty = difficulty;
        this.skill = skill;
        this.instructionText = instructionText;
        this.questionText = questionText;
        this.passage = passage;
        this.optionsText = optionsText;
        this.image = image;
        this.audio = audio;
        this.exam = exam;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getSkill() {
        return skill;
    }

    public String getInstructionText() {
        return instructionText;
    }

    public int getContentId() {
        return contentId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getPassage() {
        return passage;
    }

    public String getOptionsText() {
        return optionsText;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public String getCferlevel() {
        return cferlevel;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }
     

    public void setCferlevel(String cferlevel) {
        this.cferlevel = cferlevel;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public void setOptionsText(String optionsText) {
        this.optionsText = optionsText;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    @Override
    public String toString() {
        return "Question{"
                + ", contentId=" + contentId
                + ", difficulty='" + difficulty + '\''
                + ", skill='" + skill + '\''
                + ", questionText='" + questionText + '\''
                + ", instructionText='" + instructionText + '\''
                + ", passage='" + passage + '\''
                + ", optionsText='" + optionsText + '\''
                + ", image='" + image + '\''
                + ", audio='" + audio + '\''
                + ", optionA='" + optionA + '\''
                + ", optionB='" + optionB + '\''
                + ", optionC='" + optionC + '\''
                + ", optionD='" + optionD + '\''
                + ", correctOption='" + correctOption + '\''
                + ", exam = '" + exam + '\''
                + '}';
    }
}
