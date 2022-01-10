package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionsListDTO {


    @JsonProperty("clues")
    private List<QuestionsDTO> clues;

    public List<QuestionsDTO> getClues() {
        return clues;
    }

    public void setClues(List<QuestionsDTO> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return "QuestionsListDTO{" +
                "clues=" + clues +
                '}';
    }
}
