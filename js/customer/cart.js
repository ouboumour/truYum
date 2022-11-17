import {onInit} from "./common.js";

window.deleteFromCart = function deleteFromCart(itemId) {
    let cartItems = JSON.parse(localStorage.getItem("cartItems"));

    cartItems.splice(cartItems.indexOf(itemId), 1);

    localStorage.setItem("cartItems", JSON.stringify(cartItems));

    if (cartItems.length === 0) {
        window.location.href = "cart-empty.html";
        return false;
    } else {
        renderDatatable();
        document.getElementById("success-info").innerHTML = "Item removed from Cart Successfully";
        return false;
    }

    return false;
}

function renderDatatable() {
    let tableContent =  "<table>" +
        "  <tr>" +
        "    <th>Name</th>" +
        "    <th>Free Delivery</th>" +
        "    <th>Price</th>" +
        "  </tr>";

    const cartItems = JSON.parse(localStorage.getItem("menuItems"))
        .filter(item => JSON.parse(localStorage.getItem("cartItems"))?.includes(item.id));

    cartItems.forEach(item => {
            tableContent+= `<tr>
                             <td>${item.name}</td>
                             <td >${item.freeDelivery ? "Yes" : "No"}</td>
                             <td>Rs. ${item.price.toFixed(2)}</td>
                             <td><a onclick="return deleteFromCart(${item.id});" href="">Delete</a></td>
                        </tr>`;
        });

    tableContent += `<tr class="bold">
                         <td class="empty-cell"></td>
                         <td>Total</td>
                         <td>Rs. ${cartItems.reduce((acc, a) => acc + a.price, 0).toFixed(2)}</td>
                    </tr>`;

    document.getElementById("table-content").innerHTML = tableContent+" </table>";
}

window.onload = function () {
    onInit();
    renderDatatable();
}