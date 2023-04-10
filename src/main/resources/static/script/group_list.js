import * as fetchHandler from 'fetchHandler';

window.onload = function () {
    eventBinding();
    PAC_setting();
    console.log("오케이");
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