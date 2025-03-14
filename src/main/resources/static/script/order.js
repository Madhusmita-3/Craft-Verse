let editIndex = null;

function openModal(editing = false, row = null) {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('order-modal').style.display = 'block';
    document.getElementById('modal-title').innerText = editing ? 'Edit Order' : 'Add Order';

    if (editing && row) {
        editIndex = row;
        document.getElementById('order-id').value = row.cells[0].innerText;
        document.getElementById('customer-name').value = row.cells[1].innerText;
        document.getElementById('order-date').value = row.cells[2].innerText;
        document.getElementById('order-total').value = row.cells[3].innerText;
        document.getElementById('order-status').value = row.cells[4].innerText;
    } else {
        document.getElementById('order-id').value = "";
        document.getElementById('customer-name').value = "";
        document.getElementById('order-date').value = "";
        document.getElementById('order-total').value = "";
        document.getElementById('order-status').value = "Pending";
    }
}

function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('order-modal').style.display = 'none';
    editIndex = null;
}

function saveOrder() {
    let orderId = document.getElementById('order-id').value;
    let customerName = document.getElementById('customer-name').value;
    let orderDate = document.getElementById('order-date').value;
    let orderTotal = document.getElementById('order-total').value;
    let orderStatus = document.getElementById('order-status').value;

    if (editIndex) {
        editIndex.cells[0].innerText = orderId;
        editIndex.cells[1].innerText = customerName;
        editIndex.cells[2].innerText = orderDate;
        editIndex.cells[3].innerText = orderTotal;
        editIndex.cells[4].innerText = orderStatus;
    } else {
        let table = document.getElementById('order-list');
        let row = table.insertRow();
        row.innerHTML = `<td>${orderId}</td>
                         <td>${customerName}</td>
                         <td>${orderDate}</td>
                         <td>${orderTotal}</td>
                         <td>${orderStatus}</td>
                         <td class="actions">
                             <i class='bx bx-edit edit' onclick="editOrder(this)"></i>
                             <i class='bx bx-trash delete' onclick="deleteOrder(this)"></i>
                         </td>`;
    }
    closeModal();
}

function deleteOrder(element) {
    element.parentElement.parentElement.remove();
}

function editOrder(element) {
    openModal(true, element.parentElement.parentElement);
}
