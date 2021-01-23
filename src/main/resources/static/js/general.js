'use strict';

let formToJson = (formData) => {
    let object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    return JSON.stringify(object);
}

// let imgLoader = (img) => {
//     return (img !== undefined) ? img : 'resources/img/no-img.png';
// }

let setSelectedElement = (elem, check, color) => {
    elem.setAttribute("checked", check);
    elem.setAttribute("style", "background: " + color + ";");
}

let getSelector = () => {
    document.querySelectorAll('.rows')
        .forEach(item => item.addEventListener('click', function() {

            if (this.getAttribute("checked") === "true") {
                setSelectedElement(this, "false", "none");
                return;
            }

            let previous = document.querySelector('.rows[checked="true"]');
            if (previous !== null) {
                setSelectedElement(previous, "false", "none");
            }
            setSelectedElement(this, "true", "rgba(183, 201, 109,.3)");

        }));
}

// let token = document.querySelector('meta[name="_csrf"]').content;
let errorMsg = 'smth goes wrong on server side';

let send = async (where, jsonString, errorMsg) => {
    fetch(where, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type' : 'application/json',
            'X-CSRF-TOKEN': token
        },
        async: true,
        processData: false,
        body: jsonString
    }).then((response) => {
        return response;
    }).then((data) => {
        console.log(data);
        if (data.ok) {
            // location.reload();
        } else {
            alert(errorMsg);
        }
    }).catch((e) => {
        console.log(e);
        alert("cannot send");
    });
}

let tableSort = (item, table) => {
    let tbl, rows, switching, shouldSwitch,
        i, n, row1, row2, direction, switchCount = 0;

    n = item.getAttribute('columns');
    tbl = document.getElementById(table);
    switching = true;
    direction = 'asc';
    while (switching) {
        switching = false;
        rows = tbl.rows;
        for (i = 1; i < (rows.length - 1); ++i) {
            shouldSwitch = false;
            row1 = rows[i].getElementsByTagName('td')[n].innerHTML.toLowerCase();
            row2 = rows[i + 1].getElementsByTagName('td')[n].innerHTML.toLowerCase();
            if ((direction === 'asc' && row1 > row2) ||
                (direction === 'desc' && row1 < row2)) {
                    shouldSwitch = true;
                    break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i+1], rows[i]);
            switching = true;
            switchCount++;
        } else if (switchCount === 0 && direction === "asc") {
            direction = "desc";
            switching = true;
        }
    }
}

let addItemsToSelect = (tag, source) => {
    let elem = document.querySelector(tag);
    for (let i in source) {
        let option = document.createElement('option');
        option.setAttribute('id', source[i].id);
        option.innerHTML = source[i].name;
        elem.appendChild(option);
    }
}
let getSelectedOptionId = (tag) => {
    let elem = document.querySelector(tag);
    let arr = elem.getElementsByTagName("option");
    for (let i in arr)
        if (arr[i].value === elem.value)
            return arr[i].getAttribute("id");
}
let getSelectedRowId = (className) => {
    let item = document.querySelector('.rows[checked="true"]');
    if (item === null || item.className !== className) return;
    return item.getElementsByTagName("td")[0].innerHTML;
}

let getObjectById = (id, array) => {
    for (let i in array) {
        if (id === array[i].id) {
            return array[i];
        }
    }
}