let editIndex = null;

 async function openModal(categoryId =null) {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('category-modal').style.display = 'block';
    if (categoryId) {
       const response = await fetch(`/admin/categories/${categoryId}`).catch((err) =>
             console.error(err)
    );
     const category = await response.json();
     updateFields(category);
    }
}
function updateFields(category){
    document.getElementById(`category-name`).value= category.categoryTitle;
    document.getElementById(`category-desc`).value= category.categoryDescription;
    document.getElementById(`category-img`).value = category.categoryImage;
    document.getElementById(`category-img`).value = category.categoryHoverImage;
    document.getElementById(`category-status`).value = category.categoryStatus;
}

function closeModal() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('category-modal').style.display = 'none';
    
}

function saveCategory() {
    // let name = document.getElementById('category-name').value;
    // let desc = document.getElementById('category-desc').value;

    // if (editIndex) {
    //     editIndex.cells[0].innerText = name;
    //     editIndex.cells[1].innerText = desc;
    // } else {
    //     let table = document.getElementById('category-list');
    //     let row = table.insertRow();
    //     row.innerHTML = `<td>${name}</td>
    //                      <td>${desc}</td>
    //                      <td class="actions">
    //                          <i class='bx bx-edit edit' onclick="editCategory(this)"></i>
    //                          <i class='bx bx-trash delete' onclick="deleteCategory(this)"></i>
    //                      </td>`;
    // }
    closeModal();
}

function deleteCategory(element) {
    element.parentElement.parentElement.remove();
}

function editCategory(element) {
    openModal(true, element.parentElement.parentElement);
}
