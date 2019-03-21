function brandchecked(brand, event, by) {
    var brandnames_labels;
    if (by === 0)
        brandnames_labels = $('.full-brand label');
    else
        brandnames_labels = $('.short-brand label');
    if (event.checked) {
        console.log("checked :" + brand);
        for (let i = 0; i < brandnames_labels.length; i++) {
            if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                var input = brandnames_labels.eq(i).children().eq(0);
                input.prop("checked", true);
            }
        }
        $("<div onclick='filter_removeself(this);' class='filter-selected' id='" + brand + "' data-filter='brand:" + brand + "'>" + brand + "</div>").insertBefore($('.clearfloat'));
    } else {
        console.log("unchecked :" + brand);
        for (let i = 0; i < brandnames_labels.length; i++) {
            if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                var input = brandnames_labels.eq(i).children().eq(0);
                input.prop("checked", false);
            }
        }
        $('#' + brand).remove();
    }
    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
}

function oschecked(os, event) {
    if (event.checked) {
        $("<div onclick='filter_removeself(this);' class='filter-selected' id='" + os + "' data-filter='os:" + os + "'>" + os + "</div>").insertBefore($('.clearfloat'));
    } else {
        $('#' + os).remove();
    }

    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
}

function filter_removeself(event) {
    var id = event.getAttribute("id");
    var filterdata = event.getAttribute("data-filter");
    var datasplit = filterdata.split(":");
    var filtertype = datasplit[0];
    var filterdata = datasplit[1];
    if (filtertype === "brand") {
        var brandnames_labels = $('.full-brand label');
        var brand = filterdata;
        for (let i = 0; i < brandnames_labels.length; i++) {
            if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                var input = brandnames_labels.eq(i).children().eq(0);
                input.prop("checked", false);
            }
        }
        brandnames_labels = $('.short-brand label');
        for (let i = 0; i < brandnames_labels.length; i++) {
            if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                var input = brandnames_labels.eq(i).children().eq(0);
                input.prop("checked", false);
            }
        }
    }
    if (filtertype === "os") {
        var os_labels = $(".os-ul label");
        var os = filterdata;
        for (let i = 0; i < os_labels.length; i++) {
            if (os_labels.eq(i).attr("data-os") === os) {
                var input = os_labels.eq(i).children().eq(0);
                input.prop("checked", false);
            }
        }
    }
    var checkFilter = ["external", "internal", "ram", "selfiecam", "maincam", "battery"];
    for (let i = 0; i < checkFilter.length; i++) {
        if (filtertype === checkFilter[i]) {
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(0).children().eq(0).val("");
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(0).children().eq(0).prop("disabled",false);
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(2).children().eq(0).val("");
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(2).children().eq(0).prop("disabled",false);
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(3).css("display", "none");
            $('.filter-'+checkFilter[i]).children().eq(1).children().eq(4).css("display", "none");
        }
    }

    $('#' + id).remove();
    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
}

function is_filterSelected() {
    if ($('.filter-title .filter-selected').length === 1) {
        return "firstselected";
    } else if ($('.filter-title .filter-selected').length === 0) {
        window.location.hash = "";
        return "noneselected";
    } else {
        return "moreselected";
    }
}
function focusInput(event, action) {
    if (action === 'in') {
        event.children[3].style.display = "block";
    } else {
        if (event.children[0].children[0].value.length > 0 || event.children[2].children[0].value.length > 0) {
            event.children[3].style.display = "block";
        } else {
            event.children[3].style.display = "none";
        }
    }
}
function addfilterRange(event) {
    var filterType = event.getAttribute("filter-type");
    var from = event.children[1].children[0].children[0].value;
    event.children[1].children[0].children[0].disabled = true;
    var to = event.children[1].children[2].children[0].value;
    event.children[1].children[2].children[0].disabled = true;
    if (from.length === 0)
        from = 0.0;
    if (to.length === 0)
        to = "inf";
    $("<div onclick='filter_removeself(this);' class='filter-selected' id='" + filterType + "' data-filter='" + filterType + ":" + from + "to" + to + "'>" + filterType + "</div>").insertBefore($('.clearfloat'));
    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
    event.children[1].children[3].style.display = "none";
    event.children[1].children[4].style.display = "block";
}
function removefilterRange(event) {
    event.children[1].children[0].children[0].disabled = false;
    event.children[1].children[2].children[0].disabled = false;
    event.children[1].children[0].children[0].value = "";
    event.children[1].children[2].children[0].value = "";
    
    event.children[1].children[3].style.display = "none";
    event.children[1].children[4].style.display = "none";
    $('#' + event.getAttribute("filter-type")).remove();
    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
}