import * as fetchHandler from 'fetchHandler';

window.onload = function () {
    eventBinding();
    PAC_setting();
    console.log("오케이");
}

function characterInsert() {
    const serverUri = "/character_insert";
    const character_name = document.getElementById("characterName").value;
    const character_level = document.getElementById("characterLevel").value;
    console.log(character_name + " / " + character_level);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ character_name, character_level })
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
    const dungeon_name = document.getElementById("dungeonName").value;
    const dungeon_code = document.getElementById("dungeonCode").value;
    const dungeon_description = document.getElementById("dungeonDescription").value;
    const dungeon_level_limit = document.getElementById("dungeonLevelLimit").value;
    console.log(dungeon_name + " / " + dungeon_code+ " / " + dungeon_description+ " / " + dungeon_level_limit);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ dungeon_name, dungeon_code, dungeon_description, dungeon_level_limit })
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
    const group_name = document.getElementById("groupName").value;
    console.log(group_name);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({group_name})
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
    const party_name = document.getElementById("partyName").value;
    const party_group_id = document.getElementById("partyGroup").value;
    console.log(party_name);
    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({party_name, party_group_id})
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

function PAC_setting() {
    const pac_tag_list = document.querySelectorAll(".pac-party-list");
    console.log(pac_tag_list);
    console.log(pac_tag_list.length);
    pac_tag_list.forEach(pac_tag => {
        PACCall(pac_tag);
    })
}

function PACCall(pac_tag) {
    const serverUri = "/pac_list?partyId="+pac_tag.getAttribute("value");

    fetch(serverUri)
        .then((res) => fetchHandler.toJsonPromise(res))
        .then((res) => fetchHandler.httpStatusHandler(res) )
        .then((res) => {
            console.log(res);
            res.forEach((pac_data_list, i) => {
                if (pac_data_list?.length) {
                    partyTable(pac_tag, pac_data_list, i+1);
                }
            });
        })
        .catch((err) => {
            console.log(err);
        });
}

function partyTable(pac_tag, pac_data_list, partyNumber) {
    const tableHeadTH = document.createElement("th");
    tableHeadTH.innerText = partyNumber + "번 파티";
    const tableHeadTR = document.createElement("tr");
    tableHeadTR.appendChild(tableHeadTH);
    const tableHead = document.createElement("thead");
    tableHead.appendChild(tableHeadTR);

    let tableBodyTR;
    const tableBody = document.createElement("tbody");

    pac_data_list.forEach(pac_data => {
        tableBodyTR = document.createElement("tr");

        const tableBodyTDName = document.createElement("td");
        const tableBodyTDLevel = document.createElement("td");
        const tableBodyTDId = document.createElement("td");
        tableBodyTDName.innerText = pac_data.character.character_name;
        tableBodyTR.appendChild(tableBodyTDName);

        tableBodyTDLevel.innerText = pac_data.character.character_level;
        tableBodyTR.appendChild(tableBodyTDLevel);

        tableBodyTDId.innerText = pac_data.character.id;
        tableBodyTR.appendChild(tableBodyTDId);

        tableBody.appendChild(tableBodyTR);
    });

    const table = document.createElement("table");
    table.appendChild(tableHead);
    table.appendChild(tableBody);
    pac_tag.appendChild(table);
}

function CADInsert() {
    const serverUri = "/cad_insert";
    const cad_dungeon_id = document.getElementById("cadDungeon").value;
    const cad_character_id = document.getElementById("cadCharacter").value;
    const cad_clear = document.getElementById("cadClear").checked;

    const options = {
        method : 'POST',
        cache : 'no-cache',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({cad_dungeon_id, cad_character_id, cad_clear})
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
    document.getElementById("cad").addEventListener("click", CADInsert);
}