/**
 * promise json 형태로 반환
 */
function toJsonPromise(res) {
    return new Promise(function(resolve, reject) {
        return resolve(res.json());
    });
}

/**
 * http status에 맞는 처리
 */
function httpStatusHandler(res) {
    return new Promise(function(resolve, reject) {
        switch (httpsStatusClass(res.httpStatus)) {
            case 2:
                resolve(res.data);
                break;
            case 4:
                reject(res);
                break;
        }
    });
}

function httpsStatusClass(httpsStatus) {
    return Math.floor(httpsStatus/100);
}

export {toJsonPromise, httpStatusHandler};