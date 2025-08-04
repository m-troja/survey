 function addAnswerInput(questionIndex) {
            const container = document.getElementById("answers-" + questionIndex);
            const inputCount = container.querySelectorAll("input").length;

            const input = document.createElement("input");
            input.type = "text";
            input.name = "answers[" + questionIndex + "][" + inputCount + "]";
            input.placeholder = "Odpowiedź " + (inputCount + 1);
            container.appendChild(input);
        }