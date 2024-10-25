package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// ToDo 4: Done Match the UI as in drawable gpa_design.png. Use the following hints:
// - The background color should be Color.Cyan
// - Fix padding, alignment, and keypad type

// ToDo 5: Done  Add the GpaAppScreen composable button that clears the input fields when clicked


@Composable
fun GpaAppScreen() {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }


    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Calulate GPA") }

    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .padding(16.dp)
            .fillMaxSize()
        ,verticalArrangement = Arrangement.Center
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = grade1,
            onValueChange = { grade1 = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),  // Ensure text field takes up full width
            label = { Text("Course 1 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        OutlinedTextField(
            value = grade2,
            onValueChange = { grade2 = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            label = { Text("Course 2 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )



        OutlinedTextField(
            value = grade3,
            onValueChange = { grade3 = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            label = { Text("Course 3 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        Button(
            onClick = {
                if (btnLabel == "Compute GPA") {
                    // Compute GPA
                    val gpaVal = calGPA(grade1, grade2, grade3)
                    if (gpaVal != null) {
                        gpa = gpaVal.toString()
                        backColor = when {
                            gpaVal < 60 -> Color.Red
                            gpaVal in 60.0..79.0 -> Color.Yellow
                            else -> Color.Green
                        }
                        btnLabel = "Clear"  // Change the button label to "Clear" after computing GPA
                    } else {
                        gpa = "Invalid input"
                    }
                } else {
                    // Clear the input fields and reset the GPA
                    grade1 = ""
                    grade2 = ""
                    grade3 = ""
                    gpa = ""
                    backColor = Color.White
                    btnLabel = "Compute GPA"  // Change the button label back to "Compute GPA"
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()  // Ensure the button takes up full width
        ) {
            Text(btnLabel)
        }

        // Display GPA result if it's not empty
        if (gpa.isNotEmpty()) {
            Text(
                text = "GPA: $gpa",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.headlineSmall  // Updated to Material3's equivalent of h5
            )
        }
    }
}


fun calGPA(grade1: String, grade2: String, grade3: String): Double? {
    return try {
        val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
        return grades.average()
    } catch (e: NumberFormatException) {
        null
    }
}

