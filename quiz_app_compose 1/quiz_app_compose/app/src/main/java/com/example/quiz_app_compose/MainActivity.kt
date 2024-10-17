package com.example.quiz_app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app_compose.ui.theme.Quiz_app_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Quiz_app_composeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) { innerPadding ->
                    Question(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val fontSize = 32.sp

@Composable
fun Question(modifier: Modifier = Modifier) {
    var questionId by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
    ) {
        QuestionText(Questions.questions[questionId].text, questionId)
        AnswersText(answers = Questions.questions[questionId].answers)

        if (questionId < Questions.questions.size - 1)
            NextButton(
                increaseQuestionId = {
                    questionId++
                })
        else
            SubmitButton()
    }
}

@Composable
fun QuestionText(question: String, questionId: Int) {
    Text(
        text = "${questionId + 1}. $question",
        fontSize = fontSize,
        lineHeight = fontSize * 1.2
    )
}

@Composable
fun AnswersText(answers: Array<String>) {
    var selectedOption by remember { mutableStateOf(answers[0]) }

    Column(
        modifier = Modifier.padding(vertical = 32.dp)
    ) {
        answers.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = {
                        selectedOption = option
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 2.dp),
                    fontSize = fontSize
                )
            }
        }
    }
}

@Composable
fun NextButton(increaseQuestionId: () -> Unit, increaseScore: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                increaseQuestionId()


            }
        ) {
            Text(
                text = "Next",
                modifier = Modifier.padding(0.dp),
                fontSize = fontSize
            )
        }
    }
}

@Composable
fun SubmitButton() {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {}
        ) {
            Text(
                text = "Next",
                modifier = Modifier.padding(0.dp),
                fontSize = fontSize
            )
        }
    }
}

@Preview
@Composable
fun QuestionPreview() {
    Question()
}