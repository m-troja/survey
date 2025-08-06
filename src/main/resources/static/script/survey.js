function addAnswerInput(questionIndex) {
    const container = document.getElementById("answers-container-" + questionIndex);
    const inputCount = container.querySelectorAll("input[type='text']").length;

    const button = container.querySelector("button"); // pobieramy przycisk z tego kontenera

    if ( inputCount < 10)
    {
        const input = document.createElement("input");
        input.type = "text";
        input.name = "questions[" + questionIndex + "].answers[" + inputCount + "].text";
        input.placeholder = "Odpowiedź " + (inputCount + 1);
        input.maxLength=20;
        const button = container.querySelector("button");
        container.insertBefore(input, button);
    }
    else
    {
           button.style.backgroundColor = "brown";
           button.textContent="Limit 10 odpowiedzi!"
    }
}