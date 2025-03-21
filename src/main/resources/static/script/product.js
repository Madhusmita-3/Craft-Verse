let editIndex = null;

async function openModal(productId = null) {
  document.getElementById("overlay").style.display = "block";
  document.getElementById("product-modal").style.display = "block";
  if (productId) {
    const response = await fetch(`/admin/products/${productId}`).catch((err) =>
      console.error(err)
    );

    const product = await response.json();
    updateFields(product);
  }
}

function updateFields(product) {
    document.getElementById('product-name').value = product.productTitle;
    document.getElementById('category').value = product.category.categoryId;
    document.getElementById('product-price').value = product.productPrice;
    document.getElementById('product-stock').value = product.productStockQuantity;
    document.getElementById('product-description').value = product.productDescription;
    document.getElementById('productStatus').value = product.productStatus;

    document.getElementById('imageUrl').value = product.productImageUrl;
    document.getElementById('productId').value = product.productId;
}

function closeModal() {
  document.getElementById("overlay").style.display = "none";
  document.getElementById("product-modal").style.display = "none";
}

function saveProduct() {
  // let name = document.getElementById('product-name').value;
  // let category = document.getElementById('product-category').value;
  // let price = document.getElementById('product-price').value;
  // let stock = document.getElementById('product-stock').value;

  // if (editIndex) {
  //     editIndex.cells[1].innerText = name;
  //     editIndex.cells[2].innerText = category;
  //     editIndex.cells[3].innerText = price;
  //     editIndex.cells[4].innerText = stock;
  // } else {
  //     let table = document.getElementById('product-list');
  //     let row = table.insertRow();
  //     row.innerHTML = `<td><img src="https://via.placeholder.com/50"></td>
  //                      <td>${name}</td>
  //                      <td>${category}</td>
  //                      <td>${price}</td>
  //                      <td>${stock}</td>
  //                      <td class="actions">
  //                          <i class='bx bx-edit edit' onclick="editProduct(this)"></i>
  //                          <i class='bx bx-trash delete' onclick="deleteProduct(this)"></i>
  //                      </td>`;
  // }
  closeModal();
}

function deleteProduct(element) {
  element.parentElement.parentElement.remove();
}

function editProduct(element) {
  openModal(true, element.parentElement.parentElement);
}
function increaseStock() {
  let stockInput = document.getElementById("product-stock");
  stockInput.value = parseInt(stockInput.value) + 1;
}

function decreaseStock() {
  let stockInput = document.getElementById("product-stock");
  if (parseInt(stockInput.value) > 0) {
    stockInput.value = parseInt(stockInput.value) - 1;
  }
}
