<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
   <head>
      <meta charset="utf-8">
      <title>Camundanzia</title>
      <meta name="description" content="Pixma Responsive HTML5/CSS3 Template from FIFOTHEMES.COM">
      <meta name="author" content="FIFOTHEMES.COM">
      <!-- Mobile Metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Google Fonts -->
<!--       <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'> -->
<!--       <link href='http://fonts.googleapis.com/css?family=Lato:400,300,400italic,700,300italic' rel='stylesheet' type='text/css'> -->
      <!-- Library CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
      <link rel="stylesheet" href="css/bootstrap-theme.css">
      <link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
      <link rel="stylesheet" href="css/animations.css" media="screen">
      <link rel="stylesheet" href="css/superfish.css" media="screen">
      <link rel="stylesheet" href="css/revolution-slider/css/settings.css" media="screen">
      <link rel="stylesheet" href="css/prettyPhoto.css" media="screen">
      <!-- Theme CSS -->
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="css/global.css">
      <!-- Skin -->
      <link rel="stylesheet" href="css/colors/blue.css" class="colors">
      <!-- Responsive CSS -->
      <link rel="stylesheet" href="css/theme-responsive.css">

    <script src="camunda-bpm-sdk.js" ></script>
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<!--     <script src="../webjars/jquery/1.9.0/jquery.js" -->


   </head>
   <body class="boxed home">
      <div class="page-mask">
            <div class="page-loader"> 

                <div class="spinner"></div>
                Loading...
            </div>

      </div>
      <div class="wrap">
         <!-- Header Start -->
         <header id="header">
<%@ include file="headerTopBar.inc" %>            
<%@ include file="mainHeader.inc" %>
		</header>
         <!-- Header End -->             
         <!-- Content Start -->
         <div id="main">
            <!-- Title, Breadcrumb Start-->
            <div class="breadcrumb-wrapper">
               <div class="container">
                  <div class="row">
                     <div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
                        <h2 class="title">${param.lang == 'de' ? 'Unterlagen einreichen' : 'Hand in Documents'}</h2>
                     </div>
                     <div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
                        <div class="breadcrumbs pull-right">
                           <ul>
                              <li>${param.lang == 'de' ? 'Sie sind hier:' : 'You are here:'}</li>
                              <li><a href="index.jsp?lang=${param.lang}">Home</a></li>
                              <li>${param.lang == 'de' ? 'Unterlagen einreichen' : 'Hand in Documents'}</li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <!-- Title, Breadcrumb End-->
            <!-- Main Content start-->
            <div class="content">
               <div class="container">
                  <div class="row">
                     <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12" id="contact-form">
                        <form method="post" class="reply" id="contact">
                           <div id="fieldsetForm">
                           <fieldset>
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <label>${param.lang == 'de' ? 'Vorgangsnummer' : 'Reference Number'}: <span>*</span></label>
                                    <input class="form-control" id="referenceId" name="name" type="text" value="" required>
                                 </div>
                              </div>
                              
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <label>${param.lang == 'de' ? 'PDF-Dokument' : 'PDF Document'}: <span>*</span></label>
		                            <input id="documentToUpload" name="Dokument hochladen" type="file" accept="application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>
                                 </div>
                              </div>
                                                       
                           <button id="triggerUploadDocuments" class="btn btn-normal btn-color submit  bottom-pad" type="button">${param.lang == 'de' ? 'Abschicken' : 'Send'}</button>
                           </fieldset>
                           </div>
                           <div id="documentsReceived" class="success alert-success alert" style="display:none">${param.lang == 'de' ? 'Unterlagen erhalten - wir melden uns!' : 'Documents received - we reach out to you shortly!'}</div>
                           <div class="clearfix">
                           </div>
                        </form>
                     </div>
                     <div class="col-lg-4 col-md-4 col-xs-12 col-sm-6">
                       <!-- <img src="http://polpix.sueddeutsche.com/bild/1.1196262.1376398621/900x600/kfzversicherung-versicherung-kfz-auto-assekuranz-tarif-tarifwechsel.jpg"> -->
                     </div>
                  </div>
                  <div class="divider"></div>
               </div>
            </div>
            <!-- Main Content end-->
         </div>
         <!-- Content End -->
         <!-- Footer Start -->

<%@ include file="footer.inc" %>
      </div>
      <!-- Wrap End -->

<%@ include file="scripts.inc" %>
   </body>
</html>