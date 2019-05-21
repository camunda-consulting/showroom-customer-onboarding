<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Camunbankia - The Business Account Company</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <!-- Preloader -->
    <div class="preloader d-flex align-items-center justify-content-center">
        <div class="lds-ellipsis">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>

    <!-- ##### Header Area Start ##### -->
    <header class="header-area">
        <!-- Top Header Area -->
        <div class="top-header-area">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12 d-flex justify-content-between">
                        <!-- Logo Area -->
                        <div class="logo">
                            <a href="index.jsp"><img src="img/core-img/logo.png" alt=""></a>
                        </div>

                        <!-- Top Contact Info -->
                        <div class="top-contact-info d-flex align-items-center">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Zossener Str. 55, 10961 Berlin"><img src="img/core-img/placeholder.png" alt=""> <span>${param.lang == 'de' ? 'Zossener Str. 55, 10961 Berlin' :'3001 Brighton Blvd, Suite 450, Denver, CO 80216'}</span></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="info@camunda.com"><img src="img/core-img/message.png" alt="info@camunda.com"> <span>${param.lang == 'de' ? 'info@camunda.com' :'contact@camunda.com'}</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Navbar Area -->
        <div class="credit-main-menu" id="sticker">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="creditNav">

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">

                            <!-- Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                    <li><a href="index.jsp?lang=${param.lang}">Home</a></li>
                                    <li><a href="#">${param.lang == 'de' ? 'Seiten' :'Pages'}</a>
                                        <ul class="dropdown">
                                            <li><a >${param.lang == 'de' ? '&Uuml;ber Uns' : 'About Us'}</a></li>
                                            <li><a href="services.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Konten' : 'Bank Accounts'}</a></li>
                                            <li><a href="documents.jsp?lang=${param.lang}">${param.lang == 'de' ? 'Dokument einreichen' : 'Hand In Documents'}</a></li>
                                            <li><a >${param.lang == 'de' ? 'Kontakt' : 'Contact'}</a></li>
                                            <li><a >${param.lang == 'de' ? 'Weitere Elemente' : 'Elements'}</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!-- Nav End -->
                        </div>

                        <!-- Contact -->
                        <div class="contact">
                            <a href="#"><img src="img/core-img/call2.png" alt="">${param.lang == 'de' ? '&nbsp;030 664040900' :'&nbsp;+1 415 513 0111'}</a>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Hero Area Start ##### -->
    <div class="hero-area">
        <div class="hero-slideshow owl-carousel">

            <!-- Single Slide -->
            <div class="single-slide bg-img">
                <!-- Background Image-->
                <div class="slide-bg-img bg-img bg-overlay" style="background-image: url(img/bg-img/1.jpg);"></div>
                <!-- Welcome Text -->
                <div class="container h-100">
                    <div class="row h-100 align-items-center justify-content-center">
                        <div class="col-12 col-lg-9">
                            <div class="welcome-text text-center">
                                <h6 data-animation="fadeInUp" data-delay="100ms">${param.lang == 'de' ? 'Direkt - Einfach - Gut!' :'direct - easy - awesome'}</h6>
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? 'Hol dir jetzt</h2>' :'Get </h2>'}
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? '<span>Dein Girokonto,</span></h2>' :'<span>your personal account</span></h2>'}
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? 'das zu Dir passt</h2>' :'now</h2>'}
                                <a href="services.jsp?lang=${param.lang}" class="btn credit-btn mt-50" data-animation="fadeInUp" data-delay="700ms">${param.lang == 'de' ? 'Entdecken' :'Discover'}</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Slide Duration Indicator -->
                <div class="slide-du-indicator"></div>
            </div>

            <!-- Single Slide -->
            <div class="single-slide bg-img">
                <!-- Background Image-->
                <div class="slide-bg-img bg-img bg-overlay" style="background-image: url(img/bg-img/5.jpg);"></div>
                <!-- Welcome Text -->
                <div class="container h-100">
                    <div class="row h-100 align-items-center justify-content-center">
                        <div class="col-12 col-lg-9">
                            <div class="welcome-text text-center">
                                <h6 data-animation="fadeInUp" data-delay="100ms">${param.lang == 'de' ? '0,23 effektiver Jahreszins' :'0.23 APR'}</h6>
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? 'Hol Dir die</h2>' :'Get your</h2>'}
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? '<span>Immobilien-Finanzierung,</span></h2>' :'<span>personal mortgage loan</span></h2>'}
                                <h2 data-animation="fadeInUp" data-delay="300ms">${param.lang == 'de' ? 'die zu Dir passt!</h2>' :'now!</h2>'}
                                <a href="services.jsp?lang=${param.lang}" class="btn credit-btn mt-50" data-animation="fadeInDown" data-delay="700ms">${param.lang == 'de' ? 'Jetzt entdecken' :'Discover'}</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Slide Duration Indicator -->
                <div class="slide-du-indicator"></div>
            </div>
        </div>
    </div>
    <!-- ##### Hero Area End ##### -->

    <!-- ##### Features Area Start ###### -->
    <section class="features-area section-padding-100-0">
        <div class="container">
            <div class="row align-items-end">
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-features-area mb-100 wow fadeInUp" data-wow-delay="100ms">
                        <!-- Section Heading -->
                        <div class="section-heading">
                            <div class="line"></div>
                            <p>Take look at our</p>
                            <h2>Our Accounts</h2>
                        </div>
                        <h6>In vitae nisi aliquam, scelerisque leo a, volutpat sem. Viva mus rutrum dui fermentum eros hendrerit.</h6>
                        <a href="services.jsp?lang=${param.lang}" class="btn credit-btn mt-50">${param.lang == 'de' ? 'Entdecken' :'Discover'}</a>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-features-area mb-100 wow fadeInUp" data-wow-delay="300ms">
                        <img src="img/bg-img/2.jpg" alt="">
                        <h5>We take care of you</h5>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-features-area mb-100 wow fadeInUp" data-wow-delay="500ms">
                        <img src="img/bg-img/3.jpg" alt="">
                        <h5>No documents needed</h5>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-features-area mb-100 wow fadeInUp" data-wow-delay="700ms">
                        <img src="img/bg-img/4.jpg" alt="">
                        <h5>Fast &amp; easy Accounts</h5>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Features Area End ###### -->

    <!-- ##### Call To Action Start ###### -->
    <section class="cta-area d-flex flex-wrap">
        <!-- Cta Thumbnail -->
        <div class="cta-thumbnail bg-img jarallax" style="background-image: url(img/bg-img/5.jpg);"></div>

        <!-- Cta Content -->
        <div class="cta-content">
            <!-- Section Heading -->
            <div class="section-heading white">
                <div class="line"></div>
                <p>Bold desing and beyound</p>
                <h2>Helping small businesses like yours</h2>
            </div>
            <h6>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem. Duis non volutpat arcu, eu mollis tellus. Sed finibus aliquam neque sit amet sod ales. Maecenas sed magna tempor, efficitur maur is in, sollicitudin augue. Praesent pretium finibus quam.</h6>
            <div class="d-flex flex-wrap mt-50">
                <!-- Single Skills Area -->
                <div class="single-skils-area mb-70 mr-5">
                    <div id="circle" class="circle" data-value="0.90">
                        <div class="skills-text">
                            <span>90%</span>
                        </div>
                    </div>
                    <p>Energy</p>
                </div>

                <!-- Single Skills Area -->
                <div class="single-skils-area mb-70 mr-5">
                    <div id="circle2" class="circle" data-value="0.75">
                        <div class="skills-text">
                            <span>75%</span>
                        </div>
                    </div>
                    <p>power</p>
                </div>

                <!-- Single Skills Area -->
                <div class="single-skils-area mb-70">
                    <div id="circle3" class="circle" data-value="0.97">
                        <div class="skills-text">
                            <span>97%</span>
                        </div>
                    </div>
                    <p>resource</p>
                </div>
            </div>
            <a href="#" class="btn credit-btn box-shadow btn-2">Read More</a>
        </div>
    </section>
    <!-- ##### Call To Action End ###### -->

    <!-- ##### Call To Action Start ###### -->
    <section class="cta-2-area wow fadeInUp" data-wow-delay="100ms">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Cta Content -->
                    <div class="cta-content d-flex flex-wrap align-items-center justify-content-between">
                        <div class="cta-text">
                            <h4>Are you in need for a load? Get in touch with us.</h4>
                        </div>
                        <div class="cta-btn">
                            <a href="#" class="btn credit-btn box-shadow">Read More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Call To Action End ###### -->

    <!-- ##### Services Area Start ###### -->
    <section class="services-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center mb-100 wow fadeInUp" data-wow-delay="100ms">
                        <div class="line"></div>
                        <p>Take look at our</p>
                        <h2>Our services</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="200ms">
                        <div class="icon">
                            <i class="icon-profits"></i>
                        </div>
                        <div class="text">
                            <h5>All the Accounts</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>

                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="300ms">
                        <div class="icon">
                            <i class="icon-money-1"></i>
                        </div>
                        <div class="text">
                            <h5>Easy and fast answer</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>

                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="400ms">
                        <div class="icon">
                            <i class="icon-coin"></i>
                        </div>
                        <div class="text">
                            <h5>No additional papers</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>

                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="500ms">
                        <div class="icon">
                            <i class="icon-smartphone-1"></i>
                        </div>
                        <div class="text">
                            <h5>Secure financial services</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>

                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="600ms">
                        <div class="icon">
                            <i class="icon-diamond"></i>
                        </div>
                        <div class="text">
                            <h5>Good investments</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>

                <!-- Single Service Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-service-area d-flex mb-100 wow fadeInUp" data-wow-delay="700ms">
                        <div class="icon">
                            <i class="icon-piggy-bank"></i>
                        </div>
                        <div class="text">
                            <h5>Accumulation goals</h5>
                            <p>Morbi ut dapibus dui. Sed ut iaculis elit, quis varius mauris. Integer ut ultricies orci, lobortis egestas sem.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Services Area End ###### -->

    <!-- ##### Miscellaneous Area Start ###### -->
    <section class="miscellaneous-area bg-gray section-padding-100-0">
        <div class="container">
            <div class="row align-items-end justify-content-center">
                <!-- Add Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="add-area mb-100 wow fadeInUp" data-wow-delay="100ms">
                        <a href="#"><img src="img/bg-img/add.png" alt=""></a>
                    </div>
                </div>

                <!-- Contact Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="contact--area mb-100 wow fadeInUp" data-wow-delay="300ms">
                        <!-- Section Heading -->
                        <div class="section-heading mb-50">
                            <div class="line"></div>
                            <h2>Get in touch</h2>
                        </div>
                        <!-- Contact Content -->
                        <div class="contact-content">
                            <!-- Single Contact Content -->
                            <div class="single-contact-content d-flex align-items-center">
                                <div class="icon">
                                    <img src="img/core-img/location.png" alt="">
                                </div>
                                <div class="text">
                                    <p>3007 Sarah Drive <br> Franklin, LA 70538</p>
                                </div>
                            </div>
                            <!-- Single Contact Content -->
                            <div class="single-contact-content d-flex align-items-center">
                                <div class="icon">
                                    <img src="img/core-img/call.png" alt="">
                                </div>
                                <div class="text">
                                    <p>337-413-9538</p>
                                    <span>mon-fri , 08.am - 17.pm</span>
                                </div>
                            </div>
                            <!-- Single Contact Content -->
                            <div class="single-contact-content d-flex align-items-center">
                                <div class="icon">
                                    <img src="img/core-img/message2.png" alt="">
                                </div>
                                <div class="text">
                                    <p>contact@yourbusiness.com</p>
                                    <span>we reply in 24 hrs</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- News Area -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="news--area mb-100 wow fadeInUp" data-wow-delay="500ms">
                        <!-- Section Heading -->
                        <div class="section-heading mb-50">
                            <div class="line"></div>
                            <h2>The news</h2>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/10.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <span>July 18, 2018</span>
                                <a href="#">How to get the best Account online</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/11.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <span>July 18, 2018</span>
                                <a href="#">A new way to finance your dream home</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/12.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <span>July 18, 2018</span>
                                <a href="#">10 tips to get the best Account for you</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Miscellaneous Area End ###### -->

    <!-- ##### Newsletter Area Start ###### -->
    <section class="newsletter-area section-padding-100 bg-img jarallax" style="background-image: url(img/bg-img/6.jpg);">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-sm-10 col-lg-8">
                    <div class="nl-content text-center">
                        <h2>${param.lang == 'de' ? 'Abboniere unseren Newsletter' : 'Subscribe to our newsletter'}</h2>
                        <form action="#" method="post">
                            <input type="email" name="nl-email" id="nlemail" placeholder="Your e-mail">
                            <button type="submit">${param.lang == 'de' ? 'Abbonieren' : 'Subscribe'}</button>
                        </form>
                        <p>Curabitur elit turpis, maximus quis ullamcorper sed, maximus eu neque. Cras ultrices erat nec auctor blandit.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Newsletter Area End ###### -->

    <!-- ##### Footer Area Start ##### -->
    <footer class="footer-area section-padding-100-0">
        <div class="container">
            <div class="row">

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? '&Uuml;ber uns' : 'About Us'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">Homepage</a></li>
                                <li><a href="#">${param.lang == 'de' ? '&Uuml;ber uns' : 'About Us'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Service &amp' : 'Services &amp; Offers'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Portfolio Pr&auml;sentation' : 'Portfolio Presentation'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Neuigkeiten' : 'The News'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'L&ouml;sungen' : 'Solutions'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Accounts'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'kommerzielles Handeln' : 'Trading &amp; Commerce'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Banking &amp; Private Equity' : 'Banking &amp; Private Equity'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Industrie &amp; Fabrik' : 'Industrial &amp; Factory'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Finanzl&ouml;sungen' : 'Financial Solutions'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'L&ouml;sungen' : 'Solutions'}</h5>
                        <!-- Nav -->
                        <nav>
                            <ul>
                                <li><a href="#">${param.lang == 'de' ? 'Unsere Kredite' : 'Our Accounts'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'kommerzielles Handeln' : 'Trading &amp; Commerce'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Banking &amp; Private Equity' : 'Banking &amp; Private Equity'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Industrie &amp Fabrik;' : 'Industrial &amp; Factory'}</a></li>
                                <li><a href="#">${param.lang == 'de' ? 'Finanzl&ouml;sungen' : 'Financial Solutions'}</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-footer-widget mb-100">
                        <h5 class="widget-title">${param.lang == 'de' ? 'Das neuest vom Neuesten' : 'Latest News'}</h5>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/7.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Wie bekommt man den besten Kredit' : 'How to get the best Account'}?</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/8.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Neue Wege f&uuml;r neue Kredite' : 'A new way to get a Account'}</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-latest-news-area d-flex align-items-center">
                            <div class="news-thumbnail">
                                <img src="img/bg-img/9.jpg" alt="">
                            </div>
                            <div class="news-content">
                                <a href="#">${param.lang == 'de' ? 'Finanzier dir dein Eigenheim' : 'Finance you home'}</a>
                                <div class="news-meta">
                                    <a href="#" class="post-author"><img src="img/core-img/pencil.png" alt=""> Jane Smith</a>
                                    <a href="#" class="post-date"><img src="img/core-img/calendar.png" alt=""> April 26</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!-- Copywrite Area -->
        <div class="copywrite-area">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="copywrite-content d-flex flex-wrap justify-content-between align-items-center">
                            <!-- Footer Logo -->
                            <a href="index.jsp" class="footer-logo"><img src="img/core-img/logo.png" alt=""></a>

                            <!-- Copywrite Text -->
                            <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area Start ##### -->

    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
</body>

</html>
