package com.example.myapplication; // Указываем, что класс MainActivity лежит в пакете com.example.myapplication

import androidx.appcompat.app.AppCompatActivity; // Импортируем класс AppCompatActivity из встроенного пакета androidx.appcompat.app

import android.os.Bundle;        // Импортируем класс Bundle из пакета android.os
import android.util.Log;         // Импортируем класс Log из пакета android.util
import android.view.View;        // Импортируем класс View из пакета android.view
import android.widget.Button;    // Импортируем класс Button из пакета android.widget
import android.widget.TextView;  // Импортируем класс TextView из пакета android.widget
import android.widget.Toast;     // Импортируем класс Toast из пакета android.widget

public class MainActivity extends AppCompatActivity { // Создаем основной класс MainActivity, который наследует импортированный класс AppCompacActivity.

    private Button truebtn;  // Создаем объект Button импортированного класса Button, называем его truebtn;
    private Button falsebtn; // Создаем второй объект Button импортированного класса Button, называем его falsebtn;
    private TextView textView; // Создаем объект TextView импортированного класса TextView, называем его textView;
    private Question[] questions = { //создаем массив объектов класса Question, который сами создали
            new Question(R.string.question1, true),  // Поочередно создаем пять элементов массива questions, в каждый из которых
            new Question(R.string.question2, false), // кладем объект Question со свойством questionTextId, взятым из string.xml
            new Question(R.string.question3, true),  // (ресурсного файла, в котором хранятся элементы <string>, содержимое которых
            new Question(R.string.question4, false), // можно получить по атрибуту name (в нашем случае question1...question5)), и
            new Question(R.string.question5, true) };// свойством answerTrue, которое мы задаем напрямую
    private int currentIndex=0; // создаем целочисленную переменную с модификатором доступа private (доступна только внутри MainActivity) со значением 0.
    private String TAG = "Информация о запуске метода: "; // создаем строковую переменную TAG (кстати, можно было, наверное, сделать ее final и считать
    // константой, раз мы даже назвали ее заглавными буквами) со значением "Информация о запуске метода: "



    @Override // Ниже будем переопределять встроенный метод onCreate()
    protected void onCreate(Bundle savedInstanceState) { // Как бы «создаем» метод onCreate, который в качестве аргументов берет объекты
                                                         // savedInstanceState встроенного класса Bundle...
        super.onCreate(savedInstanceState);              // ...наследуем всю дефолтную отработку метода onCreate...
        Log.d(TAG, "Метод onCreate() запущен.");   // ...и дополняем метод onCreate:
                                                         // <LOG.D.DESC>
                                                         // вызываем метод d импортированного класса Log. Он будет
                                                         // выводить в местную консоль Logcat строку, состоящую из
                                                         // значения переменной TAG и содержимого свойства msg,
                                                         // которое мы задали вручную.
                                                         // </LOG.D.DESC>
        if (savedInstanceState!= null) { // Если объект savedInstanceState класса Bundle не равен null,...
            currentIndex = savedInstanceState.getInt("index"); // ...получаем из объекта savedInstanceState значение по текстовому ключу index,
                                                                    //  после чего трансформируем его в целое число с помощью метода getInt()
                                                                    // и присваиваем это число как значение переменной currentIndex значение.
                                                                    // Пару ключ-значение создаем ниже в переопределенном методе onSaveInstanceState().

                                                                    // Короче, весь if нужен для того, чтобы при возврате к свернутому/повернутому
                                                                    // приложению начать с того вопроса, на котором мы его свернули/повернули

            Log.d("Содержимое savedInstanceState", savedInstanceState.toString()); // при первом запуске null (это я для себя записал на трансляции)
        }
        setContentView(R.layout.activity_main); // С помощью встроенного метода setContentView рисуем базовый интерфейс нашей Main Activity,
                                                // описанный в ресурсном файле activity_main.xml, из базы макетов R.layout, отдав в метод ее ID
        truebtn = findViewById(R.id.truebtn);   // В activity_main находим элемент <Button> с атрибутом android:id cо значением truebtn — и все
                                                // его прочие атрибуты как свойства передаем в объект truebtn класса Button
        falsebtn = findViewById(R.id.falsebtn); // В activity_main находим элемент <Button> с атрибутом android:id cо значением falsebtn — и все
                                                // его прочие атрибуты как свойства передаем в объект falsebtn класса Button
        textView = findViewById(R.id.TextView); // В activity_main находим элемент <TextView> с атрибутом android:id cо значением TextView — и все
                                                // его прочие атрибуты как свойства передаем в объект textView класса TextView

        int question = questions[currentIndex].getQuestionTextId(); // Создаем целочисленную переменную question,
                                                                    // присваиваем ей значение, равное значению
                                                                    // свойства questionTextId экземпляра
                                                                    // объекта Question, который лежит в массиве
                                                                     // questions под индексом currentIndex
        textView.setText(question); // В объект textView методом setText выводим текст,
                                    // который лежит в файле <string> и имеет ID,
                                    // равное значению переменной question

                                    // В общем, выводим на экран текущий вопрос

       truebtn.setOnClickListener(new View.OnClickListener() { // На объект truebtn класса Button методом setOnClickListener вешаем
                                                               // обработчик-«слушатель» события нажатия, внутри которого определяем
                                                               // интерфейс setOnClickListener и описываем его callback при нажатии:
            @Override                           // (Ниже будем переопределять встроенный метод onClick())
            public void onClick(View view) {    // Как бы «создаем» метод onCreate, который в качестве аргументов берет объекты
                                                // view встроенного класса View
            checkAnswer(true);       // Вызываем метод checkAnswer (описан ниже) и передаем ему значение true
            updateQuestion();                   // Вызываем метод updateQuestion (описан ниже)
            }
        });                                     // Короче, говорим, что делать и показывать, когда пользователь нажал кнопку «Да»

        falsebtn.setOnClickListener(new View.OnClickListener() { // На объект falsebtn класса Button методом setOnClickListener вешаем
                                                                 // обработчик-«слушатель» события нажатия, внутри которого определяем
                                                                 // интерфейс setOnClickListener и описываем его callback при нажатии:
            @Override                           // (Ниже будем переопределять встроенный метод onClick())
            public void onClick(View view) {    // Как бы «создаем» метод onCreate, который в качестве аргументов берет объекты
                                                // view встроенного класса View
                checkAnswer(false);  // Вызываем метод checkAnswer (описан ниже) и передаем ему значение true
                updateQuestion();               // Вызываем метод updateQuestion (описан ниже)
            }
        });                                     // Короче, говорим, что делать и показывать, когда пользователь нажал кнопку «Нет»
    }

    private void updateQuestion() {             // Создаем метод updateQuestion(), который доступен только внутри класса,
                                                // отрабатывает и ничего не возвращает
        currentIndex=(currentIndex+1)%questions.length; // Кладем в переменную currentIndex остаток от деления ее самой же +1 на
                                                        // длину массива questions, в котором содержатся вопросы. Чтобы после
                                                        // отработки метода перейти к следующему элементу массива, а при
                                                        // достижении конца массива откатиться на нулевой элемент.
        textView.setText(questions[currentIndex].getQuestionTextId()); // В объект textView методом setText выводим текст,
                                                                       // который лежит в файле <string> и имеет ID,
                                                                       // равное свойству questionTextId экземпляра
                                                                       // объекта Question, который лежит в массиве questions
                                                                       //  под индексом currentIndex... Фух!

                                                                       // В общем, меняем на экране текст вопроса.

    }

    private void checkAnswer(boolean userAnswer) { // Создаем метод updateQuestion(), который доступен только внутри класса,
                                                   // принимает в качестве аргумента значение переменной userAnswer,
                                                   // отрабатывает и ничего не возвращает вовне.
        if (questions[currentIndex].isAnswerTrue() == userAnswer) // (*) Если у объекта Question, который лежит в массиве
                                                                  // questions под индексом currentIndex, свойство
                                                                  // answerTrue, полученное геттером isAnswerTrue(),
                                                                  // равно значению userAnswer...
            Toast.makeText(MainActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show(); //...в текущей
            // activity с помощью объекта Toast класса Toast на короткое время LENGTH_SHORT методом show() выводим
            // текст, содержащийся в ресурсном файле strings.xml в элементе <string> c значением атрибута name,
            // равным correct_toast.

            // Выводим на экран слово «Правильно», в общем.

            else Toast.makeText(MainActivity.this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

            // ...в противном случае (если у объекта Question, который лежит в массиве questions под индексом currentIndex,
            // свойство answerTrue, полученное геттером isAnswerTrue(), НЕ равно значению userAnswer) в текущей
            // activity с помощью объекта Toast класса Toast на короткое время LENGTH_SHORT методом show() выводим
            // текст, содержащийся в ресурсном файле strings.xml в элементе <string> c значением атрибута name,
            // равным incorrect_toast.

            // «Неправильно» на экран выводим ненадолго.

    }

    @Override // Ниже будем переопределять встроенный метод onSaveInstanceState()
    public void onSaveInstanceState(Bundle savedInstanceState){ // Как бы «создаем» метод onSaveInstanceState(), который
                                                                // в качестве аргументов берет объекты savedInstanceState
                                                                // встроенного класса Bundle...
        super.onSaveInstanceState(savedInstanceState); // ...наследуем всю дефолтную отработку метода onSaveInstanceState...
        Log.d(TAG, "Метод onSaveInstanceState() запущен!!!"); // ...плюс дописываем своё: см. строку 34 <LOG.D.DESC>
        savedInstanceState.putInt("index",currentIndex); // С помощью метода putInt() присваиваем объекту savedInstanceState
                                                         // значение свойства key ("index) и значение свойства value,
                                                         // в которое кладем значение переменной currentIndex.
                                                         // Нам это понадобится выше, чтобы извлечь текущее состояние activity.
                                                         // Чтобы начать с того же вопроса, на котором остановились,
                                                         // после всяких кручений-верчений аппарата, в общем.
    }

    // Дальше будем переопределять методы жизненного цикла activity, чтобы пошагово логировать их вызов

    @Override                                         // Ниже будем переопределять встроенный метод onStart()
    public void onStart(){                            // Как бы «создаем» метод onStart(),...
        super.onStart();                              // ...наследуем всю дефолтную отработку метода onStart()...
        Log.d(TAG, "Метод onStart() запущен");  //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }

    @Override                                         // Ниже будем переопределять встроенный метод onResume()
    public void onResume(){                           // Как бы «создаем» метод onResume(),...
        super.onResume();                             // ...наследуем всю дефолтную отработку метода onResume()...
        Log.d(TAG, "Метод onResume() запущен"); //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }

    @Override                                         // Ниже будем переопределять встроенный метод onPause()
    public void onPause(){                            // Как бы «создаем» метод onPause(),...
        super.onPause();                              // ...наследуем всю дефолтную отработку метода onPause()...
        Log.d(TAG, "Метод onPause() запущен");  //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }

    @Override                                         // Ниже будем переопределять встроенный метод onStop()
    public void onStop(){                             // Как бы «создаем» метод onStop(),...
        super.onStop();                               // ...наследуем всю дефолтную отработку метода onStop()...
        Log.d(TAG, "Метод onStop() запущен");   //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }

    @Override                                          // Ниже будем переопределять встроенный метод onDestroy()
    public void onDestroy(){                           // Как бы «создаем» метод onDestroy()),...
        super.onDestroy();                             // ...наследуем всю дефолтную отработку метода onDestroy()...
        Log.d(TAG, "Метод onDestroy() запущен"); //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }

    @Override                                          // Ниже будем переопределять встроенный метод onRestart()
    public void onRestart(){                           // Как бы «создаем» метод onRestart(),...
        super.onRestart();                             // ...наследуем всю дефолтную отработку метода onRestart()...
        Log.d(TAG, "Метод onRestart() запущен"); //...и дописываем своё: см. строку 34 <LOG.D.DESC>
    }
}