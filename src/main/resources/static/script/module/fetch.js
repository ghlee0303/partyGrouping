function fetchData(url, option) {
    return fetch(url, option)
        .then(async (response) => {
            if (response.ok) {
                const contentType = response.headers.get("Content-Type");

                if (contentType && contentType.includes("application/json")) {
                    return response.json();
                } else {
                    return response.text();
                }
            } else {
                if (response.status === 500) {
                    throw new Error("500");
                } else {
                    const text = await response.text();
                    throw new Error(text);
                }
            }
        })
        .catch((err) => {
            throw err;
        });
}

function catchHandler(err) {
    if (err.message === "500") {
        return;
    }
    alert(err.message);
}