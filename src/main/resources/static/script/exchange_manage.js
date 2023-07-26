let characterObj = {};
let adventureName = null;

window.onload = async function () {
    eventBinding();
    await setUp();
}

function setUp() {
    return requestExchangeSessionPage(1)
        .then(async exchangeResList => {
            for (const res of exchangeResList) {
                await createExchangeMainDiv(res);
            }
        });
}

function eventBinding() {
    document.querySelector("#container-search .button").addEventListener("click", characterSearch);
    document.getElementsByClassName("modal-btn")[1].addEventListener("click", exchangeCreation);
}

function characterSearch() {
    const parentElement = document.getElementById("container-search");
    const name = parentElement.querySelector(".input").value;
    const type = parentElement.querySelector(".search-select").value;

    requestCharacterSearch(name, type)
        .then((data) => createSearchCharacterList(data));
}

// 검색된 캐릭터 클릭 시 상세 정보 요청
function characterStatus(event) {
    const character = event.target.parentNode;
    const apiId = character.getAttribute('apiId');
    const server = character.getAttribute('server');

    requestCharacterStatus(apiId, server)
        .then((data) => createExchangeCharacterDiv(data))
        .then(() => initSearchedDiv())
        .catch((err) => catchHandler(err));
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

// 교환 파티 캐릭터
async function createExchangeCharacterDiv(res) {
    const character = res.character;
    if (characterObj[character.name]) {
        reject(new Error("이미 등록된 캐릭터입니다."));
        return;
    }

    if (Object.keys(characterObj).length >= 4) {
        reject(new Error("캐릭터는 최대 4개까지 등록할 수 있습니다."));
        return;
    }

    // 모험단명이 설정되어 있으며 설정된 모험단명과 요청된 모험단명이 다를 경우 실행
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
    characterDiv.appendChild(createDungeonIcon(res.dungeon));

    exchangeCharacters.appendChild(characterDiv);

    characterObj[character.name] = res;
    adventureName = character.adventureName;

    return character;
}

// 휴지통 아이콘 클릭 시 이벤트
// characterObject에 해당 캐릭터 삭제 및 교환 파티 등록 캐릭터 목록에서도 삭제
function characterTrashIconClickEvent() {
    const parent = this.parentElement; // parent 요소
    const name = parent.querySelector(".character-name").innerText;
    delete characterObj[name];
    if (Object.keys(characterObj).length === 0)
        adventureName = null;

    parent.remove();
}

// 교환파티 생성
function exchangeCreation() {
    requestExchangeCreation(characterObj, adventureName)
        .then(id => {
            return new ExchangeParty(id, characterObj);
        })
        .then(exchangeRes => createExchangeMainDiv(exchangeRes))
        .then(() => {
            modalClear();
            characterObj = {};
            adventureName = null;
        })
        .catch((err) => catchHandler(err));
}

// 메인 화면에 생성할 교환 파티 div
async function createExchangeMainDiv(exchangeRes) {
    const containerParty = document.getElementsByClassName("container-party")[0];
    const containerExchange = createExchangeDiv(exchangeRes);

    const exchangeKeySpan = (containerExchange).querySelector(".exchange-key");
    exchangeKeySpan.addEventListener("click", exchangeCodeClickEvent);

    const trashIcon = createTrashIcon();
    const refreshIcon = createRefreshIcon();
    refreshIcon.classList.add("exchange-refresh");
    trashIcon.addEventListener('click', trashIconClickEvent);
    refreshIcon.addEventListener("click", refreshIconClickEvent);
    containerExchange.appendChild(trashIcon);
    containerExchange.appendChild(refreshIcon);

    containerParty.insertBefore(containerExchange, containerParty.firstChild);

    return exchangeRes;
}

function trashIconClickEvent() {
    const parentElement = this.parentElement;
    const id = parentElement.getAttribute("id");

    if (confirm("해당 교환파티를 삭제 하시겠습니까?")) {
        requestExchangeDelete(id)
            .then(() => {
                parentElement.remove();
                alert("교환파티가 삭제되었습니다.");
            });
    }
}

function refreshIconClickEvent() {
    const parentElement = this.parentElement; // parent 요소
    const id = parentElement.getAttribute("id");

    let start = new Date(); // 시작
    requestExchangeRefresh(id)
        .then((data) => {
            let end = new Date(); // 종료
            console.log(end - start); // 경과시간(밀리초)
        });
}

function exchangeCodeClickEvent() {
    const parentElement = this.parentElement;
    const id = parentElement.getAttribute("id");
    if (this.innerText !== "파티 번호 생성")
        return

    requestExchangeCode(id)
        .then(data => {
            parentElement.querySelector(".exchange-key").innerText = data;
        });
}

function modalClear() {
    const exchangeModal = document.getElementById('exchangeModal');
    const modal = bootstrap.Modal.getInstance(exchangeModal);     // 부트스트랩 모달
    modal.hide();
    exchangeModal.querySelector(".search-select").selectedIndex = 0;
    exchangeModal.querySelector(".input").value = null;
    exchangeModal.querySelector("#searched").innerHTML = "";
    exchangeModal.querySelector(".exchange-characters").innerHTML = "";
}

async function initSearchedDiv() {
    const searched = document.getElementById("searched");
    searched.innerHTML = "";
}

function testBtnClick() {
    console.log(characterObj);
}

