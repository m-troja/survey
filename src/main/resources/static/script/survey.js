function addAnswerInput(questionIndex) {
    const container = document.getElementById("answers-container-" + questionIndex);
    const inputCount = container.querySelectorAll("input[type='text']").length;

    const input = document.createElement("input");
    input.type = "text";
    input.name = "questions[" + questionIndex + "].answers[" + inputCount + "].text";
    input.placeholder = "Odpowiedź " + (inputCount + 1);

    const button = container.querySelector("button");
    container.insertBefore(input, button);
}
