<form action="createTest" method="post">
    <label for="testTitle">Test Title:</label>
    <input type="text" id="testTitle" name="testTitle" required><br><br>

    <div id="questions">
        <div class="question">
            <label>Question:</label>
            <input type="text" name="questionText" required><br>

            <label>Answers:</label><br>
            <div class="answers">
                <input type="text" name="answer[0][]" required>
                <input type="radio" name="correctAnswer[0]" value="0"> Correct<br>
            </div>
            <button type="button" onclick="addAnswer(this, 0)">Add Answer</button>
        </div>
    </div>

    <button type="button" onclick="addQuestion()">Add Question</button><br><br>
    <input type="submit" value="Save Test">
</form>

<script>
let questionCount = 0;

function addQuestion() {
    questionCount++;
    const questionHTML = `
        <div class="question">
            <label>Question:</label>
            <input type="text" name="questionText" required><br>
            <label>Answers:</label><br>
            <div class="answers">
                <input type="text" name="answer[${questionCount}][]" required>
                <input type="radio" name="correctAnswer[${questionCount}]" value="0"> Correct<br>
            </div>
            <button type="button" onclick="addAnswer(this, ${questionCount})">Add Answer</button>
        </div>
    `;
    document.getElementById("questions").insertAdjacentHTML("beforeend", questionHTML);
}

function addAnswer(button, questionIndex) {
    const answersDiv = button.previousElementSibling;
    const answerCount = answersDiv.querySelectorAll('input[type="text"]').length;

    const answerHTML = `
        <input type="text" name="answer[${questionIndex}][]" required>
        <input type="radio" name="correctAnswer[${questionIndex}]" value="${answerCount}"> Correct<br>
    `;
    answersDiv.insertAdjacentHTML("beforeend", answerHTML);
}
</script>
