function fetchData(url, option) {
    return fetch(url, option)
        .then(response => {
            if (response.ok) {
                const contentType = response.headers.get("Content-Type");

                // 만약 가져온 데이터가 JSON 형식이라면
                if (contentType && contentType.includes("application/json")) {
                    // JSON.parse() 메서드를 사용하여 데이터를 파싱한다.
                    return response.json();
                } else {
                    // JSON 형식이 아니라면 일반 텍스트나 다른 형식으로 파싱한다.
                    return response.text();
                }
            } else {
                return response.text().then(errorMessage => {
                    throw new Error(errorMessage);
                });
            }
        });
}

function fetchOK() {
    console.log("패치 ok");
}