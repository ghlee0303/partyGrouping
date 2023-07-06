package com.party_grouping.exception;

public enum ErrorCode {

    // SYS / 시스템 관련
    UNKNOWN_ERROR(999, "UNE000", "에러"),
    SYSTEM_INSPECT(500, "SYS001", "던파 점검중입니다."),

    // WEB / 웹 관련
    WRONG_REQ_PARAM(400, "WEB001", "잘못 된 요청입니다."),

    // CHA / 캐릭터 관련
    CHARACTER_NOT_FOUND(404, "CHA404", "해당 캐릭터를 찾을 수 없습니다."),
    CHARACTER_LOCK(400, "CHA001", "캐릭터가 잠금 상태입니다."),
    CHARACTER_NONE_BUFF(400, "CHA002", "버프강화를 선택해주세요."),
    
    // ITE / 아이템 관련
    ITEM_NOT_FOUND(404, "ITE404", "해당 아이템을 찾을 수 없습니다."),
    NONE_AVATAR(404, "ITE001", "해당 캐릭터는 아바타를 입고있지 않습니다."),
    ITEM_NOT_ENOUGH(400, "ITE002", "장착중인 장비 갯수가 13개 미만입니다."),

    // PAR / 파티 관련
    PARTY_NOT_FOUND(404, "PAR404", "해당 파티를 찾을 수 없습니다."),
    PARTY_MEMBERS_NOT_ENOUGH(400, "PAR001", "파티 멤버는 4명이어야 합니다."),
    PARTY_MEMBERS_DUPLICATE(400, "PAR002", "파티 멤버가 중복되었습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
