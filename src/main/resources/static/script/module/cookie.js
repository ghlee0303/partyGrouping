function getCookie(key) {
    const cookies = document.cookie.split(';');
    key += "=";
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith(key)) {
            return cookie.substring(key.length);
        }
    }
    return "[]";
}

function createCookie(key, cookieValue, expirationDays) {
    const date = new Date();
    date.setTime(date.getTime() + (expirationDays * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = key + "=" + cookieValue + "; " + expires + "; path=/";
}

function getListCookie(key) {
    const cookie = getCookie(key);
    return cookie === "[]" ? [] : cookie.split(",");
}

function getJsonCookie(key) {
    return JSON.parse(getCookie(key));
}