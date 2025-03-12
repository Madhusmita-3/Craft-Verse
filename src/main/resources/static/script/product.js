let editIndex = null;

function openModal(editing = false, row = null) {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('product-modal').style.display = 'block';
    document.getElementById('modal-title').innerText = editing ? 'Edit Product' : 'Add Product';

    if (editing && row) {
        editIndex = row;
        document.getElementById('product-name').value = row.cells[1].innerText;
        document.getElementById('product-category').value = row.cells[2].innerText;
        document.getElementById('product-price').value = row.cells[3].innerText;
        document.getElementById('product-stock').value = row.cells[4].innerText;
    } else {
        document.getElementById('product-name').value = "";
        document.getElementById('product-category').value = "";
        document.getElementById('product-price').value = "";
        document.getElementById('product-stock').value = "";
    }
}

function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('product-modal').style.display = 'none';
    editIndex = null;
}

function saveProduct() {
    let name = document.getElementById('product-name').value;
    let category = document.getElementById('product-category').value;
    let price = document.getElementById('product-price').value;
    let stock = document.getElementById('product-stock').value;

    if (editIndex) {
        editIndex.cells[1].innerText = name;
        editIndex.cells[2].innerText = category;
        editIndex.cells[3].innerText = price;
        editIndex.cells[4].innerText = stock;
    } else {
        let table = document.getElementById('product-list');
        let row = table.insertRow();
        row.innerHTML = `<td><img src="https://via.placeholder.com/50"></td>
                         <td>${name}</td>
                         <td>${category}</td>
                         <td>${price}</td>
                         <td>${stock}</td>
                         <td class="actions">
                             <i class='bx bx-edit edit' onclick="editProduct(this)"></i>
                             <i class='bx bx-trash delete' onclick="deleteProduct(this)"></i>
                         </td>`;
    }
    closeModal();
}

function deleteProduct(element) {
    element.parentElement.parentElement.remove();
}

function editProduct(element) {
    openModal(true, element.parentElement.parentElement);
}
