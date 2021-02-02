'use strict';

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
            if ((direction === 'asc' && tableSortComparator(row1, row2)) ||
                (direction === 'desc' && tableSortComparator(row2, row1))) {
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

let tableSortComparator = (row1, row2) => {
    let num1 = Number.parseFloat(row1);
    let num2 = Number.parseFloat(row2);

    if (isFloatOrInteger(num1) && isFloatOrInteger(num2)) {
        return num1 > num2;
    }
    return row1 > row2;
}

function isFloatOrInteger(n) {
    return n === +n;
}
