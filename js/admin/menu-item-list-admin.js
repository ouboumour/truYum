import {onInit} from "./common.js";

// Bring the function from global scope to module scope
window.memorizeClickedItem = function memorizeClickedItem(editedItemId) {
    localStorage.setItem("editedItemId", editedItemId);
}

function renderDatatable() {
    let tableContent =  "<table>" +
                        "  <tr>" +
                        "    <th>Name</th>" +
                        "    <th>Price</th>" +
                        "    <th>Active</th>" +
                        "    <th>Date of Launch</th>" +
                        "    <th>Category</th>" +
                        "    <th>Free Delivery</th>" +
                        "    <th>Action</th>" +
                        "  </tr>";
    JSON.parse(localStorage.getItem("menuItems")).forEach(item => {
       tableContent+= `<tr>
                             <td>${item.name}</td>
                             <td>Rs. ${item.price}</td>
                             <td>${item.active ? "Yes" : "No"}</td>
                             <td>${item.launchDate.split("-").reverse().join("/")}</td>
                             <td>${item.category}</td>
                             <td>${item.freeDelivery ? "Yes" : "No"}</td>
                             <td><a href="edit-menu-item.html" onclick='memorizeClickedItem(${item.id});'>Edit</a></td>
                        </tr>`;
    });

    document.getElementById("table-content").innerHTML = tableContent+" </table>";
}

window.onload = function () {
    onInit();
    renderDatatable();
}