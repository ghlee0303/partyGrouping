window.onload = function () {
    eventBinding();
}

function eventBinding() {
    document.querySelector("#container-exchange-search .button").addEventListener("click", exchangeSearch);
}

function exchangeSearch() {
    const parentElement = this.parentElement;
    const exchangeKey = parentElement.querySelector(".input").value;
    requestExchangeSearch(exchangeKey)
        .then((data) => {
            let e = new ExchangeParty();
            e.request(data);
            return e;
        })
        .then((exchangeParty) => createExchangeMain(exchangeParty));
}

async function createExchangeMain(exchangeParty) {
    const containerParty = document.getElementsByClassName("container-party")[0];
    const containerExchange = createExchangeDiv(exchangeParty);
    containerParty.insertBefore(containerExchange, containerParty.firstChild);

    return exchangeParty;

}