import * as fetchHandler from 'fetchHandler';

window.onload = function () {
    eventBinding();
    console.log("오케이");
}

function characterInsert() {
    const serverUri = "/character_insert";
    const name = document.getElementById("characterName").value;
    const level = document.getElementById("characterLevel").value;
    console.log(name + " / " + level);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ name, level })
    }

    fetch(serverUri, options)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log("ok");
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}

function dungeonInsert() {
    const serverUri = "/dungeon_insert";
    const name = document.getElementById("dungeonName").value;
    const code = document.getElementById("dungeonCode").value;
    const description = document.getElementById("dungeonDescription").value;
    const levelLimit = document.getElementById("dungeonLevelLimit").value;
    console.log(name + " / " + code+ " / " + description+ " / " + levelLimit);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ name, code, description, levelLimit })
    }

    fetch(serverUri, options)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log("ok");
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}

function groupInsert() {
    const serverUri = "/group_insert";
    const name = document.getElementById("groupName").value;
    console.log(name);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({name})
    }

    fetch(serverUri, options)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log("ok");
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}

function partyInsert() {
    const serverUri = "/party_insert";
    const name = document.getElementById("partyName").value;
    const party_group_id = document.getElementById("partyGroup").value;
    console.log(name);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({name, party_group_id})
    }

    fetch(serverUri, options)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log("ok");
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}

function PACInsert() {
    const serverUri = "/pac_insert";
    const pac_party_id = document.getElementById("pacParty").value;
    const pac_character_id = document.getElementById("pacCharacter").value;
    const pac_party_number = document.getElementById("pacNumber").value;

    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({pac_party_id, pac_character_id, pac_party_number})
    }

    fetch(serverUri, options)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log("ok");
        })
        .catch((err) => {
            alert(err.res.data.message);
        });
}
function eventBinding() {
    document.getElementById("character").addEventListener("click", characterInsert);
    document.getElementById("dungeon").addEventListener("click", dungeonInsert);
    document.getElementById("group").addEventListener("click", groupInsert);
    document.getElementById("party").addEventListener("click", partyInsert);
    document.getElementById("pac").addEventListener("click", PACInsert);
}