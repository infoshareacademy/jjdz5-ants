function validateAdditionForm() {
    var name = document.forms["additionForm"]["name"].value;
    var desc = document.forms["additionForm"]["desc"].value;
    if (name.length < 3) {
        alert("Nazwa trasy musi się składac z co najmniej 3 znaków.");
        return false;
    }
    if (desc.length < 10) {
        alert("Podany opis jest zbyt krótki (minimum 10 znaków).");
        return false;
    }
    if (!checkboxCount()) {
        alert("Należy wybrać co najmniej dwie atrakcje turystyczne.");
        return false;
    }
}

function checkboxCount() {
    var inputList = document.getElementsByTagName("input");
    var numChecked = 0;

    for (var i = 0; i < inputList.length; i++) {
        if (inputList[i].type == "checkbox" && inputList[i].checked) {
            numChecked = numChecked + 1;
        }
    }
    if (numChecked < 2) {
        return false;
    }
    return true;

}