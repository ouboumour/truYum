import {onInit} from "./common.js";

// Bring the function from global scope to module scope
window.addToCart = function addToCart(editedItemId) {
    let cartItems = typeof localStorage.getItem("cartItems") === "string"
        ? JSON.parse(localStorage.getItem("cartItems"))
        : [];

    if (!cartItems.includes(editedItemId)) {
        cartItems.push(editedItemId);
    }

    localStorage.setItem("cartItems", JSON.stringify(cartItems));

    document.getElementById("success-info").innerHTML = "Item added to Cart Successfully";
    return false;
}

function renderDatatable() {
    let tableContent =  "<table>" +
                        "  <tr>" +
                        "    <th>Name</th>" +
                        "    <th>Free Delivery</th>" +
                        "    <th>Price</th>" +
                        "    <th>Category</th>" +
                        "    <th>Action</th>" +
                        "  </tr>";

    JSON.parse(localStorage.getItem("menuItems"))
        .filter(item => item.active)
        .forEach(item => {
        tableContent+= `<tr>
                             <td>${item.name}</td>
                             <td>${item.freeDelivery ? "Yes" : "No"}</td>
                             <td>Rs. ${item.price}</td>
                             <td>${item.category}</td>
                             <td><a href="" onclick="return addToCart(${item.id})">Add to Cart</a></td>
                        </tr>`;
    });

    document.getElementById("table-content").innerHTML = tableContent+" </table>";
}

window.onload = function () {
    onInit();
    renderDatatable();
};