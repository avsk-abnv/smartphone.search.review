var arr = [];
var currentFocus;
var preventInput = false;
function closeAllLists(elmnt) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
        if (elmnt !== x[i] && elmnt !== document.getElementById("myInput")) {
            x[i].parentNode.removeChild(x[i]);
        }
    }
}

function keydownFunc(e) {
    if (preventInput) {
        e.preventDefault();
        return false;
    } else {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x)
            x = x.getElementsByTagName("div");
        if (e.keyCode === 40) {
            currentFocus++;
            addActive(x);
        } else if (e.keyCode === 38) { //up
            currentFocus--;
            addActive(x);
        } else if (e.keyCode === 13) {
            e.preventDefault();
            if (currentFocus > -1) {
                if (x)
                    x[currentFocus].click();
            }
        }
    }
}

function addActive(x) {
    if (!x)
        return false;
    removeActive(x);
    if (currentFocus >= x.length)
        currentFocus = 0;
    if (currentFocus < 0)
        currentFocus = (x.length - 1);
    x[currentFocus].classList.add("autocomplete-active");
}

function removeActive(x) {
    for (var i = 0; i < x.length; i++) {
        x[i].classList.remove("autocomplete-active");
    }
}
function searchlist(e) {
    var a, b, i, val = document.getElementById("myInput").value;
    closeAllLists();
    if (!val) {
        return false;
    }
    if (val.length === 1 || val.length === 2) {
        var this_ = this;
        preventInput = true;
        a = document.createElement("DIV");
        a.setAttribute("id", this_.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        document.getElementById("myInput").parentNode.appendChild(a);
        b = document.createElement("DIV");
        b.innerHTML = "Loading...";
        a.appendChild(b);
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/SearchAjax",
            data: {request: "ping", value: val},
            success: function (data) {
                if ($.trim(data).length !== 0) {
                    arr = [];
                    var list = $.trim(data).split("\n");
                    for (let i = 0; i < list.length; i++) {
                        var brand = list[i].split("%")[0];
                        arr.push(list[i].split("%")[1]);
                    }
                    closeAllLists();
                    preventInput = false;
                    currentFocus = -1;
                    a = document.createElement("DIV");
                    a.setAttribute("id", this_.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    document.getElementById("myInput").parentNode.appendChild(a);
                    for (i = 0; i < arr.length; i++) {
                        if (arr[i].substr(0, val.length).toUpperCase() === val.toUpperCase()) {
                            b = document.createElement("DIV");
                            b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                            b.innerHTML += arr[i].substr(val.length);
                            b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                            b.addEventListener("click", function (e) {
                                document.getElementById("myInput").value = this.getElementsByTagName("input")[0].value;
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                } else {
                    closeAllLists();
                    preventInput = false;
                }
            }
        });
    } else {
        currentFocus = -1;
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        document.getElementById("myInput").parentNode.appendChild(a);
        for (i = 0; i < arr.length; i++) {
            if (arr[i].substr(0, val.length).toUpperCase() === val.toUpperCase()) {
                b = document.createElement("DIV");
                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                b.innerHTML += arr[i].substr(val.length);
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                b.addEventListener("click", function (e) {
                    document.getElementById("myInput").value = this.getElementsByTagName("input")[0].value;
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    }
}
function keypressFunc(e) {
    if (preventInput) {
        e.preventDefault();
        return false;
    }
}
function autocomplete(inp) {
    inp.addEventListener("input", searchlist);
    inp.addEventListener("keydown", keydownFunc);
    inp.addEventListener("keypress", keypressFunc);
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

autocomplete(document.getElementById("myInput"));

function getSearch_results(page) {
    var val = document.getElementById("myInput").value;
    if (val.length > 0) {
        $('.loader-mask').css("display", "block");
        $('.loader-mask .loading').html("Loading...");
        var url = window.location.href.toString();
        if (url.indexOf("Details") !== -1) {
            url = url.substring(0, url.indexOf("Details")) + "Homepage#" + "search=" + val + "_page:" + page;
            window.location.href = url;
        } else {
            window.location.hash = "search=" + val + "_page:" + page;
            $('.page-count').html(page + "");
            $('.filter-result').html("Search Results : ");
            $.ajax({
                type: 'POST',
                url: "/Smartphone.search.review/SearchResult",
                data: {request: "ping", value: val},
                success: function (data) {
                    console.log($.trim(data));
                    if (parseInt($.trim(data)) > 0) {
                        var pageNo = page;
                        var total_data = 16;
                        if (parseInt($.trim(data)) < total_data)
                            total_data = parseInt($.trim(data));
                        $('.filter-result').css("visibility", "visible");
                        $('.filter-result-count').css("visibility", "visible");
                        $('.filter-result-count').html($.trim(data));
                        $('.loader-mask .loading').html("Fetching Data... 0/" + total_data);
                        $('.img-container').css("display", "none");
                        $('.title').css("display", "none");
                        $('.likedislike').css("display", "none");
                        $('.price').css("display", "none");
                        fetchFromDatabase("ping-search", parseInt($.trim(data)), (pageNo - 1) * 16);
                    }
                }
            });
        }
    }
}