function searchWidget() {
    let input = document.getElementById("search").value.toLowerCase();
    let table = document.getElementById("widgetTable").getElementsByTagName("tr");

    for (let i = 0; i < table.length; i++) {
        let widgetName = table[i].getElementsByTagName("td")[0];
        if (widgetName) {
            let textValue = widgetName.textContent || widgetName.innerText;
            if (textValue.toLowerCase().indexOf(input) > -1) {
                table[i].style.display = "";
            } else {
                table[i].style.display = "none";
            }
        }
    }
}