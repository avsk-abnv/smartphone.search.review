<%-- 
    Document   : Homepage
    Created on : 8 Mar, 2019, 4:39:27 PM
    Author     : Abhishek Abhinav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Device Search and Review</title>
        <link rel="stylesheet" type="text/css" href="https://device-pics.firebaseapp.com/bootstrap.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <style>
            <%@ include file="./css/style.css" %>
        </style>
    </head>
    <body style="margin:0px;">
        <div id="middle">
            <div id="navbar-container">
                <div id="navbar" class="row">
                    <div class="logo">Project Logo</div>
                    <div class="search-box row">
                        <input type="text" placeholder="Search devices" class="search-input" />
                        <button class="search-icon"><i style="color:black" class="fa fa-search fa-lg"></i></button>
                    </div>
                    <div class="login">Login or Sign up</div>
                </div>
            </div>
            <div id="row-fmain" class="row">
                <div id="filter-container">
                    <div id="filters" class="column">
                        <div class="filter-title">Filters</div>
                    </div>
                </div>
                <div id="main-col" class="column">
                    <div id="tabs" class="row">
                        <div class="each-tab" style="background-color:#4d5760">Popularity</div>
                        <div class="each-tab">Price</div>
                        <div class="each-tab" style="width:34%;">Recommendation</div>
                    </div>
                    <div id="main-body" class="column">
                        <div class="grid-row row">
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="https://device-pics.firebaseapp.com/apple/Apple%20iPhone%20SE.jpg" /></div>
                                <h6 class="title">Apple iPhone SE</h6>
                                <h6 class="price">Price :₹42,000 </h6>
                                <!--<div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">5479</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count"> 2045</div>
                                    </div>
                                </div>-->
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone XS.jpg" /></div>
                                <h6 class="title">Apple iPhone XS</h6>
                                <h6 class="price">Price :₹45,999 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">8745</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">4213</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone XS Max.jpg" /></div>
                                <h6 class="title">Apple iPhone XS Max</h6>
                                <h6 class="price">Price :₹38,499 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">9564</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">6007</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone XR.jpg" /></div>
                                <h6 class="title">Apple iPhone XR</h6>
                                <h6 class="price">Price :₹35,999 </h6>

                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">12457</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">3245</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grid-row row">
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone X.jpg" /></div>
                                <h6 class="title">Apple iPhone X</h6>
                                <h6 class="price">Price :₹49,999 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">14325</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">965</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 8.jpg" /></div>
                                <h6 class="title">Apple iPhone 8</h6>
                                <h6 class="price">Price :₹62,999 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">4125</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">564</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 8 Plus.jpg" /></div>
                                <h6 class="title">Apple iPhone 8 Plus</h6>
                                <h6 class="price">Price :₹65,499 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">5687</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">248</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 7.jpg" /></div>
                                <h6 class="title">DApple iPhone 7</h6>
                                <h6 class="price">Price :₹55,999 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">6487</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">785</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="grid-row row">
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 7 Plus.jpg" /></div>
                                <h6 class="title">Apple iPhone 7 Plus</h6>
                                <h6 class="price">Price :₹59,000 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">5468</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">6235</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 6s.jpg" /></div>
                                <h6 class="title">Apple iPhone 6s</h6>
                                <h6 class="price">Price :₹48,799 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">7548</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">9547</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 6s Plus.jpg" /></div>
                                <h6 class="title">Apple iPhone 6s Plus</h6>
                                <h6 class="price">Price :₹51,299 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">4789</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">3958</div>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-cols column">
                                <div class="img-container"><img class="thumbnails" src="Apple iPhone 5.jpg" /></div>
                                <h6 class="title">Apple iPhone 5</h6>
                                <h6 class="price">Price :₹43,899 </h6>
                                <div class="like-dislike row">
                                    <div class="like-container column">
                                        <div class="like-icon"><i style="color:#7d8ca3;" class="fa fa-thumbs-up fa-lg"></i></div>
                                        <div class="like-count">7859</div>
                                    </div>
                                    <div class="dislike-container column">
                                        <div class="dislike-icon"><i style="color:#a05959;" class="fa fa-thumbs-down fa-lg"></i></div>
                                        <div class="dislike-count">4569</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="page-row row">
                            <div class="left-spacing"></div>
                            <div class="page-no" style="color: white;background-color: #0087a6;">1</div>
                            <div class="page-no">2</div>
                            <div class="page-no">3</div>
                            <div class="page-no">4</div>
                            <div class="page-no">5</div>
                            <div class="page-no">6</div>
                            <div class="page-no">7</div>
                            <div class="page-no">8</div>
                            <div class="page-no">9</div>
                            <div class="page-no">10</div>
                            <div class="next-page">NEXT</div>
                            <div class="right-spacing"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
