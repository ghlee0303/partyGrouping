let characterObject = {};
let characterCookie = [];       // json List
const COOKIE_KEY = "character";

window.onload = function () {
    characterCookie = getJsonCookie(COOKIE_KEY);
    eventBinding();

    characterStatusList(0)
        .catch((err) => {
            console.log(err);
        });
}

// apiUse = 던파 api를 사용할 것인지 아닌지 0, 1
async function characterStatusList(apiUse) {
    requestCharacterStatusList(characterCookie, apiUse)
        .then(async dataList => {
            for (const data of dataList) {
                await createCharacterStatus(data);  // 순서가 보장되어야 함으로 await 사용
            }
        })
        .catch(err => {
            return err;
        });
}

function eventBinding() {
    document.querySelector("#container-search .button").addEventListener("click", characterSearch);
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
        const charactersDiv = createCharactersDiv(character);

        // 검색 된 캐릭터 클릭 시 상세정보 요청 및 하단의 교환파티 멤버 추가
        charactersDiv.addEventListener("click", characterStatus);
        container.appendChild(charactersDiv);
    });
    return characterList;
}

// 검색된 캐릭터 클릭 시 상세 정보 요청
function characterStatus(event) {
    const parent = event.target.parentNode;
    const apiId = parent.getAttribute('apiId');
    const server = parent.getAttribute('server');

    requestCharacterStatus(apiId, server)
        .then((data) => createCharacterStatus(data))
        .then((data) => {
            pushCookie(characterCookieGenerate(apiId, server));
        })
        .catch((err) => {
            console.log(err);
        })
}

// 캐릭터 상세정보 요소 생성
// 쿠키 저장
async function createCharacterStatus(character) {
    if (characterObject[character.name]) {
        return new Error("이미 등록된 캐릭터입니다.")
    }
    const container = document.getElementsByClassName("container-character")[1];

    const charactersDiv = createCharactersDiv(character);

    const trashIcon = createTrashIcon();
    const resetIcon = createRefreshIcon();
    resetIcon.classList.add("character-reset");
    trashIcon.addEventListener('click', trashIconClickEvent);
    resetIcon.addEventListener('click', refreshIconClickEvent);

    charactersDiv.appendChild(trashIcon);
    charactersDiv.appendChild(resetIcon);

    charactersDiv.appendChild(createItemDiv(character));

    container.appendChild(charactersDiv);
    characterObject[character.name] = character;

    return character;
}

// 휴지통 아이콘 클릭 시 이벤트
// characterObject에 해당 캐릭터 삭제 및 교환 파티 등록 캐릭터 목록에서도 삭제
function trashIconClickEvent() {
    const characterDiv = this.parentElement; // parent 요소
    const name = characterDiv.querySelector(".character-name").innerText;
    const apiId = characterDiv.getAttribute("apiId");
    const server = characterDiv.getAttribute("server");

    delete characterObject[name];
    popCookie(characterCookieGenerate(apiId, server));

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
    div.querySelector(".character-name").innerText = data.name;
    div.querySelector(".character-job").innerText = data.jobGrowName;
    div.querySelector(".character-fame").innerText = data.fame;
    div.removeChild(div.querySelector(".item"));
    div.appendChild(createItemDiv(data));
}

function characterCookieGenerate(apiId, server) {
    return {
        "apiId": apiId,
        "server": server
    };
}

function pushCookie(push) {
    characterCookie.push(push);
    createCookie(COOKIE_KEY, JSON.stringify(characterCookie), 30);
}

function popCookie(pop) {
    for (let i=0; i<characterCookie.length; i++) {
        if (characterCookie[i].server === pop.server && characterCookie[i].apiId === pop.apiId) {
            characterCookie.splice(i, 1);
            break;
        }
    }
    createCookie(COOKIE_KEY, JSON.stringify(characterCookie), 30);
}

function test11() {
    console.log(characterObject);
    console.log(characterCookie);
}