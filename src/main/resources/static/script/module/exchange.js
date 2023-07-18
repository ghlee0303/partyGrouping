class ExchangeParty {
    constructor(id, c) {
        this.id = id;
        this.exchangeKey = null;
        this.characterList = [];
        for (let name in c) {
            this.characterList.push(c[name]);
        }
    }
}

function requestExchangeSearch(search, type) {
    const serverUri = `/exchange_search?search=${search}&type=${type}`;

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

function requestExchangeCreation(characterObj, adventureName) {
    const serverUri = "/exchange_manage";
    const keys = Object.keys(characterObj);
    const exchangeReq = exchangePostBodyGenerated(keys, characterObj, adventureName);

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

function exchangePostBodyGenerated(keys, characterObj, adventureName) {
    const apiIdList = keys.map((name) => {
        return characterObj[name].character.apiId;
    });
    const server = characterObj[keys[0]].character.server

    return {
        server,
        "adventureName": adventureName,
        "apiIdList": apiIdList
    };
}

function createExchangeDiv(exchangeRes) {
    const {id, exchangeKey, characterList} = exchangeRes;
    console.log(exchangeRes);
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

    for (let characterRes of characterList) {
        const characterDiv = createCharactersDiv(characterRes.character);
        characterDiv.appendChild(createItemDiv(characterRes.character));
        characterDiv.appendChild(createDungeonIcon(characterRes.dungeon));
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