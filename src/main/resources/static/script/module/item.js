
function createItemDiv(character) {
    const { item, buffLevel, buffMax, buffer } = character;

    if (buffer) {
        return document.createDocumentFragment();
    }

    const itemDiv = document.createElement('div'); // characters 엘리먼트 생성
    itemDiv.classList.add("item");

    const buffLevelSpan = document.createElement("span");    // buffLevelSpan 생성
    buffLevelSpan.innerText = buffText(buffLevel, buffMax);
    buffLevelSpan.classList.add("buffLevel");
    itemDiv.appendChild(buffLevelSpan);

    const weaponSpan = document.createElement("span");    // weaponSpan 생성
    weaponSpan.innerText = weaponText(item);
    weaponSpan.classList.add("weapon");
    itemDiv.appendChild(weaponSpan);

    const accessorySpan = document.createElement("span");    // accessorySpan 생성
    accessorySpan.innerText = accessoryText(item);
    accessorySpan.classList.add("accessory");
    itemDiv.appendChild(accessorySpan);

    const endItemSpan = document.createElement("span");    // endItemSpan 생성
    endItemSpan.innerText = endItemText(item);
    endItemSpan.classList.add("endItem");
    itemDiv.appendChild(endItemSpan);

    const skillBonusSpan = document.createElement("span");    // skillBonusSpan 생성
    skillBonusSpan.innerText = `어벨 합 : ${item.enchantSkillBonus}`;
    skillBonusSpan.classList.add("skillBonus");
    itemDiv.appendChild(skillBonusSpan);

    return itemDiv;
}

// 무기 강화/증폭 및 재련 텍스트화
function weaponText(item) {
    const { weaponAmp, weaponReinforce, weaponRefine } = item;
    let weaponText = `${weaponReinforce}${weaponAmp ? '증' : '강'}`;

    if (weaponRefine) {
        weaponText += `/${weaponRefine}재련`;
    }

    return weaponText;
}

function accessoryText(item) {
    const { wrist, amulet, ring } = item;
    return `${wrist}/${amulet}/${ring}`;
}

// 시브, 크리쳐, 오라, 칭호 종결템
function endItemText(item) {
    const { siv, creature, aurora, title } = item;
    let endItemText = "";

    if (siv) endItemText += "시";
    if (creature) endItemText += "/나";
    if (aurora) endItemText += "/오";
    if (title) endItemText += "/칭";

    if (endItemText.startsWith("/")) {
        endItemText = endItemText.slice(1);
    }

    return endItemText;
}

function buffText(buffLevel, buffMax) {
    if (buffMax) {
        return `${buffLevel}(MAX)`;
    }
    return buffLevel;
}

function itemOK() {
    console.log("아이템 ok");
}