import * as fetchHandler from 'fetchHandler';

window.onload = function () {
    eventBinding();
    console.log("오케이");
}

function characterSearch() {
    const character_name = document.getElementById("characterName").value;
    const serverUri = "/character_search?name="+character_name;
    console.log(character_name);

    fetch(serverUri)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log(res);
            console.log("ok");
            characterList(res);
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}

function characterList(characterList) {
    const listBody = document.getElementById("list_body");
    const ul = document.createElement('ul'); // ul 엘리먼트 생성

    characterList.forEach(character => { // 배열의 각 객체를 반복
        const li = document.createElement('li'); // li 엘리먼트 생성
        const name = document.createTextNode(character.name); // 이름 텍스트 노드 생성
        li.appendChild(name); // li 엘리먼트에 이름 텍스트 노드 추가
        ul.appendChild(li); // ul 엘리먼트에 li 엘리먼트 추가
    });

    listBody.appendChild(ul); // ul 엘리먼트를 list_body에 추가
}

function eventBinding() {
    document.getElementById("characterSearch").addEventListener("click", characterSearch);
}