window.onload = function () {
    eventBinding();
}

function eventBinding() {
    document.querySelector("#container-search .button").addEventListener("click", exchangeSearch);
}

function exchangeSearch() {
    const parentElement = this.parentElement;
    const search = parentElement.querySelector(".input").value;
    const type = parentElement.querySelector(".search-select").value;
    requestExchangeSearch(search, type)
        .then((resList) => {
            for (let res of resList) {
                createExchangeMainDiv(res);
            }
        });
}

function createExchangeMainDiv(exchangeParty) {
    const containerParty = document.getElementsByClassName("container-party")[0];
    const containerExchange = createExchangeDiv(exchangeParty);
    containerParty.insertBefore(containerExchange, containerParty.firstChild);

    return exchangeParty;
}