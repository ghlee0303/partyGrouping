class ExchangeParty {
    constructor() {
        this.id = null;
        this.exchangeKey = null;
        this.characters = [];
    }

    // 교환파티 등록 후 사용
    creation(id, e, c) {
        this.id = id;
        this.exchangeKey = e;
        for (let objKey in c) {
            this.characters.push(c[objKey]);
        }
    }

    // 교환파티 조회 후 사용
    request(data) {
        this.id = data.id;
        this.exchangeKey = data.exchangeKey;
        this.characters.push(data.character1);
        this.characters.push(data.character2);
        this.characters.push(data.character3);
        this.characters.push(data.character4);
    }
}

function requestExchangeSearch(id) {
    const serverUri = `/exchange_search?id=${id}`;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestExchangeSessionPage(page) {
    const serverUri = "/exchange_session?page="+page;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestExchangeCreation(createExchange) {
    const serverUri = "/exchange_manage";
    const keys = Object.keys(createExchange);
    const exchangeReq = exchangePostBodyGenerated(keys, createExchange);

    if (keys.length !== 4) {
        throw new Error("멤버가 4명이 되어야합니다.");
    }

    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(exchangeReq)
    };

    return fetchData(serverUri, option)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestExchangeDelete(id) {
    const serverUri = "/exchange_manage?id="+id;
    const option = {
        method: 'DELETE'
    };

    return fetchData(serverUri, option)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestExchangeRefresh(id) {
    const serverUri = "/exchange_refresh?id="+id;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestExchangeCode(id) {
    const serverUri = "/exchange_key?id="+id;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });

}

function exchangePostBodyGenerated(keys, createExchange) {
    const apiIdList = keys.map((name) => {
        return createExchange[name].apiId;
    });
    const server = createExchange[keys[0]].server

    return {
        server,
        "apiIdList": apiIdList
    };
}

function createExchangeDiv(exchangeParty) {
    const {id, exchangeKey, characters} = exchangeParty;
    const containerExchange = document.createElement('div');
    containerExchange.classList.add("container-exchange");
    containerExchange.setAttribute("id", id);

    // 교환 파티 키 생성
    // 클릭 시 검색에 사용할 수 있는 파티 키가 생성
    // 파티 키 유효 기간은 다음 주 까지
    const exchangeKeySpan = document.createElement('span');
    exchangeKeySpan.classList.add("exchange-key");
    exchangeKeySpan.innerText = exchangeKey ? exchangeKey : "파티 번호 생성";
    containerExchange.appendChild(exchangeKeySpan);

    const exchangeCharacters = document.createElement('div');
    exchangeCharacters.classList.add("exchange-characters");

    for (let c of characters) {
        const characterDiv = createCharactersDiv(c);
        characterDiv.appendChild(createItemDiv(c));
        exchangeCharacters.appendChild(characterDiv);
    }
    containerExchange.appendChild(exchangeCharacters);

    return containerExchange;
}

async function constructorExchangePartyList(exchangeList) {
    const exchangePartyList = [];

    for (const exchange of exchangeList) {
        const e = new ExchangeParty();
        await e.request(exchange);
        exchangePartyList.push(e);
    }

    return exchangePartyList;
}