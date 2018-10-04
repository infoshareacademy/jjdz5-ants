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
    if (chosenPlacesCounter() < 2) {
        alert("Należy wybrać co najmniej dwie atrakcje turystyczne.");
        return false;
    }
}

function chosenPlacesCounter() {
    return document.getElementById("selectionTable").getElementsByTagName("tr").length;
}