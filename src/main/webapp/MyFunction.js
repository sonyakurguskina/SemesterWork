var nav = document.getElementById('nav');
nav.addEventListener('click', function(e) {
    var target = e.target;

    var targetParent = target.closest('.menu-item');

    if (targetParent) {
        var subm = targetParent.getElementsByClassName('submenu')[0];
        close();
        if (subm) {
            subm.style.display = 'block';
        }
    }
});

function close() {
    var s = document.getElementsByClassName('submenu');
    for (var i = 0; i < s.length; i++) {
        s[i].style.display = 'none';
    }
}