function getFormattedTime() {
    let date = new Date();

    let day = date.getDate();
    let month = date.getMonth();
    let year = date.getFullYear();

    let sec = date.getSeconds();
    let min = date.getMinutes();
    let hr = date.getHours();

    return (
        day
        + "-"
        + month
        + "-"
        + year
        + " "
        + sec
        + "-"
        + min
        + "-"
        + hr
    )
}

function injectCurrentTime() {
    document.getElementById("currentTime").innerHTML = getFormattedTime();
}

function updateFormWithRequestedSeat() {

    // Get the requested seat from just the visible HTML under the first H3 on /book
    // noinspection UnnecessaryLocalVariableJS
    let incoming = document.getElementById("requested_seat").innerText.substr(0,2);

    // Set the value in the form
    document.getElementById("requested_seat_form").value = incoming;

}

function generateSecurityCode() {

    document.getElementById("security_code_generated").innerHTML =
        Math.random().toString(36).substr(2, 6).toUpperCase();

}

/**
 * Validate the security code
 * @returns {boolean} True if verified successfully
 */
function validateSecurityCode() {

    let generatedCode = document.getElementById("security_code_generated").innerHTML;
    let userEnteredCode = document.getElementById("security_code_returned").value.toUpperCase();

    return generatedCode === userEnteredCode;

}

function validateForm() {

    let validated = true;

    // Test UserId input
    let regexUserId = /^\D+$/;
    let enteredUserId = document.getElementById("user_id").value;

    if (!regexUserId.test(enteredUserId)) {
        window.alert("User ID invalid!");
        validated = false;
    }

    // Test email input
    let regexEmail = /^\w+@\w+/;
    let enteredEmail = document.getElementById("user_email").value;

    if (!regexEmail.test(enteredEmail)) {
        window.alert("Email address invalid!");
        validated = false;
    }

    // Test security code
    if (!validateSecurityCode()) {
        window.alert("Security code is invalid!");
        validated = false;
    }

    return validated;

        }
