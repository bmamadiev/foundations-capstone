package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!
     */

    static final String URL = "https://jservice.kenzie.academy/api/clues";
    static int counter = 0;
    static int score = 0;

    static ArrayList<String> questionsList = new ArrayList<>();
    static ArrayList<String> categoryList = new ArrayList<>();
    static ArrayList<String> answerList = new ArrayList<>();
    static String answer;
    static String question;
    static String category;

    public static String randomQuestion() {

        int max = 100;
        int min = 1;
        int range = max - min + 1;

        int randInt = (int) (Math.random() * range) + min;

        answer = answerList.get(randInt);
        question = questionsList.get(randInt);
        category = categoryList.get(randInt);

        return "Category: " + categoryList.get(randInt) + "\n" + "Question: " + questionsList.get(randInt) + "\n";
    }

    public static void displayQuestion() {

        String httpResponse = CustomHttpClient.sendGET(URL);
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionsListDTO QuestionsDTOList = null;

        try {
            QuestionsDTOList = objectMapper.readValue(httpResponse, QuestionsListDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for (QuestionsDTO question : QuestionsDTOList.getClues()) {
            questionsList.add(question.getQuestion());
            categoryList.add(question.getCategory().getTitle());
            answerList.add(question.getAnswer());
        }

        System.out.println("Please answer random ten questions. For each correct answer, you will get one point. \n");

        while (counter < 10) {
            System.out.println(randomQuestion());
            counter++;
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your answer below.");

            String userInput = scan.nextLine();

            if (userInput.equalsIgnoreCase(answer.toLowerCase())) {
                System.out.println("\nYou got it!");
                score++;
                System.out.println("Your score: " + score + "\n");
            } else {
                System.out.println("\nTry your luck next time. The correct answer is: " + answer);
                System.out.println("Your score: " + score + "\n");
            }

            if (counter == 10) {
                System.out.println("Thank you for answering all the questions!");
                System.out.println("Your total score: " + score);
            }
        }
    }

    public static void main(String[] args) {
        //Write main execution code here
        displayQuestion();
    }
}