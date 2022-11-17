import {initFooter, setUpLocalStorage} from "../base.js"

function initHeader() {
    document.getElementById("nav-bar").innerHTML = "      <section id=\"home\">" +
        "        <div class=\"app-name\">truYum</div>" +
        "        <div class=\"app-logo\">" +
        "          <img src=\"images/cutlery.png\" alt=\"cutlery image\">" +
        "        </div>" +
        "      </section>" +
        "      <section id=\"actions\">" +
        "        <nav>" +
        "          <li><a href=\"menu-item-list-admin.html\">Menu</a></li>" +
        "        </nav>" +
        "      </section>";
}

export function onInit() {
    setUpLocalStorage();
    initHeader();
    initFooter();
}