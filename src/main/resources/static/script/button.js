function addAnswerInput(questionIndex) {
    const container = document.getElementById("answers-container-" + questionIndex);
    const inputCount = container.querySelectorAll("input[type='text']").length;
    const button = container.querySelector("button");
    const answerMaxLength = parseInt(container.getAttribute('data-answer-max-length'));
    console.log("inputCount: " + inputCount);
    if (inputCount < 10) {
        const input = document.createElement("input");
        input.type = "text";
        input.name = "questions[" + questionIndex + "].answers[" + inputCount + "].text";
        input.placeholder = "Odpowiedź " + (inputCount + 1);
        input.maxLength = answerMaxLength;
        container.insertBefore(input, button);
    }
    if (inputCount == 9) {
    button.remove();
    }
}