var select = document.getElementById('time'),
    all_blocs = document.querySelector('.timeBarbers'),
    blocs = all_blocs.getElementsByTagName('div'),
    radio = document.getElementsByName('barber'),
    cost = document.getElementById('costRec');

function init() {
    select.options[0].selected = true;
    blocs[0].style.display = 'block';
}

init();

select.addEventListener('change', function () {
    var ind = select.selectedIndex;
    for (var i = 0; i < blocs.length; i++) {
        var ii = i - 1;
        if (i == ind) {
            blocs[i].style.display = 'block';
        } else {
            blocs[i].style.display = 'none';
        }
    }
})

function changeCost(newCost) {
    for (var i = 0; i < radio.length; i++) {
        if (radio[i].checked) {
            cost.textContent = newCost;
        }
    }
}


