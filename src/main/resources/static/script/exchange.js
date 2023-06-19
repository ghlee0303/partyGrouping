let characterObject = {};
let exchangeCookie = [];
let adventureName = null;
const COOKIE_KEY = "exchange";

class ExchangeParty {
    constructor() {
        this.persistentKey = null;
        this.exchangeKey = null;
        this.characters = [];
    }

    // 교환파티 등록 후 사용
    creation(p, e, c) {
        this.persistentKey = p;
        this.exchangeKey = e;
        for (let objKey in c) {
            this.characters.push(c[objKey]);
        }
    }

    // 교환파티 조회 후 사용
    request(data) {
        this.persistentKey = data.persistentKey;
        this.exchangeKey = data.exchangeKey;
        this.characters.push(data.character1);
        this.characters.push(data.character2);
        this.characters.push(data.character3);
        this.characters.push(data.character4);
    }
}

window.onload = function () {
    exchangeCookie = getListCookie(COOKIE_KEY);
    requestExchangePartyList(exchangeCookie);
    eventBinding();
    console.log("오케이");
}

function eventBinding() {
    document.querySelector("#container-search .button").addEventListener("click", characterSearch);
    document.getElementsByClassName("modal-btn")[1].addEventListener("click", requestExchangePartyCreation);
    document.getElementById("testBtn").addEventListener("click", test11);
}

function characterSearch() {
    const name = document.getElementById("search-input").value;
    const type = document.getElementById("search-select").value;

    requestCharacterSearch(name, type)
        .then((data) => createSearchCharacterList(data))
        .catch((err) => {
            console.log(err);
        });
}

// 캐릭터 목록 생성
async function createSearchCharacterList(characterList) {
    const container = document.getElementById("searched");
    container.innerHTML = "";
    characterList.forEach(character => {
        const characterDiv = createCharactersDiv(character);

        // 검색 된 캐릭터 클릭 시 상세정보 요청 및 하단의 교환파티 멤버 추가
        characterDiv.addEventListener("click", characterStatus);
        container.appendChild(characterDiv);
    });

    return characterList;
}

// 검색된 캐릭터 클릭 시 상세 정보 요청
function characterStatus(event) {
    const character = event.target.parentNode;
    const apiId = character.getAttribute('apiId');
    const server = character.getAttribute('server');

    requestCharacterStatus(apiId, server)
        .then((data) => createExchangePartyCharacter(data))
        .catch((err) => {
            console.log(err);
        })
}

// 교환 파티 캐릭터
async function createExchangePartyCharacter(character) {
    if (characterObject[character.name]) {
        reject(new Error("이미 등록된 캐릭터입니다."));
        return;
    }

    if (Object.keys(characterObject).length >= 4) {
        reject(new Error("캐릭터는 최대 4개까지 등록할 수 있습니다."));
        return;
    }

    // 모험단명이 존재하며 설정된 모험단명과 요청된 모험단명이 다를 경우 실행
    if (adventureName && adventureName !== character.adventureName) {
        reject(new Error("모험단이 다릅니다."));
        return;
    }
    const container = document.getElementsByClassName("container-exchange")[0];

    const exchangeCharacters = container.querySelector(".exchange-characters");
    const characterDiv = createCharactersDiv(character);

    const trashIcon = createTrashIcon();
    trashIcon.addEventListener('click', characterTrashIconClickEvent);
    characterDiv.appendChild(trashIcon);
    characterDiv.appendChild(createItemDiv(character));

    exchangeCharacters.appendChild(characterDiv);

    characterObject[character.name] = character;
    adventureName = character.adventureName;

    return character;
}

// 휴지통 아이콘 클릭 시 이벤트
// characterObject에 해당 캐릭터 삭제 및 교환 파티 등록 캐릭터 목록에서도 삭제
function characterTrashIconClickEvent() {
    const parent = this.parentElement; // parent 요소
    const name = parent.querySelector(".character-name").innerText;
    delete characterObject[name];
    if (Object.keys(characterObject).length === 0)
        adventureName = null;

    parent.remove();
}

function requestExchangePartyList() {
    const serverUri = "/exchange_list";
    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(exchangeCookie)
    };

    fetchData(serverUri, option)
        .then(data => constructorExchangePartyList(data))
        .then(async exchangePartyList => {
            for (const exchangeParty of exchangePartyList) {
                await createExchangePartyMain(exchangeParty);
            }
        })
        .catch(error => {
            // 에러 처리 코드 작성
            console.error(error);
        });
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

// 교환파티 생성 요청
function requestExchangePartyCreation() {
    const serverUri = "/exchange";
    const keys = Object.keys(characterObject);
    const exchangeReq = exchangePostBodyGenerated();

    if (keys.length !== 4) {
        console.log("멤버가 4명이 되어야합니다.");
        return;
    }

    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(exchangeReq)
    };

    fetchData(serverUri, option)
        .then((persistentKey) => {
            const e = new ExchangeParty();
            e.creation(persistentKey, null, characterObject);
            return e;
        })
        .then(exchangeParty => createExchangePartyMain(exchangeParty))
        .then(exchangeParty => {
            const promises = [
                pushCookie(exchangeParty.persistentKey),
                modalClear()
            ];

            return Promise.all(promises);
        })
        .then(() => {
            characterObject = {};
            adventureName = null;
        })
        .catch(error => {
            // 에러 처리 코드 작성
            console.error(error);
        });
}

// 메인 화면에 생성할 교환 파티 div
async function createExchangePartyMain(exchangeParty) {
    const {persistentKey, exchangeKey, characters} = exchangeParty;
    const containerParty = document.getElementsByClassName("container-party")[0];
    const containerExchange = document.createElement('div');
    containerExchange.classList.add("container-exchange");
    containerExchange.setAttribute("persistentKey", persistentKey);

    // 교환 파티 키 생성
    // 클릭 시 검색에 사용할 수 있는 파티 키가 생성
    // 파티 키 유효 기간은 다음 주 까지
    const exchangeKeySpan = document.createElement('span');
    exchangeKeySpan.classList.add("exchange-key");
    exchangeKeySpan.innerText = exchangeKey ? exchangeKey : "교환 키 생성";
    exchangeKeySpan.addEventListener("click", exchangeCodeClickEvent);
    containerExchange.appendChild(exchangeKeySpan);

    const exchangeCharacters = document.createElement('div');
    exchangeCharacters.classList.add("exchange-characters");

    for (let c of characters) {
        const characterDiv = createCharactersDiv(c);
        characterDiv.appendChild(createItemDiv(c));
        exchangeCharacters.appendChild(characterDiv);
    }
    containerExchange.appendChild(exchangeCharacters);

    const trashIcon = createTrashIcon();
    const resetIcon = createRefreshIcon();
    resetIcon.classList.add("exchange-reset");
    trashIcon.addEventListener('click', exchangeTrashIconClickEvent);
    containerExchange.appendChild(trashIcon);
    containerExchange.appendChild(resetIcon);

    containerParty.insertBefore(containerExchange, containerParty.firstChild);

    return exchangeParty;
}

function exchangeTrashIconClickEvent() {
    const parentElement = this.parentElement;

    if (confirm("해당 교환파티를 삭제 하시겠습니까?")) {
        requestExchangePartyDelete(parentElement)
    }
}

function requestExchangePartyDelete(parentElement) {
    const persistentKey = parentElement.getAttribute("persistentKey");
    const serverUri = "/exchange?persistentKey="+persistentKey;
    const option = {
        method: 'DELETE'
    };

    fetchData(serverUri, option)
        .then(() => {
            const promises = [
                parentElement.remove(),
                popCookie(persistentKey)
            ];

            return Promise.all(promises);
        })
        .then(() => {
            alert("교환파티가 삭제되었습니다.");
        })
        .catch(error => {
            // 에러 처리 코드 작성
            console.error(error);
        });
}

function exchangeCodeClickEvent() {
    const parentElement = this.parentElement;
    const persistentKey = parentElement.getAttribute("persistentKey");
    if (this.innerText !== "교환 키 생성")
        return

    const serverUri = "/exchange_key?persistentKey="+persistentKey;

    fetchData(serverUri, null)
        .then(data => {
            parentElement.querySelector(".exchange-key").innerText = data;
        })
        .catch(error => {
            // 에러 처리 코드 작성
            console.error(error);
        });
}

function modalClear() {
    const exchangeModal = document.getElementById('exchangeModal');
    const modal = bootstrap.Modal.getInstance(exchangeModal);     // 부트스트랩 모달
    modal.hide();
    exchangeModal.querySelector("#search-select").selectedIndex = 0;
    exchangeModal.querySelector("#search-input").value = null;
    exchangeModal.querySelector("#searched").innerHTML = "";
    exchangeModal.querySelector(".exchange-characters").innerHTML = "";

}

function exchangePostBodyGenerated() {
    const keys = Object.keys(characterObject);
    const apiIdList = keys.map((name) => {
        return characterObject[name].apiId;
    });
    const server = characterObject[keys[0]].server

    return {
        server,
        "apiIdList": apiIdList
    };
}

function pushCookie(persistentKey) {
    exchangeCookie.push(persistentKey);
    createCookie(COOKIE_KEY, exchangeCookie, 30);
}

function popCookie(persistentKey) {
    for (let i=0; i<exchangeCookie.length; i++) {
        if (exchangeCookie[i].persistentKey === persistentKey) {
            exchangeCookie.splice(i, 1);
            break;
        }
    }
    createCookie(COOKIE_KEY, exchangeCookie, 30);
}

function test11() {
    console.log(characterObject);
    console.log(adventureName);
    console.log(exchangeCookie);
}
