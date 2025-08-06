function validateAnswers() {
    const questionBlocks = document.querySelectorAll('.question-block');
    for (const block of questionBlocks) {
        const answers = block.querySelectorAll('input[type="text"]');
        let filledCount = 0;
        answers.forEach(input => {
            if (input.value.trim() !== '') {
                filledCount++;
            }
        });
        if (filledCount < 3) {
            alert('Każde pytanie musi mieć co najmniej 3 wypełnione odpowiedzi!');
            for (const input of answers) {
                if (input.value.trim() === '') {
                    input.focus();
                    break;
                }
            }
            return false;
        }
    }
    return true;
}