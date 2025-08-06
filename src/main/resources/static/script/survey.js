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
// Check if cookies have been accepted
function hasAcceptedCookies() {
    return localStorage.getItem('cookiesAccepted') === 'true';
}

// Show popup if not accepted
window.addEventListener('load', function() {
    if (!hasAcceptedCookies()) {
        var popup = document.getElementById('cookie-popup');
        if (popup) {
            popup.style.display = 'block';
        }
    }

    var okButton = document.getElementById('cookie-ok-button');
    if (okButton) {
        okButton.addEventListener('click', function() {
            localStorage.setItem('cookiesAccepted', 'true');
            var popup = document.getElementById('cookie-popup');
            if (popup) {
                popup.style.display = 'none';
            }
        });
    }
});
