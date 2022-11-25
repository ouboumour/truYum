import {onInit} from "./common.js";

const menuItems = JSON.parse(localStorage.getItem("menuItems"));
const editedItemId = +localStorage.getItem("editedItemId");

const editedItem = menuItems.find(item => item.id === editedItemId);

function renderFormValues() {
    document.getElementById("title").value = editedItem.name;

    document.getElementById("price").value = editedItem.price;

    document.getElementById("yes").checked = editedItem.active;
    document.getElementById("no").checked = !editedItem.active;

    document.getElementById("dateOfLaunch").value = editedItem.launchDate;

    document.getElementById("category").value = editedItem.category;

    document.getElementById("freeDelivery").checked = editedItem.freeDelivery;
}

function submitItemEdition() {
    editedItem.name = document.getElementById("title").value;
    editedItem.price = +document.getElementById("price").value;
    editedItem.active = document.getElementById("yes").checked;
    editedItem.launchDate = document.getElementById("dateOfLaunch").value;
    editedItem.category = document.getElementById("category").value;
    editedItem.freeDelivery = document.getElementById("freeDelivery").checked;

    localStorage.setItem("menuItems", JSON.stringify(menuItems));
}

window.onload = function () {
    onInit();
    renderFormValues();
    document
        .getElementById("submit-button")
        .addEventListener("click", submitItemEdition, false);
};