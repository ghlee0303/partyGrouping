let characterList = [];
let characterDupliCheck = [];       // 중복 요소 체크

window.onload = async function () {
    const page = 0;
    eventBinding();

    await requestCharacterSession(page)
        .then(async dataList => {
            characterList = dataList;
            for (const data of dataList) {
                await createCharacterStatus(data);  // 순서가 보장되어야 함으로 await 사용
            }
        })
        .catch((err) => {
            console.log(err);
        });
}

function eventBinding() {
    document.querySelector("#container-search .button").addEventListener("click", characterSearch);
    document.querySelector(".container-filter .filter").addEventListener("click", dungeonFilter);
    document.querySelector(".container-filter .init").addEventListener("click", initFilter);
    document.getElementById("testBtn").addEventListener("click", testBtnClick);
    document.getElementById("searched").addEventListener("click", characterSearchClickEvent);
}

function characterSearch() {
    const parentElement = document.getElementById("container-search");
    const name = parentElement.querySelector(".input").value;
    const type = parentElement.querySelector(".search-select").value;

    requestCharacterSearch(name, type)
        .then((data) => createSearchCharacterList(data))
        .catch((err) => catchHandler(err));
}

// 캐릭터 목록 생성
async function createSearchCharacterList(searchedList) {
    const container = document.getElementById("searched");
    container.innerHTML = "";
    searchedList.forEach(character => {
        // 검색 된 캐릭터 클릭 시 상세정보 요청 및 하단의 교환파티 멤버 추가
        container.appendChild(createCharactersDiv(character));
    });
    return searchedList;
}

function characterSearchClickEvent(event) {
    const clicked = event.target;
    const target = clicked.classList.contains('characters') ? clicked : clicked.parentNode;
    const apiId = target.getAttribute('apiId');
    const server = target.getAttribute('server');

    requestCharacterStatus(apiId, server,  true)
        .then((data) => createCharacterStatus(data))
        .then((data) => { characterList.push(data); })
        .then(() => initSearchedDiv())
        .catch((err) => catchHandler(err));
}

// 캐릭터 상세정보 요소 생성
async function createCharacterStatus(res) {
    const character = res.character;
    const isCharacterAlreadyRegistered = characterDupliCheck.some(obj => obj.apiId === character.apiId && obj.server === character.server);

    if (isCharacterAlreadyRegistered) {
        throw new Error("이미 등록된 캐릭터입니다.");
    }
    const container = document.getElementsByClassName("main-list")[0];

    const charactersDiv = createCharactersDiv(character);

    const trashIcon = createTrashIcon();
    const refreshIcon = createRefreshIcon();
    refreshIcon.classList.add("character-refresh");
    trashIcon.addEventListener('click', trashIconClickEvent);
    refreshIcon.addEventListener('click', refreshIconClickEvent);

    charactersDiv.appendChild(trashIcon);
    charactersDiv.appendChild(refreshIcon);
    charactersDiv.appendChild(createDungeonIcon(res.dungeon));

    charactersDiv.appendChild(createItemDiv(character));

    container.appendChild(charactersDiv);
    const obj = new characterObject(character.apiId, character.server);
    characterDupliCheck.push(obj);

    return res;
}

async function initSearchedDiv() {
    const searched = document.getElementById("searched");
    searched.innerHTML = "";
}

// 휴지통 아이콘 클릭 시 이벤트
// characterObject에 해당 캐릭터 삭제 및 교환 파티 등록 캐릭터 목록에서도 삭제
function trashIconClickEvent() {
    const characterDiv = this.parentElement; // parent 요소
    const apiId = characterDiv.getAttribute("apiId");
    const server = characterDiv.getAttribute("server");

    requestCharacterDelete(apiId, server)
        .then(data => {
            characterList = characterList.filter(obj => !(obj.apiId === apiId && obj.server === server));
        });

    characterDiv.remove();
}

function refreshIconClickEvent() {
    const characterDiv = this.parentElement; // parent 요소
    const apiId = characterDiv.getAttribute("apiId");
    const server = characterDiv.getAttribute("server");

    requestCharacterStatus(apiId, server)
        .then(data => characterRefresh(data, characterDiv))
        .catch((err) => {
           console.log(err);
        });
}

function characterRefresh(data, div) {
    const character = data.character;
    const dungeon = data.dungeon;
    div.querySelector(".character-name").innerText = character.name;
    div.querySelector(".character-job").innerText = character.jobGrowName;
    div.querySelector(".character-fame").innerText = character.fame;
    div.removeChild(div.querySelector(".item"));
    div.appendChild(createItemDiv(character));
    div.removeChild(div.querySelector(".dungeon-icon"));
    div.appendChild(createDungeonIcon(dungeon));
}


function dungeonFilter() {
    const ispinsCheck = document.getElementById('ispinsCheckbox');
    const dimenCheck = document.getElementById('dimenCheckbox');
    const bakalCheck = document.getElementById('bakalCheckbox');
    const clearCheck = document.getElementById('clearCheckbox');
    const checkList = Array.from([ispinsCheck, dimenCheck, bakalCheck]).filter(check => check.checked);

    const filterList = characterList.filter(character => {
        if (clearCheck.checked) {
            return checkList.length ? checkList.every(check => character.dungeon[check.value]) : false;
        } else {
            return checkList.length ? checkList.every(check => !character.dungeon[check.value]) : false;
        }
    });

    if (checkList.length > 0) {
        createFilterCharacterDiv(filterList);
    }
}

function initFilter() {
    createFilterCharacterDiv(characterList);
}

function createFilterCharacterDiv(filterList) {
    characterDupliCheck = [];
    document.getElementsByClassName("main-list")[0].innerHTML = "";

    for (let character of filterList) {
        createCharacterStatus(character);
    }
}

function testBtnClick() {
    console.log(characterList)
    console.log(characterDupliCheck);
}