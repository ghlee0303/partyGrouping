// 검색 요청
// 이름, 서버or모험단, 세션여부
function requestCharacterSearch(name, type) {
    const serverUri = `/character_search?name=${name}&type=${type}`;
    console.log(serverUri);

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

// 상세정보 요청
function requestCharacterStatus(apiId, server, session=false) {
    const serverUri = `/character_status?apiId=${apiId}&server=${server}&session=${session}`;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

function requestCharacterSessionPage(page) {
    const serverUri = "/character_session?page="+page;

    return fetchData(serverUri, null)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

// 상세정보 리스트 요청
function requestCharacterStatusList(list, apiUse) {
    const serverUri = `/character_status_list`;
    const postBody = {
        apiUse,
        "characterRequestList": list
    }
    console.log(list);

    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postBody)
    };

    return fetchData(serverUri, option)
        .then(data => { return data; })
        .catch(error => {
            throw error;
        });
}

// 캐릭터 기본 정보창 생성
function createCharactersDiv(character) {
    const charactersDiv = document.createElement('div');
    charactersDiv.classList.add("characters");
    charactersDiv.setAttribute("apiId", character.apiId);       // 캐릭터 apiId
    charactersDiv.setAttribute("server", character.server);     // 캐릭터 서버

    const imageSrc = `https://img-api.neople.co.kr/df/servers/${character.server}/characters/${character.apiId}`;

    const img = document.createElement("img");      // 캐릭터 이미지
    img.src = imageSrc;
    img.alt = "image";
    img.classList.add("character-image");
    charactersDiv.appendChild(img);

    const nameSpan = document.createElement("span");    // 캐릭터명
    nameSpan.innerText = character.name;
    nameSpan.classList.add("character-name");
    charactersDiv.appendChild(nameSpan);

    const jobSpan = document.createElement("span");    // 캐릭터 직업
    jobSpan.innerText = character.jobGrowName;
    jobSpan.classList.add("character-job");
    charactersDiv.appendChild(jobSpan);

    const adventureNameSpan = document.createElement("span");    // 모험단
    adventureNameSpan.innerText = character.adventureName;
    adventureNameSpan.classList.add("character-adventure");
    charactersDiv.appendChild(adventureNameSpan);

    const fameSpan = document.createElement("span");    // 명성
    fameSpan.innerText = character.fame;
    fameSpan.classList.add("character-fame");
    charactersDiv.appendChild(fameSpan);

    return charactersDiv;
}

// 휴지통 아이콘 생성
function createTrashIcon() {
    const trashIcon = document.createElement("i");   // 휴지통 생성
    trashIcon.classList.add("bl");
    trashIcon.classList.add("bi-trash");
    trashIcon.classList.add("character-trash");

    return trashIcon;
}

// 리셋 아이콘
function createRefreshIcon() {
    const refreshIcon = document.createElement("i");   // 휴지통 생성
    refreshIcon.classList.add("bl");
    refreshIcon.classList.add("bi-arrow-clockwise");

    return refreshIcon;
}