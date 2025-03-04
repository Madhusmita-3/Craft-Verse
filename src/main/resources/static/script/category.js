let editIndex = null;

function openModal(editing = false, row = null) {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('category-modal').style.display = 'block';
    document.getElementById('modal-title').innerText = editing ? 'Edit Category' : 'Add Category';

    if (editing && row) {
        editIndex = row;
        document.getElementById('category-name').value = row.cells[0].innerText;
        document.getElementById('category-desc').value = row.cells[1].innerText;
    } else {
        document.getElementById('category-name').value = "";
        document.getElementById('category-desc').value = "";
    }
}

function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('category-modal').style.display = 'none';
    editIndex = null;
}

function saveCategory() {
    let name = document.getElementById('category-name').value;
    let desc = document.getElementById('category-desc').value;

    if (editIndex) {
        editIndex.cells[0].innerText = name;
        editIndex.cells[1].innerText = desc;
    } else {
        let table = document.getElementById('category-list');
        let row = table.insertRow();
        row.innerHTML = `<td>${name}</td>
                         <td>${desc}</td>
                         <td class="actions">
                             <i class='bx bx-edit edit' onclick="editCategory(this)"></i>
                             <i class='bx bx-trash delete' onclick="deleteCategory(this)"></i>
                         </td>`;
    }
    closeModal();
}

function deleteCategory(element) {
    element.parentElement.parentElement.remove();
}

function editCategory(element) {
    openModal(true, element.parentElement.parentElement);
}
