package com.example.myapplication; //Указываем, что класс Question лежит в пакете com.example.myapplication

public class Question { // создаем публичный класс Question
    private int questionTextId; // создаем целочисленную переменную questionTextId с модификатором доступа private; она будет доступна только внутри класса Question
    private boolean answerTrue; // создаем булеву переменную answerTrue с модификатором доступа private; она будет доступна только внутри класса Question

    public Question(int questionTextId, boolean answerTrue) { // Создаем конструктор объекта Question класса Question,
        this.questionTextId = questionTextId;                 // который принимает значение свойства questionTextId из переменной questionTextId
        this.answerTrue = answerTrue;                         // и значение свойства answerTrue из переменной answerTrue
    }

    public int getQuestionTextId() { // Создаем метод-геттер getQuestionTextId для свойства questionTextId объекта Question
        return questionTextId;       //  класса Question, чтобы получать значение переменной questionTextId извне класса Question
    }

    public void setQuestionTextId(int questionTextId) { // Создаем метод-cеттер setQuestionTextId для свойства questionTextId объекта Question
        this.questionTextId = questionTextId;           //  класса Question, чтобы задавать значение переменной questionTextId извне класса Question
    }

    public boolean isAnswerTrue() { // Создаем метод-геттер isAnswerTrue для свойства answerTrue объекта Question класса Question,
        return answerTrue;          // чтобы получать значение переменной answerTrue извне класса Question
    }

    public void setAnswerTrue(boolean answerTrue) { // Создаем метод-cеттер setAnswerTrue для свойства answerTrue объекта Question
        this.answerTrue = answerTrue;              //  класса Question, чтобы задавать значение переменной answerTrue извне класса Question
    }




}
