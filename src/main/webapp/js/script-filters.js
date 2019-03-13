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
    $('#' + id).remove();
    if (is_filterSelected() === "firstselected") {
        filterapply("show");
    } else if (is_filterSelected() === "noneselected") {
        filterapply("hide");
    }
}

function filterapply(action) {
    if (action === "show") {
        $('.filter-title-container span').html("Filters Selected");
        $('.apply-filter').css("display", "block");
    }
    if (action === "hide") {
        $('.filter-title-container span').html("Filters");
        $('.apply-filter').css("display", "none");
    }
}

function is_filterSelected() {
    if ($('.filter-title .filter-selected').length === 1) {
        return "firstselected";
    } else if ($('.filter-title .filter-selected').length === 0) {
        return "noneselected";
    } else {
        return "moreselected";
    }
}