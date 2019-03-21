<%-- 
    Document   : Filters
    Created on : 13 Mar, 2019, 7:18:55 PM
    Author     : Abhishek Abhinav
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="com.accessObjects.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>
<%@page import="com.weblogics.DBDevice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    <%@ include file="./js/filter-ajax.js" %>
</script>
<div class="filter-title filter-all">
    <div class="filter-title-container">
        <i class="fa fa-filter" aria-hidden="true"></i>
        <span style="margin-left:10px;">Filters</span>
        <div class="apply-filter" onclick="filterAjax(this.parentElement.parentElement);"><i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i> Apply</div>
    </div>
    <div style="clear: both" class="clearfloat"></div>

</div>
<div class="filter-brand filter-all">
    <span id="brand-text">Brands </span>
    <ul class="brand-ul">
        <%for (int i = 0; i < 4; i++) {
                String capitalized = Globals.BRANDS.get(i).substring(0, 1).toUpperCase() + Globals.BRANDS.get(i).substring(1);
        %>

        <li class="short-brand">
            <label class="container" data-brand="<%=capitalized%>"><%=capitalized%><input onclick="brandchecked('<%=capitalized%>', this, 0);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>

        <%}%>
        <li id="showmore" onclick="showbrands();">Show more ...</li>
    </ul>
</div>
<div id="all-brand-names">
    <div class="cross-button" onclick="hidebrands();" align="center">x</div>
    <div class="brand-wrapper row">
        <%  int listcount = 0;
            boolean opened = false;
            ArrayList<ArrayList<String>> allbrands = new ArrayList<>();
            for (int al = 0; al < 26; al++) {
                char ch = (char) (al + 65);
                ArrayList<String> brands_a = new ArrayList<>();
                for (String brand : Globals.BRANDS) {
                    if (brand.startsWith((ch + "").toLowerCase())) {
                        brand = brand.substring(0, 1).toUpperCase() + brand.substring(1);
                        brands_a.add(brand);
                    }
                }
                allbrands.add(brands_a);
            }
            ArrayList<String> brand_last = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<String>>> grouping = new ArrayList<>();
            ArrayList<ArrayList<String>> subgroup = new ArrayList<>();
            for (ArrayList<String> brand_1 : allbrands) {
                if (brand_last.size() > 0) {
                    subgroup.add(brand_last);
                    brand_last = new ArrayList<>();
                }
                listcount += brand_1.size() + 1;
                if (listcount >= 21) {
                    brand_last = brand_1;
                    listcount = brand_1.size() + 1;
                    grouping.add(subgroup);
                    subgroup = new ArrayList<>();
                } else {
                    subgroup.add(brand_1);
                }
            }
            if (subgroup.size() > 1) {
                grouping.add(subgroup);
            }
        %>
        <%  for (ArrayList<ArrayList<String>> brandscol : grouping) {%>
        <div class="three-container">
            <%for (ArrayList<String> brandalpha : brandscol) {
            %>

            <div style="font-weight:500;font-size: 13px;" align="center"><%=brandalpha.get(0).charAt(0)%></div>
            <ul class="brand-list">
                <% for (String brand : brandalpha) {%>

                <li class="full-brand">
                    <label class="container" data-brand="<%=brand%>"><%=brand%><input type="checkbox" onclick="brandchecked('<%=brand%>', this, 1);">
                        <span class="checkmark"></span>
                    </label>
                </li>

                <%}%>
            </ul>
            <%}%>
        </div>
        <%}%>
    </div>
</div>
<div class="filter-os filter-all">
    <span id="os-text">Operating Systems </span>
    <ul class="os-ul">
        <li>
            <label class="container" data-os="Android">Android<input onclick="oschecked('Android', this);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>
        <li>
            <label class="container" data-os="Windows">Windows<input onclick="oschecked('Windows', this);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>
        <li>
            <label class="container" data-os="Symbian">Symbian<input onclick="oschecked('Symbian', this);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>
        <li>
            <label class="container" data-os="BlackBerry">BlackBerry<input onclick="oschecked('BlackBerry', this);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>
        <li>
            <label class="container" data-os="Others">Others<input onclick="oschecked('Others', this);" type="checkbox">
                <span class="checkmark"></span>
            </label>
        </li>
    </ul>
</div>
<div class="filter-external filter-all"filter-type='external'>
    <span class="filter-text">External Memory </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="ext-lower" type="number"><div>GB</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="ext-lower" type="number"><div>GB</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
<div class="filter-internal filter-all" filter-type='internal'>
    <span class="filter-text">Internal Memory </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="int-lower" type="number"><div>GB</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="int-lower" type="number"><div>GB</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
<div class="filter-ram filter-all" filter-type='ram'>
    <span class="filter-text">RAM </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="RAM-lower" type="number"><div>GB</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="RAM-lower" type="number"><div>GB</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
<div class="filter-selfiecam filter-all" filter-type='selfiecam'>
    <span class="filter-text">Selfie Camera </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="selfiecam-lower" type="number"><div>MP</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="selfiecam-lower" type="number"><div>MP</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
<div class="filter-maincam filter-all" filter-type='maincam'>
    <span class="filter-text">Main Camera </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="maincam-lower" type="number"><div>MP</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" id="maincam-lower" type="number"><div>MP</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
<div class="filter-battery filter-all" filter-type='battery'>
    <span class="filter-text">Battery </span>
    <div class="row filter-inp" align="center">
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" style="width:70%;" id="battery-lower" type="number"><div>mAh</div></div>
        <div class="to" align="center">to</div>
        <div class="input-container row"><input onfocus="focusInput(this.parentElement.parentElement,'in');" onfocusout="focusInput(this.parentElement.parentElement,'out');" class="filter-input" style="width:70%;" id="battery-lower" type="number"><div>mAh</div></div>
        <i class="fa fa-check" onclick="addfilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
        <i class="fa fa-times" onclick="removefilterRange(this.parentElement.parentElement);" aria-hidden="true"></i>
    </div>
</div>
