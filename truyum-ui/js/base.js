const sandwich = {
    id: 1,
    name: "Sandwich",
    price: 99.00,
    active: true,
    launchDate: "2017-03-15",
    category: "Main Course",
    freeDelivery: true
}

const burger = {
    id: 2,
    name: "Burger",
    price: 129.00,
    active: true,
    launchDate: "2017-11-23",
    category: "Main Course",
    freeDelivery: false
}

const pizza = {
    id: 3,
    name: "Pizza",
    price: 149.00,
    active: true,
    launchDate: "2017-08-21",
    category: "Main Course",
    freeDelivery: false
}

const frenchFries = {
    id: 4,
    name: "French Fries",
    price: 57.00,
    active: false,
    launchDate: "2017-07-02",
    category: "Starters",
    freeDelivery: true
}

const chocolateBrownie = {
    id: 5,
    name: "Chocolate Brownie",
    price: 32.00,
    active: true,
    launchDate: "2017-11-02",
    category: "Dessert",
    freeDelivery: true
}

function getInitMenuItems() {
    return JSON.stringify([
        sandwich,
        burger,
        pizza,
        frenchFries,
        chocolateBrownie
    ]);
}

export function initFooter() {
    document.getElementById("footer").innerHTML = "Copyright &copy; 2022"
}

export function setUpLocalStorage() {
    if (typeof(Storage) === "undefined") return;
    typeof localStorage.getItem("menuItems") === "string" ?
        localStorage.getItem("menuItems") :
        localStorage.setItem("menuItems", getInitMenuItems());
}